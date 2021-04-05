package vn.osp.adfilex.Departmentfrequency.model;


import vn.osp.adfilex.Departmentfrequency.model.map.*;

public class MapOverviewRes {

    private TechOverview techOverview;
    private TelcoOverview telcoOverview;
    private StatusOverview statusOverview;
    private SystemOverview systemOverview;

    public TechOverview getTechOverview() {
        return techOverview;
    }

    public void setTechOverview(TechOverview techOverview) {
        this.techOverview = techOverview;
    }

    public TelcoOverview getTelcoOverview() {
        return telcoOverview;
    }

    public void setTelcoOverview(TelcoOverview telcoOverview) {
        this.telcoOverview = telcoOverview;
    }

    public StatusOverview getStatusOverview() {
        return statusOverview;
    }

    public void setStatusOverview(StatusOverview statusOverview) {
        this.statusOverview = statusOverview;
    }

    public SystemOverview getSystemOverview() {
        return systemOverview;
    }

    public void setSystemOverview(SystemOverview systemOverview) {
        this.systemOverview = systemOverview;
    }
}
