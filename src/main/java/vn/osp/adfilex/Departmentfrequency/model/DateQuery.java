/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.model;


import vn.osp.adfilex.Departmentfrequency.utils.GlobalFuncs;
import vn.osp.adfilex.Departmentfrequency.utils.OspStringUtils;

/**
 *
 * @author abc
 */
public class DateQuery implements IQuery{

    //yyyy-MM-dd
    String from;
    String to;

    public DateQuery() {
    }

    public DateQuery(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public boolean isValid() {
        return from != null && to != null && from.length() == 10 && to.length() == 10;
    }
    
 @Override
    public String build(String fieldName, boolean hasWhere) {
        final DateQuery d = this;
        StringBuilder sb = new StringBuilder();

        if (d == null) {
            return "";
        }

        if (d.getFrom() != null && GlobalFuncs.isValidDay(d.getFrom())) {
            if (hasWhere) {
                sb.append(" AND ");
            }
            hasWhere = true;
            sb.append(fieldName).append(" >= ")
                    .append("'").append(OspStringUtils.escapeSQL(d.getFrom())).append("'");

        }

        if (d.getTo() != null && GlobalFuncs.isValidDay(d.getTo())) {
            if (hasWhere) {
                sb.append(" AND ");
            }
            hasWhere = true;
            sb.append(fieldName).append(" <= ")
                    .append("'").append(OspStringUtils.escapeSQL(d.getTo())).append("'");

        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "DateQuery{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
