/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "bts_cell")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BtsCell.findAll", query = "SELECT b FROM BtsCell b"),
    @NamedQuery(name = "BtsCell.findById", query = "SELECT b FROM BtsCell b WHERE b.id = :id"),
    @NamedQuery(name = "BtsCell.findByCellId", query = "SELECT b FROM BtsCell b WHERE b.cellId = :cellId")})
public class BtsCell implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "cell_id")
    private BigInteger cellId;
    @JoinColumn(name = "bts_id", referencedColumnName = "id")
    @ManyToOne
    private Btsf btsId;

    public BtsCell() {
    }

    public BtsCell(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getCellId() {
        return cellId;
    }

    public void setCellId(BigInteger cellId) {
        this.cellId = cellId;
    }

    public Btsf getBtsId() {
        return btsId;
    }

    public void setBtsId(Btsf btsId) {
        this.btsId = btsId;
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
        if (!(object instanceof BtsCell)) {
            return false;
        }
        BtsCell other = (BtsCell) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.BtsCell[ id=" + id + " ]";
    }
    
}
