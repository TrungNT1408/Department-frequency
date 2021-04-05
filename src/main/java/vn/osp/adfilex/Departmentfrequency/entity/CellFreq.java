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
@Table(name = "cell_freq")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CellFreq.findAll", query = "SELECT c FROM CellFreq c"),
    @NamedQuery(name = "CellFreq.findById", query = "SELECT c FROM CellFreq c WHERE c.id = :id"),
    @NamedQuery(name = "CellFreq.findByCellId", query = "SELECT c FROM CellFreq c WHERE c.cellId = :cellId"),
    @NamedQuery(name = "CellFreq.findByCellName", query = "SELECT c FROM CellFreq c WHERE c.cellName = :cellName"),
    @NamedQuery(name = "CellFreq.findByRtwp", query = "SELECT c FROM CellFreq c WHERE c.rtwp = :rtwp"),
    @NamedQuery(name = "CellFreq.findByHour", query = "SELECT c FROM CellFreq c WHERE c.hour = :hour"),
    @NamedQuery(name = "CellFreq.findByDateTime", query = "SELECT c FROM CellFreq c WHERE c.dateTime = :dateTime"),
    @NamedQuery(name = "CellFreq.findByUcellId", query = "SELECT c FROM CellFreq c WHERE c.ucellId = :ucellId"),
    @NamedQuery(name = "CellFreq.findByUarfcndl", query = "SELECT c FROM CellFreq c WHERE c.uarfcndl = :uarfcndl"),
    @NamedQuery(name = "CellFreq.findByRbsId", query = "SELECT c FROM CellFreq c WHERE c.rbsId = :rbsId"),
    @NamedQuery(name = "CellFreq.findByCreateTime", query = "SELECT c FROM CellFreq c WHERE c.createTime = :createTime")})
public class CellFreq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 45)
    @Column(name = "cell_id")
    private String cellId;
    @Size(max = 45)
    @Column(name = "cell_name")
    private String cellName;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "rtwp")
    private Double rtwp;
    @Column(name = "hour")
    private Integer hour;
    @Size(max = 45)
    @Column(name = "date_time")
    private String dateTime;
    @Size(max = 45)
    @Column(name = "ucell_Id")
    private String ucellId;
    @Size(max = 45)
    @Column(name = "uarfcndl")
    private String uarfcndl;
    @Size(max = 45)
    @Column(name = "rbs_id")
    private String rbsId;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public CellFreq() {
    }

    public CellFreq(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public Double getRtwp() {
        return rtwp;
    }

    public void setRtwp(Double rtwp) {
        this.rtwp = rtwp;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getUcellId() {
        return ucellId;
    }

    public void setUcellId(String ucellId) {
        this.ucellId = ucellId;
    }

    public String getUarfcndl() {
        return uarfcndl;
    }

    public void setUarfcndl(String uarfcndl) {
        this.uarfcndl = uarfcndl;
    }

    public String getRbsId() {
        return rbsId;
    }

    public void setRbsId(String rbsId) {
        this.rbsId = rbsId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
        if (!(object instanceof CellFreq)) {
            return false;
        }
        CellFreq other = (CellFreq) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CellFreq[ id=" + id + " ]";
    }
    
}
