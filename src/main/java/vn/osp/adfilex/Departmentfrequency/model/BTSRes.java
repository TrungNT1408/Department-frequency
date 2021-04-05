package vn.osp.adfilex.Departmentfrequency.model;


import java.util.List;

public class BTSRes {

    private String btsId;
    private Location location;
    private String province;
    private String telco;
    private List<String> techs;
    private double distance;
    private List<String> freqs;
//    private List<Cell> cells;



    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
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

    public List<String> getTechs() {
        return techs;
    }

    public void setTechs(List<String> techs) {
        this.techs = techs;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public List<String> getFreqs() {
        return freqs;
    }

    public void setFreqs(List<String> freqs) {
        this.freqs = freqs;
    }

    public String getBtsId() {
        return btsId;
    }

    public void setBtsId(String btsId) {
        this.btsId = btsId;
    }
}
