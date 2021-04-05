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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cell_noise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CellNoise.findAll", query = "SELECT c FROM CellNoise c"),
    @NamedQuery(name = "CellNoise.findById", query = "SELECT c FROM CellNoise c WHERE c.id = :id"),
    @NamedQuery(name = "CellNoise.findByProvince", query = "SELECT c FROM CellNoise c WHERE c.province = :province"),
    @NamedQuery(name = "CellNoise.findByCellId", query = "SELECT c FROM CellNoise c WHERE c.cellId = :cellId"),
    @NamedQuery(name = "CellNoise.findByCi", query = "SELECT c FROM CellNoise c WHERE c.ci = :ci"),
    @NamedQuery(name = "CellNoise.findByPciPcs", query = "SELECT c FROM CellNoise c WHERE c.pciPcs = :pciPcs"),
    @NamedQuery(name = "CellNoise.findByArfcndl", query = "SELECT c FROM CellNoise c WHERE c.arfcndl = :arfcndl"),
    @NamedQuery(name = "CellNoise.findByBand", query = "SELECT c FROM CellNoise c WHERE c.band = :band"),
    @NamedQuery(name = "CellNoise.findByTacLac", query = "SELECT c FROM CellNoise c WHERE c.tacLac = :tacLac"),
    @NamedQuery(name = "CellNoise.findByFreq", query = "SELECT c FROM CellNoise c WHERE c.freq = :freq"),
    @NamedQuery(name = "CellNoise.findByLon", query = "SELECT c FROM CellNoise c WHERE c.lon = :lon"),
    @NamedQuery(name = "CellNoise.findByLat", query = "SELECT c FROM CellNoise c WHERE c.lat = :lat"),
    @NamedQuery(name = "CellNoise.findByHight", query = "SELECT c FROM CellNoise c WHERE c.hight = :hight"),
    @NamedQuery(name = "CellNoise.findByAzimuth", query = "SELECT c FROM CellNoise c WHERE c.azimuth = :azimuth"),
    @NamedQuery(name = "CellNoise.findByTilt", query = "SELECT c FROM CellNoise c WHERE c.tilt = :tilt"),
    @NamedQuery(name = "CellNoise.findByRtwp", query = "SELECT c FROM CellNoise c WHERE c.rtwp = :rtwp"),
    @NamedQuery(name = "CellNoise.findByNumDayNoise", query = "SELECT c FROM CellNoise c WHERE c.numDayNoise = :numDayNoise"),
    @NamedQuery(name = "CellNoise.findByWat", query = "SELECT c FROM CellNoise c WHERE c.wat = :wat"),
    @NamedQuery(name = "CellNoise.findByStatus", query = "SELECT c FROM CellNoise c WHERE c.status = :status"),
    @NamedQuery(name = "CellNoise.findByCreateTime", query = "SELECT c FROM CellNoise c WHERE c.createTime = :createTime"),
    @NamedQuery(name = "CellNoise.findByUpdateTime", query = "SELECT c FROM CellNoise c WHERE c.updateTime = :updateTime"),
    @NamedQuery(name = "CellNoise.findBySys", query = "SELECT c FROM CellNoise c WHERE c.sys = :sys"),
    @NamedQuery(name = "CellNoise.findByStartDate", query = "SELECT c FROM CellNoise c WHERE c.startDate = :startDate"),
    @NamedQuery(name = "CellNoise.findByEndDate", query = "SELECT c FROM CellNoise c WHERE c.endDate = :endDate")})
public class CellNoise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "province")
    private String province;
    @Size(max = 45)
    @Column(name = "cell_id")
    private String cellId;
    @Column(name = "ci")
    private BigInteger ci;
    @Column(name = "pci_pcs")
    private Integer pciPcs;
    @Column(name = "arfcndl")
    private Integer arfcndl;
    @Column(name = "band")
    private Integer band;
    @Column(name = "tac_lac")
    private Integer tacLac;
    @Size(max = 45)
    @Column(name = "freq")
    private String freq;
    @Size(max = 45)
    @Column(name = "lon")
    private String lon;
    @Size(max = 45)
    @Column(name = "lat")
    private String lat;
    @Size(max = 45)
    @Column(name = "hight")
    private String hight;
    @Size(max = 45)
    @Column(name = "azimuth")
    private String azimuth;
    @Size(max = 45)
    @Column(name = "tilt")
    private String tilt;
    @Size(max = 45)
    @Column(name = "rtwp")
    private String rtwp;
    @Size(max = 45)
    @Column(name = "num_day_noise")
    private String numDayNoise;
    @Size(max = 45)
    @Column(name = "wat")
    private String wat;
    @Size(max = 16)
    @Column(name = "status")
    private String status;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;
    @Column(name = "sys")
    private Integer sys;
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @JoinColumn(name = "cell_noise_info_id", referencedColumnName = "id")
    @ManyToOne
    private CellNoiseInfo cellNoiseInfoId;

    public CellNoise() {
    }

    public CellNoise(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public BigInteger getCi() {
        return ci;
    }

    public void setCi(BigInteger ci) {
        this.ci = ci;
    }

    public Integer getPciPcs() {
        return pciPcs;
    }

    public void setPciPcs(Integer pciPcs) {
        this.pciPcs = pciPcs;
    }

    public Integer getArfcndl() {
        return arfcndl;
    }

    public void setArfcndl(Integer arfcndl) {
        this.arfcndl = arfcndl;
    }

    public Integer getBand() {
        return band;
    }

    public void setBand(Integer band) {
        this.band = band;
    }

    public Integer getTacLac() {
        return tacLac;
    }

    public void setTacLac(Integer tacLac) {
        this.tacLac = tacLac;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getHight() {
        return hight;
    }

    public void setHight(String hight) {
        this.hight = hight;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getRtwp() {
        return rtwp;
    }

    public void setRtwp(String rtwp) {
        this.rtwp = rtwp;
    }

    public String getNumDayNoise() {
        return numDayNoise;
    }

    public void setNumDayNoise(String numDayNoise) {
        this.numDayNoise = numDayNoise;
    }

    public String getWat() {
        return wat;
    }

    public void setWat(String wat) {
        this.wat = wat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getSys() {
        return sys;
    }

    public void setSys(Integer sys) {
        this.sys = sys;
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

    public CellNoiseInfo getCellNoiseInfoId() {
        return cellNoiseInfoId;
    }

    public void setCellNoiseInfoId(CellNoiseInfo cellNoiseInfoId) {
        this.cellNoiseInfoId = cellNoiseInfoId;
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
        if (!(object instanceof CellNoise)) {
            return false;
        }
        CellNoise other = (CellNoise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CellNoise[ id=" + id + " ]";
    }
    
}
