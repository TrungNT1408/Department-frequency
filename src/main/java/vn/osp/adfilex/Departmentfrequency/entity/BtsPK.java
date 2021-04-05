/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author abc
 */
@Embeddable
public class BtsPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "bts_name")
    private String btsName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private long id;

    public BtsPK() {
    }

    public BtsPK(String btsName, long id) {
        this.btsName = btsName;
        this.id = id;
    }

    public String getBtsName() {
        return btsName;
    }

    public void setBtsName(String btsName) {
        this.btsName = btsName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (btsName != null ? btsName.hashCode() : 0);
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BtsPK)) {
            return false;
        }
        BtsPK other = (BtsPK) object;
        if ((this.btsName == null && other.btsName != null) || (this.btsName != null && !this.btsName.equals(other.btsName))) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BtsPK[ btsName=" + btsName + ", id=" + id + " ]";
    }
    
}
