/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "cell")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cell.findAll", query = "SELECT c FROM Cell c"),
    @NamedQuery(name = "Cell.findByCellId", query = "SELECT c FROM Cell c WHERE c.cellId = :cellId"),
    @NamedQuery(name = "Cell.findByTechType", query = "SELECT c FROM Cell c WHERE c.techType = :techType"),
    @NamedQuery(name = "Cell.findByFreq", query = "SELECT c FROM Cell c WHERE c.freq = :freq"),
    @NamedQuery(name = "Cell.findByTilt", query = "SELECT c FROM Cell c WHERE c.tilt = :tilt"),
    @NamedQuery(name = "Cell.findByBcch", query = "SELECT c FROM Cell c WHERE c.bcch = :bcch"),
    @NamedQuery(name = "Cell.findBySc", query = "SELECT c FROM Cell c WHERE c.sc = :sc"),
    @NamedQuery(name = "Cell.findByLastUpdate", query = "SELECT c FROM Cell c WHERE c.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Cell.findByStartDate", query = "SELECT c FROM Cell c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "Cell.findByEndDate", query = "SELECT c FROM Cell c WHERE c.endDate = :endDate"),
    @NamedQuery(name = "Cell.findByState", query = "SELECT c FROM Cell c WHERE c.state = :state"),
    @NamedQuery(name = "Cell.findByAzimuth", query = "SELECT c FROM Cell c WHERE c.azimuth = :azimuth")})
public class Cell implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cell_id")
    private String cellId;
    @Size(max = 8)
    @Column(name = "tech_type")
    private String techType;
    @Size(max = 45)
    @Column(name = "freq")
    private String freq;
    @Size(max = 45)
    @Column(name = "tilt")
    private String tilt;
    @Size(max = 45)
    @Column(name = "bcch")
    private String bcch;
    @Size(max = 45)
    @Column(name = "sc")
    private String sc;
    @Size(max = 45)
    @Column(name = "last_update")
    private String lastUpdate;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Size(max = 12)
    @Column(name = "state")
    private String state;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "azimuth")
    private Double azimuth;
    @JoinColumn(name = "bts_id", referencedColumnName = "bts_id")
    @ManyToOne(optional = false)
    private Bts btsId;

    public Cell() {
    }

    public Cell(String cellId) {
        this.cellId = cellId;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getBcch() {
        return bcch;
    }

    public void setBcch(String bcch) {
        this.bcch = bcch;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(Double azimuth) {
        this.azimuth = azimuth;
    }

    public Bts getBtsId() {
        return btsId;
    }

    public void setBtsId(Bts btsId) {
        this.btsId = btsId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cellId != null ? cellId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cell)) {
            return false;
        }
        Cell other = (Cell) object;
        if ((this.cellId == null && other.cellId != null) || (this.cellId != null && !this.cellId.equals(other.cellId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Cell[ cellId=" + cellId + " ]";
    }
    
}
