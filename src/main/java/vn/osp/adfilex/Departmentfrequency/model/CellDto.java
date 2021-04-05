package vn.osp.adfilex.Departmentfrequency.model;

import vn.osp.adfilex.Departmentfrequency.entity.Bts;
import vn.osp.adfilex.Departmentfrequency.entity.Cell;
import vn.osp.adfilex.Departmentfrequency.entity.CellNoise;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

public class CellDto {

    private String cellId;
    private String techType;
    private String freq;
    private String tilt;
    private String bcch;
    private String sc;
    private String lastUpdate;
    private Date startDate;
    private Date endDate;
    private String state;
    private String btsId;
    private int status = 0;

    private String hight;
    private String azimuth;
    private String numDayNoise;
    private String wat;
    private Double alpha;
    private Double sigma;


    public static CellDto fromCell(Cell cell) {
        CellDto cellDto = new CellDto();
        cellDto.setCellId(cell.getCellId());
        cellDto.setTechType(cell.getTechType());
        cellDto.setFreq(cell.getFreq());
        cellDto.setTilt(cell.getTilt());
        cellDto.setBcch(cell.getBcch());
        cellDto.setSc(cell.getSc());
        cellDto.setLastUpdate(cell.getLastUpdate());
        cellDto.setStartDate(cell.getStartDate());
        cellDto.setEndDate(cell.getEndDate());
        cellDto.setState(cell.getState());
        cellDto.setBtsId(cell.getBtsId().getBtsId());
        cellDto.setAzimuth(cell.getAzimuth().toString());
        return cellDto;
    }


    public static CellDto fromCellNoise(CellNoise cellNoise, Cell cell, Double alpha, Double sigma) {
        CellDto cellDto = new CellDto();
        cellDto.setCellId(cellNoise.getCellId());
        cellDto.setTechType(cell.getTechType());
        cellDto.setFreq(cell.getFreq());
        cellDto.setTilt(cell.getTilt());
        cellDto.setBcch(cell.getBcch());
        cellDto.setSc(cell.getSc());
        cellDto.setLastUpdate(cellNoise.getUpdateTime().toString());
        cellDto.setStartDate(cellNoise.getStartDate());
        cellDto.setEndDate(cellNoise.getEndDate());
        cellDto.setState(cell.getState());
        cellDto.setBtsId(cell.getBtsId().getBtsId());
        cellDto.setStatus(-1);
        cellDto.setAzimuth(cell.getAzimuth().toString());
        cellDto.setNumDayNoise(cellNoise.getNumDayNoise());
        cellDto.setWat(cellNoise.getWat());
        cellDto.setHight(cellNoise.getHight());
        cellDto.setAlpha(alpha);
        cellDto.setSigma(sigma);
        return cellDto;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getTechType() {
        return techType;
    }

    public void setTechType(String techType) {
        this.techType = techType;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getTilt() {
        return tilt;
    }

    public void setTilt(String tilt) {
        this.tilt = tilt;
    }

    public String getBcch() {
        return bcch;
    }

    public void setBcch(String bcch) {
        this.bcch = bcch;
    }

    public String getSc() {
        return sc;
    }

    public void setSc(String sc) {
        this.sc = sc;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getBtsId() {
        return btsId;
    }

    public void setBtsId(String btsId) {
        this.btsId = btsId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public void setSigma(Double sigma) {
        this.sigma = sigma;
    }

    public Double getSigma() {
        return sigma;
    }
}
