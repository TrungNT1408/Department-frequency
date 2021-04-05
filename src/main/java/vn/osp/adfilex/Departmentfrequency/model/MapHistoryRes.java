package vn.osp.adfilex.Departmentfrequency.model;


import vn.osp.adfilex.Departmentfrequency.entity.Cell;

import java.util.Date;
import java.util.List;

public class MapHistoryRes {

    //id, date, reason, handler, solution
    private long id;
    private Date date;
    private String reason;
    private String handler;
    private String solution;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
