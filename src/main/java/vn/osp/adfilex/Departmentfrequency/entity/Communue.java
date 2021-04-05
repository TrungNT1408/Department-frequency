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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "communue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Communue.findAll", query = "SELECT c FROM Communue c"),
    @NamedQuery(name = "Communue.findByCommunueId", query = "SELECT c FROM Communue c WHERE c.communueId = :communueId"),
    @NamedQuery(name = "Communue.findByCommunueName", query = "SELECT c FROM Communue c WHERE c.communueName = :communueName")})
public class Communue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "communue_id")
    private String communueId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "communue_name")
    private String communueName;
    @JoinColumn(name = "district_id", referencedColumnName = "district_id")
    @ManyToOne(optional = false)
    private District districtId;

    public Communue() {
    }

    public Communue(String communueId) {
        this.communueId = communueId;
    }

    public Communue(String communueId, String communueName) {
        this.communueId = communueId;
        this.communueName = communueName;
    }

    public String getCommunueId() {
        return communueId;
    }

    public void setCommunueId(String communueId) {
        this.communueId = communueId;
    }

    public String getCommunueName() {
        return communueName;
    }

    public void setCommunueName(String communueName) {
        this.communueName = communueName;
    }

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId(District districtId) {
        this.districtId = districtId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (communueId != null ? communueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Communue)) {
            return false;
        }
        Communue other = (Communue) object;
        if ((this.communueId == null && other.communueId != null) || (this.communueId != null && !this.communueId.equals(other.communueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Communue[ communueId=" + communueId + " ]";
    }
    
}
