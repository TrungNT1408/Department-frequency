package vn.osp.adfilex.Departmentfrequency.model.map;

import vn.osp.adfilex.Departmentfrequency.constants.ArfmConstants;

public class BtsCellMapInfo {

    private Double ul_rssi = null; // xanh
    private double azimuth;
    private double freq;

    public Double getUl_rssi() {
        return ul_rssi;
    }

    public void setUl_rssi(Double ul_rssi) {
        this.ul_rssi = ul_rssi;
    }

    public double getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(double azimuth) {
        this.azimuth = azimuth;
    }

    public double getFreq() {
        return freq;
    }

    public void setFreq(double freq) {
        this.freq = freq;
    }
}
