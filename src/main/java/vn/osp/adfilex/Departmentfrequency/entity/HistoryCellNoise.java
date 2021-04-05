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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "history_cell_noise")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HistoryCellNoise.findAll", query = "SELECT h FROM HistoryCellNoise h"),
    @NamedQuery(name = "HistoryCellNoise.findById", query = "SELECT h FROM HistoryCellNoise h WHERE h.id = :id"),
    @NamedQuery(name = "HistoryCellNoise.findByCellId", query = "SELECT h FROM HistoryCellNoise h WHERE h.cellId = :cellId"),
    @NamedQuery(name = "HistoryCellNoise.findByAction", query = "SELECT h FROM HistoryCellNoise h WHERE h.action = :action"),
    @NamedQuery(name = "HistoryCellNoise.findByCreateDate", query = "SELECT h FROM HistoryCellNoise h WHERE h.createDate = :createDate"),
    @NamedQuery(name = "HistoryCellNoise.findByProcessor", query = "SELECT h FROM HistoryCellNoise h WHERE h.processor = :processor"),
    @NamedQuery(name = "HistoryCellNoise.findByNoiseCause", query = "SELECT h FROM HistoryCellNoise h WHERE h.noiseCause = :noiseCause"),
    @NamedQuery(name = "HistoryCellNoise.findBySolution", query = "SELECT h FROM HistoryCellNoise h WHERE h.solution = :solution")})
public class HistoryCellNoise implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "cell_id")
    private String cellId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "action")
    private String action;
    @Basic(optional = false)
    @NotNull
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 64)
    @Column(name = "processor")
    private String processor;
    @Size(max = 200)
    @Column(name = "noise_cause")
    private String noiseCause;
    @Size(max = 200)
    @Column(name = "solution")
    private String solution;

    public HistoryCellNoise() {
    }

    public HistoryCellNoise(Long id) {
        this.id = id;
    }

    public HistoryCellNoise(Long id, String cellId, String action, Date createDate) {
        this.id = id;
        this.cellId = cellId;
        this.action = action;
        this.createDate = createDate;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getNoiseCause() {
        return noiseCause;
    }

    public void setNoiseCause(String noiseCause) {
        this.noiseCause = noiseCause;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
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
        if (!(object instanceof HistoryCellNoise)) {
            return false;
        }
        HistoryCellNoise other = (HistoryCellNoise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.HistoryCellNoise[ id=" + id + " ]";
    }
    
}
