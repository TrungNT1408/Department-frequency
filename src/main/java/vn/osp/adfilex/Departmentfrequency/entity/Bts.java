/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "bts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bts.findAll", query = "SELECT b FROM Bts b"),
    @NamedQuery(name = "Bts.findByBtsId", query = "SELECT b FROM Bts b WHERE b.btsId = :btsId"),
    @NamedQuery(name = "Bts.findByBtsName", query = "SELECT b FROM Bts b WHERE b.btsName = :btsName"),
    @NamedQuery(name = "Bts.findByMcc", query = "SELECT b FROM Bts b WHERE b.mcc = :mcc"),
    @NamedQuery(name = "Bts.findByMnc", query = "SELECT b FROM Bts b WHERE b.mnc = :mnc"),
    @NamedQuery(name = "Bts.findByLon", query = "SELECT b FROM Bts b WHERE b.lon = :lon"),
    @NamedQuery(name = "Bts.findByLat", query = "SELECT b FROM Bts b WHERE b.lat = :lat"),
    @NamedQuery(name = "Bts.findByAntenaH", query = "SELECT b FROM Bts b WHERE b.antenaH = :antenaH"),
    @NamedQuery(name = "Bts.findByMorphology", query = "SELECT b FROM Bts b WHERE b.morphology = :morphology"),
    @NamedQuery(name = "Bts.findByStatisticTime", query = "SELECT b FROM Bts b WHERE b.statisticTime = :statisticTime"),
    @NamedQuery(name = "Bts.findByLastUpdate", query = "SELECT b FROM Bts b WHERE b.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Bts.findByTelco", query = "SELECT b FROM Bts b WHERE b.telco = :telco"),
    @NamedQuery(name = "Bts.findByProvince", query = "SELECT b FROM Bts b WHERE b.province = :province"),
    @NamedQuery(name = "Bts.findByType", query = "SELECT b FROM Bts b WHERE b.type = :type")})
public class Bts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "bts_id")
    private String btsId;
    @Size(max = 32)
    @Column(name = "bts_name")
    private String btsName;
    @Size(max = 45)
    @Column(name = "mcc")
    private String mcc;
    @Size(max = 45)
    @Column(name = "mnc")
    private String mnc;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Size(max = 45)
    @Column(name = "antena_h")
    private String antenaH;
    @Size(max = 45)
    @Column(name = "morphology")
    private String morphology;
    @Size(max = 45)
    @Column(name = "statistic_time")
    private String statisticTime;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Size(max = 20)
    @Column(name = "telco")
    private String telco;
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    @Size(max = 45)
    @Column(name = "type")
    private String type;
    @JoinColumn(name = "site_id", referencedColumnName = "site_id")
    @ManyToOne
    private Site siteId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "btsId")
    private Collection<Cell> cellCollection;

    public Bts() {
    }

    public Bts(String btsId) {
        this.btsId = btsId;
    }

    public String getBtsId() {
        return btsId;
    }

    public void setBtsId(String btsId) {
        this.btsId = btsId;
    }

    public String getBtsName() {
        return btsName;
    }

    public void setBtsName(String btsName) {
        this.btsName = btsName;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAntenaH() {
        return antenaH;
    }

    public void setAntenaH(String antenaH) {
        this.antenaH = antenaH;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
    }

    public String getStatisticTime() {
        return statisticTime;
    }

    public void setStatisticTime(String statisticTime) {
        this.statisticTime = statisticTime;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Site getSiteId() {
        return siteId;
    }

    public void setSiteId(Site siteId) {
        this.siteId = siteId;
    }

    @XmlTransient
    public Collection<Cell> getCellCollection() {
        return cellCollection;
    }

    public void setCellCollection(Collection<Cell> cellCollection) {
        this.cellCollection = cellCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (btsId != null ? btsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bts)) {
            return false;
        }
        Bts other = (Bts) object;
        if ((this.btsId == null && other.btsId != null) || (this.btsId != null && !this.btsId.equals(other.btsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Bts[ btsId=" + btsId + " ]";
    }
    
}
