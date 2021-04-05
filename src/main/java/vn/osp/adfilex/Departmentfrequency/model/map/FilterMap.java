package vn.osp.adfilex.Departmentfrequency.model.map;

import java.io.Serializable;
import java.util.List;

public class FilterMap implements Serializable {

    private List<Integer> tech;
    private List<Integer> telco;
    private List<Integer> system;
    private List<Integer> status;
    private String province;

    private  String cellName;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public List<Integer> getTech() {
        return tech;
    }

    public void setTech(List<Integer> tech) {
        this.tech = tech;
    }

    public List<Integer> getTelco() {
        return telco;
    }

    public void setTelco(List<Integer> telco) {
        this.telco = telco;
    }

    public List<Integer> getSystem() {
        return system;
    }

    public void setSystem(List<Integer> system) {
        this.system = system;
    }

    public String getCellName() {
        return cellName;
    }

    public void setCellName(String cellName) {
        this.cellName = cellName;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "FilterMap{" +
                "tech=" + tech +
                ", telco=" + telco +
                ", system=" + system +
                ", status=" + status +
                ", cellName='" + cellName + '\'' +
                '}';
    }
}
