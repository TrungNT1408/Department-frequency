package vn.osp.adfilex.Departmentfrequency.model;

import java.util.Date;

public class InterferenceMapRes {

    private String cellId;
    private String province;
    private String telco;
    private String tech;
    private String type_noise;
    private Date date;
    private String freq;
    private String num_day_noise;
    private double lat;
    private double lon;

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public String getType_noise() {
        return type_noise;
    }

    public void setType_noise(String type_noise) {
        this.type_noise = type_noise;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public String getNum_day_noise() {
        return num_day_noise;
    }

    public void setNum_day_noise(String num_day_noise) {
        this.num_day_noise = num_day_noise;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
