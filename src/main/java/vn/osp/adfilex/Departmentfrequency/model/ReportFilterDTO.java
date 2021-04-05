/**
 * Welcome developer friend. PC ospAdfilex-smartTeleSale-service DataAccountExport.java 5:14:11 PM
 */
package vn.osp.adfilex.Departmentfrequency.model;

import java.util.Date;

public class ReportFilterDTO {
    private String organisation;
    private String province;
    private String tech;
    private String telco;
    private Date from;
    private Date to;

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getTech() {
        return tech;
    }

    public void setTech(String tech) {
        this.tech = tech;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getTelco() {
        return telco;
    }

    public void setTelco(String telco) {
        this.telco = telco;
    }
}
