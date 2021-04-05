/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "kpi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kpi.findAll", query = "SELECT k FROM Kpi k"),
    @NamedQuery(name = "Kpi.findById", query = "SELECT k FROM Kpi k WHERE k.id = :id"),
    @NamedQuery(name = "Kpi.findByBscName", query = "SELECT k FROM Kpi k WHERE k.bscName = :bscName"),
    @NamedQuery(name = "Kpi.findBySiteName", query = "SELECT k FROM Kpi k WHERE k.siteName = :siteName"),
    @NamedQuery(name = "Kpi.findByCellName", query = "SELECT k FROM Kpi k WHERE k.cellName = :cellName"),
    @NamedQuery(name = "Kpi.findByCi", query = "SELECT k FROM Kpi k WHERE k.ci = :ci"),
    @NamedQuery(name = "Kpi.findBySiteId", query = "SELECT k FROM Kpi k WHERE k.siteId = :siteId"),
    @NamedQuery(name = "Kpi.findByLongDec", query = "SELECT k FROM Kpi k WHERE k.longDec = :longDec"),
    @NamedQuery(name = "Kpi.findByLatDec", query = "SELECT k FROM Kpi k WHERE k.latDec = :latDec"),
    @NamedQuery(name = "Kpi.findByProvince", query = "SELECT k FROM Kpi k WHERE k.province = :province"),
    @NamedQuery(name = "Kpi.findByDistrict", query = "SELECT k FROM Kpi k WHERE k.district = :district"),
    @NamedQuery(name = "Kpi.findByCommunue", query = "SELECT k FROM Kpi k WHERE k.communue = :communue"),
    @NamedQuery(name = "Kpi.findByMorphology", query = "SELECT k FROM Kpi k WHERE k.morphology = :morphology"),
    @NamedQuery(name = "Kpi.findByLac", query = "SELECT k FROM Kpi k WHERE k.lac = :lac"),
    @NamedQuery(name = "Kpi.findByBcch", query = "SELECT k FROM Kpi k WHERE k.bcch = :bcch"),
    @NamedQuery(name = "Kpi.findByBsic", query = "SELECT k FROM Kpi k WHERE k.bsic = :bsic"),
    @NamedQuery(name = "Kpi.findByTch", query = "SELECT k FROM Kpi k WHERE k.tch = :tch"),
    @NamedQuery(name = "Kpi.findByBand", query = "SELECT k FROM Kpi k WHERE k.band = :band"),
    @NamedQuery(name = "Kpi.findByAntenaHeight", query = "SELECT k FROM Kpi k WHERE k.antenaHeight = :antenaHeight"),
    @NamedQuery(name = "Kpi.findByAzimuth", query = "SELECT k FROM Kpi k WHERE k.azimuth = :azimuth"),
    @NamedQuery(name = "Kpi.findByTotalTilt", query = "SELECT k FROM Kpi k WHERE k.totalTilt = :totalTilt"),
    @NamedQuery(name = "Kpi.findByPower", query = "SELECT k FROM Kpi k WHERE k.power = :power"),
    @NamedQuery(name = "Kpi.findByLastUpdate", query = "SELECT k FROM Kpi k WHERE k.lastUpdate = :lastUpdate")})
public class Kpi implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "bsc_name")
    private String bscName;
    @Size(max = 45)
    @Column(name = "site_name")
    private String siteName;
    @Size(max = 45)
    @Column(name = "cell_name")
    private String cellName;
    @Size(max = 45)
    @Column(name = "ci")
    private String ci;
    @Size(max = 45)
    @Column(name = "site_id")
    private String siteId;
    @Size(max = 45)
    @Column(name = "long_dec")
    private String longDec;
    @Size(max = 45)
    @Column(name = "lat_dec")
    private String latDec;
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    @Size(max = 45)
    @Column(name = "district")
    private String district;
    @Size(max = 45)
    @Column(name = "communue")
    private String communue;
    @Size(max = 45)
    @Column(name = "morphology")
    private String morphology;
    @Size(max = 45)
    @Column(name = "lac")
    private String lac;
    @Size(max = 45)
    @Column(name = "bcch")
    private String bcch;
    @Size(max = 45)
    @Column(name = "bsic")
    private String bsic;
    @Size(max = 45)
    @Column(name = "tch")
    private String tch;
    @Size(max = 45)
    @Column(name = "band")
    private String band;
    @Size(max = 45)
    @Column(name = "antena_height")
    private String antenaHeight;
    @Size(max = 45)
    @Column(name = "azimuth")
    private String azimuth;
    @Size(max = 45)
    @Column(name = "total_tilt")
    private String totalTilt;
    @Size(max = 45)
    @Column(name = "power")
    private String power;
    @Size(max = 45)
    @Column(name = "last_update")
    private String lastUpdate;

    public Kpi() {
    }

    public Kpi(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBscName() {
        return bscName;
    }

    public void setBscName(String bscName) {
        this.bscName = bscName;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public String getLongDec() {
        return longDec;
    }

    public void setLongDec(String longDec) {
        this.longDec = longDec;
    }

    public String getLatDec() {
        return latDec;
    }

    public void setLatDec(String latDec) {
        this.latDec = latDec;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommunue() {
        return communue;
    }

    public void setCommunue(String communue) {
        this.communue = communue;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
    }

    public String getLac() {
        return lac;
    }

    public void setLac(String lac) {
        this.lac = lac;
    }

    public String getBcch() {
        return bcch;
    }

    public void setBcch(String bcch) {
        this.bcch = bcch;
    }

    public String getBsic() {
        return bsic;
    }

    public void setBsic(String bsic) {
        this.bsic = bsic;
    }

    public String getTch() {
        return tch;
    }

    public void setTch(String tch) {
        this.tch = tch;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public String getAntenaHeight() {
        return antenaHeight;
    }

    public void setAntenaHeight(String antenaHeight) {
        this.antenaHeight = antenaHeight;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getTotalTilt() {
        return totalTilt;
    }

    public void setTotalTilt(String totalTilt) {
        this.totalTilt = totalTilt;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
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
        if (!(object instanceof Kpi)) {
            return false;
        }
        Kpi other = (Kpi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Kpi[ id=" + id + " ]";
    }
    
}
