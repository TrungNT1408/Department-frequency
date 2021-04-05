/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "kpi_noise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KpiNoise.findAll", query = "SELECT k FROM KpiNoise k"),
    @NamedQuery(name = "KpiNoise.findById", query = "SELECT k FROM KpiNoise k WHERE k.id = :id"),
    @NamedQuery(name = "KpiNoise.findByCellId", query = "SELECT k FROM KpiNoise k WHERE k.cellId = :cellId"),
    @NamedQuery(name = "KpiNoise.findBySiteId", query = "SELECT k FROM KpiNoise k WHERE k.siteId = :siteId"),
    @NamedQuery(name = "KpiNoise.findByMnc", query = "SELECT k FROM KpiNoise k WHERE k.mnc = :mnc"),
    @NamedQuery(name = "KpiNoise.findByHosr", query = "SELECT k FROM KpiNoise k WHERE k.hosr = :hosr"),
    @NamedQuery(name = "KpiNoise.findByCdr", query = "SELECT k FROM KpiNoise k WHERE k.cdr = :cdr"),
    @NamedQuery(name = "KpiNoise.findByInterference", query = "SELECT k FROM KpiNoise k WHERE k.interference = :interference"),
    @NamedQuery(name = "KpiNoise.findByLastUpdate", query = "SELECT k FROM KpiNoise k WHERE k.lastUpdate = :lastUpdate")})
public class KpiNoise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "cell_id")
    private BigInteger cellId;
    @Column(name = "site_id")
    private BigInteger siteId;
    @Size(max = 45)
    @Column(name = "mnc")
    private String mnc;
    @Size(max = 45)
    @Column(name = "hosr")
    private String hosr;
    @Size(max = 45)
    @Column(name = "cdr")
    private String cdr;
    @Size(max = 45)
    @Column(name = "interference")
    private String interference;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public KpiNoise() {
    }

    public KpiNoise(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getCellId() {
        return cellId;
    }

    public void setCellId(BigInteger cellId) {
        this.cellId = cellId;
    }

    public BigInteger getSiteId() {
        return siteId;
    }

    public void setSiteId(BigInteger siteId) {
        this.siteId = siteId;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getHosr() {
        return hosr;
    }

    public void setHosr(String hosr) {
        this.hosr = hosr;
    }

    public String getCdr() {
        return cdr;
    }

    public void setCdr(String cdr) {
        this.cdr = cdr;
    }

    public String getInterference() {
        return interference;
    }

    public void setInterference(String interference) {
        this.interference = interference;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KpiNoise)) {
            return false;
        }
        KpiNoise other = (KpiNoise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.KpiNoise[ id=" + id + " ]";
    }
    
}
