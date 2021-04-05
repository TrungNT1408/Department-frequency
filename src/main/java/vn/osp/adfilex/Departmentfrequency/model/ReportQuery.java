package vn.osp.adfilex.Departmentfrequency.model;

public class ReportQuery {

    private String province;
    private int telco;
    private int tech;
    private DateQuery time_from;


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getTelco() {
        return telco;
    }

    public void setTelco(int telco) {
        this.telco = telco;
    }

    public int getTech() {
        return tech;
    }

    public void setTech(int tech) {
        this.tech = tech;
    }

    public DateQuery getTime_from() {
        return time_from;
    }

    public void setTime_from(DateQuery time_from) {
        this.time_from = time_from;
    }
}
