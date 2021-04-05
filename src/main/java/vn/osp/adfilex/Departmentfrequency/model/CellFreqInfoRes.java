package vn.osp.adfilex.Departmentfrequency.model;

import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.CellFreq;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

public class CellFreqInfoRes {
    private Long id;
    private String cellId;
    private String cellName;
    private Double rtwp;
    private Integer hour;
    private String dateTime;
    private String ucellId;
    private String uarfcndl;
    private String rbsId;
    private Date createTime;

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


    public static CellFreqInfoRes toCellFreqInfoRes(CellFreq cellFreq){
        CellFreqInfoRes cellFreqInfoRes = new CellFreqInfoRes() ;
        cellFreqInfoRes.setId(cellFreq.getId());
        cellFreqInfoRes.setCellId(cellFreq.getCellId());
        cellFreqInfoRes.setCellName(cellFreq.getCellName());
        cellFreqInfoRes.setRtwp(cellFreq.getRtwp()-13);
        cellFreqInfoRes.setHour(cellFreq.getHour());
        cellFreqInfoRes.setDateTime(cellFreq.getDateTime());
        cellFreqInfoRes.setUcellId(cellFreq.getUcellId());
        cellFreqInfoRes.setUarfcndl(cellFreq.getUarfcndl());
        cellFreqInfoRes.setRbsId(cellFreq.getRbsId());
        cellFreqInfoRes.setCreateTime(cellFreq.getCreateTime());

        return cellFreqInfoRes;
    }
}
