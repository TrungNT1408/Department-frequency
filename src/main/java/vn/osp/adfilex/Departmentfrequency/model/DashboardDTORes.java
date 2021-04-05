package vn.osp.adfilex.Departmentfrequency.model;

import java.util.Date;
import java.util.List;

public class DashboardDTORes {

    private String cellName;
    private String telco;
    private String province;
    private Double ul_rssi_max;
    private Double ul_rssi_mean;
    private String tech;
    private String freq;
    private long number_noise;
    private String num_notify;
    private Date date_notify;
    private Date date_expire;
    private String result;
    private Double lat;
    private Double lon;
    private String file_notify;

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getUl_rssi_max() {
        return ul_rssi_max;
    }

    public void setUl_rssi_max(Double ul_rssi_max) {
        this.ul_rssi_max = ul_rssi_max;
    }

    public Double getUl_rssi_mean() {
        return ul_rssi_mean;
    }

    public void setUl_rssi_mean(Double ul_rssi_mean) {
        this.ul_rssi_mean = ul_rssi_mean;
    }

    public long getNumber_noise() {
        return number_noise;
    }

    public void setNumber_noise(long number_noise) {
        this.number_noise = number_noise;
    }

    public String getNum_notify() {
        return num_notify;
    }

    public void setNum_notify(String num_notify) {
        this.num_notify = num_notify;
    }

    public Date getDate_notify() {
        return date_notify;
    }

    public void setDate_notify(Date date_notify) {
        this.date_notify = date_notify;
    }

    public Date getDate_expire() {
        return date_expire;
    }

    public void setDate_expire(Date date_expire) {
        this.date_expire = date_expire;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
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

    public String getFile_notify() {
        return file_notify;
    }

    public void setFile_notify(String file_notify) {
        this.file_notify = file_notify;
    }
}
