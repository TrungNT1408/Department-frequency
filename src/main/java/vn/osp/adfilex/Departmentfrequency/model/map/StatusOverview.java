package vn.osp.adfilex.Departmentfrequency.model.map;

public class StatusOverview {
    private int total_active;
    private int total_interference_initiative;
    private int total_interference_passive;

    public int getTotal_active() {
        return total_active;
    }

    public void setTotal_active(int total_active) {
        this.total_active = total_active;
    }

    public int getTotal_interference_initiative() {
        return total_interference_initiative;
    }

    public void setTotal_interference_initiative(int total_interference_initiative) {
        this.total_interference_initiative = total_interference_initiative;
    }

    public int getTotal_interference_passive() {
        return total_interference_passive;
    }

    public void setTotal_interference_passive(int total_interference_passive) {
        this.total_interference_passive = total_interference_passive;
    }
}
