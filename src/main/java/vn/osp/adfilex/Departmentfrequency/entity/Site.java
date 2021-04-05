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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "site")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Site.findAll", query = "SELECT s FROM Site s"),
    @NamedQuery(name = "Site.findBySiteId", query = "SELECT s FROM Site s WHERE s.siteId = :siteId"),
    @NamedQuery(name = "Site.findBySiteName", query = "SELECT s FROM Site s WHERE s.siteName = :siteName"),
    @NamedQuery(name = "Site.findByLastUpdate", query = "SELECT s FROM Site s WHERE s.lastUpdate = :lastUpdate"),
    @NamedQuery(name = "Site.findByProvinceId", query = "SELECT s FROM Site s WHERE s.provinceId = :provinceId"),
    @NamedQuery(name = "Site.findByDistrictId", query = "SELECT s FROM Site s WHERE s.districtId = :districtId"),
    @NamedQuery(name = "Site.findByCommunueId", query = "SELECT s FROM Site s WHERE s.communueId = :communueId")})
public class Site implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "site_id")
    private Long siteId;
    @Size(max = 45)
    @Column(name = "site_name")
    private String siteName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @Size(max = 8)
    @Column(name = "province_id")
    private String provinceId;
    @Size(max = 8)
    @Column(name = "district_id")
    private String districtId;
    @Size(max = 8)
    @Column(name = "communue_id")
    private String communueId;
    @OneToMany(mappedBy = "siteId")
    private Collection<Bts> btsCollection;

    public Site() {
    }

    public Site(Long siteId) {
        this.siteId = siteId;
    }

    public Site(Long siteId, Date lastUpdate) {
        this.siteId = siteId;
        this.lastUpdate = lastUpdate;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getCommunueId() {
        return communueId;
    }

    public void setCommunueId(String communueId) {
        this.communueId = communueId;
    }

    @XmlTransient
    public Collection<Bts> getBtsCollection() {
        return btsCollection;
    }

    public void setBtsCollection(Collection<Bts> btsCollection) {
        this.btsCollection = btsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (siteId != null ? siteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Site)) {
            return false;
        }
        Site other = (Site) object;
        if ((this.siteId == null && other.siteId != null) || (this.siteId != null && !this.siteId.equals(other.siteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Site[ siteId=" + siteId + " ]";
    }
    
}
