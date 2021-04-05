/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "btsf")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Btsf.findAll", query = "SELECT b FROM Btsf b"),
    @NamedQuery(name = "Btsf.findById", query = "SELECT b FROM Btsf b WHERE b.id = :id"),
    @NamedQuery(name = "Btsf.findByLat", query = "SELECT b FROM Btsf b WHERE b.lat = :lat"),
    @NamedQuery(name = "Btsf.findByLon", query = "SELECT b FROM Btsf b WHERE b.lon = :lon"),
    @NamedQuery(name = "Btsf.findByTelco", query = "SELECT b FROM Btsf b WHERE b.telco = :telco")})
public class Btsf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lat")
    private Double lat;
    @Column(name = "lon")
    private Double lon;
    @Basic(optional = false)
    @NotNull
    @Column(name = "telco")
    private int telco;
    @OneToMany(mappedBy = "btsId")
    private Collection<BtsCell> btsCellCollection;

    public Btsf() {
    }

    public Btsf(Long id) {
        this.id = id;
    }

    public Btsf(Long id, int telco) {
        this.id = id;
        this.telco = telco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public int getTelco() {
        return telco;
    }

    public void setTelco(int telco) {
        this.telco = telco;
    }

    @XmlTransient
    public Collection<BtsCell> getBtsCellCollection() {
        return btsCellCollection;
    }

    public void setBtsCellCollection(Collection<BtsCell> btsCellCollection) {
        this.btsCellCollection = btsCellCollection;
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
        if (!(object instanceof Btsf)) {
            return false;
        }
        Btsf other = (Btsf) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Btsf[ id=" + id + " ]";
    }
    
}
