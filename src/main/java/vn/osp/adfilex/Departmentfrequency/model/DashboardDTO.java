package vn.osp.adfilex.Departmentfrequency.model;

import java.util.List;

public class DashboardDTO {

    private String province;
    private List<Integer> telcos;
    private List<Integer> techs;
    private List<Integer> freqs;
    private Double ul_rssi_max;
    private Double ul_rssi_min;
    private Double ul_rssi_mean_from;
    private Double ul_rssi_mean_to;
    private int freq_noise;
    private int status;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }


    public List<Integer> getTelcos() {
        return telcos;
    }

    public void setTelcos(List<Integer> telcos) {
        this.telcos = telcos;
    }

    public List<Integer> getTechs() {
        return techs;
    }

    public void setTechs(List<Integer> techs) {
        this.techs = techs;
    }

    public List<Integer> getFreqs() {
        return freqs;
    }

    public void setFreqs(List<Integer> freqs) {
        this.freqs = freqs;
    }

    public Double getUl_rssi_max() {
        return ul_rssi_max;
    }

    public void setUl_rssi_max(Double ul_rssi_max) {
        this.ul_rssi_max = ul_rssi_max;
    }

    public Double getUl_rssi_min() {
        return ul_rssi_min;
    }

    public void setUl_rssi_min(Double ul_rssi_min) {
        this.ul_rssi_min = ul_rssi_min;
    }

    public Double getUl_rssi_mean_from() {
        return ul_rssi_mean_from;
    }

    public void setUl_rssi_mean_from(Double ul_rssi_mean_from) {
        this.ul_rssi_mean_from = ul_rssi_mean_from;
    }

    public Double getUl_rssi_mean_to() {
        return ul_rssi_mean_to;
    }

    public void setUl_rssi_mean_to(Double ul_rssi_mean_to) {
        this.ul_rssi_mean_to = ul_rssi_mean_to;
    }

    public int getFreq_noise() {
        return freq_noise;
    }

    public void setFreq_noise(int freq_noise) {
        this.freq_noise = freq_noise;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
