/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abc
 */
@Entity
@Table(name = "cell_noise_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CellNoiseInfo.findAll", query = "SELECT c FROM CellNoiseInfo c"),
    @NamedQuery(name = "CellNoiseInfo.findById", query = "SELECT c FROM CellNoiseInfo c WHERE c.id = :id"),
    @NamedQuery(name = "CellNoiseInfo.findByLicenseNumber", query = "SELECT c FROM CellNoiseInfo c WHERE c.licenseNumber = :licenseNumber"),
    @NamedQuery(name = "CellNoiseInfo.findByOrganisationName", query = "SELECT c FROM CellNoiseInfo c WHERE c.organisationName = :organisationName"),
    @NamedQuery(name = "CellNoiseInfo.findByOrganisation", query = "SELECT c FROM CellNoiseInfo c WHERE c.organisation = :organisation"),
    @NamedQuery(name = "CellNoiseInfo.findByPhoneNumber", query = "SELECT c FROM CellNoiseInfo c WHERE c.phoneNumber = :phoneNumber"),
    @NamedQuery(name = "CellNoiseInfo.findByMobilePhone", query = "SELECT c FROM CellNoiseInfo c WHERE c.mobilePhone = :mobilePhone"),
    @NamedQuery(name = "CellNoiseInfo.findByEmail", query = "SELECT c FROM CellNoiseInfo c WHERE c.email = :email"),
    @NamedQuery(name = "CellNoiseInfo.findByRadioName", query = "SELECT c FROM CellNoiseInfo c WHERE c.radioName = :radioName"),
    @NamedQuery(name = "CellNoiseInfo.findByFreqInterf", query = "SELECT c FROM CellNoiseInfo c WHERE c.freqInterf = :freqInterf"),
    @NamedQuery(name = "CellNoiseInfo.findByLocaltion", query = "SELECT c FROM CellNoiseInfo c WHERE c.localtion = :localtion"),
    @NamedQuery(name = "CellNoiseInfo.findByDirectionRanger", query = "SELECT c FROM CellNoiseInfo c WHERE c.directionRanger = :directionRanger"),
    @NamedQuery(name = "CellNoiseInfo.findByTimeInterf", query = "SELECT c FROM CellNoiseInfo c WHERE c.timeInterf = :timeInterf"),
    @NamedQuery(name = "CellNoiseInfo.findByHourInterf", query = "SELECT c FROM CellNoiseInfo c WHERE c.hourInterf = :hourInterf"),
    @NamedQuery(name = "CellNoiseInfo.findByLevelAffect", query = "SELECT c FROM CellNoiseInfo c WHERE c.levelAffect = :levelAffect"),
    @NamedQuery(name = "CellNoiseInfo.findByPhenomenaInterf", query = "SELECT c FROM CellNoiseInfo c WHERE c.phenomenaInterf = :phenomenaInterf"),
    @NamedQuery(name = "CellNoiseInfo.findByPhenomenaDesc", query = "SELECT c FROM CellNoiseInfo c WHERE c.phenomenaDesc = :phenomenaDesc"),
    @NamedQuery(name = "CellNoiseInfo.findByAddInfo", query = "SELECT c FROM CellNoiseInfo c WHERE c.addInfo = :addInfo"),
    @NamedQuery(name = "CellNoiseInfo.findByResonFreqInterf", query = "SELECT c FROM CellNoiseInfo c WHERE c.resonFreqInterf = :resonFreqInterf"),
    @NamedQuery(name = "CellNoiseInfo.findByIdentCauseRadio", query = "SELECT c FROM CellNoiseInfo c WHERE c.identCauseRadio = :identCauseRadio"),
    @NamedQuery(name = "CellNoiseInfo.findByLocationRadioCause", query = "SELECT c FROM CellNoiseInfo c WHERE c.locationRadioCause = :locationRadioCause"),
    @NamedQuery(name = "CellNoiseInfo.findByTimeStatistic", query = "SELECT c FROM CellNoiseInfo c WHERE c.timeStatistic = :timeStatistic"),
    @NamedQuery(name = "CellNoiseInfo.findByProvince", query = "SELECT c FROM CellNoiseInfo c WHERE c.province = :province"),
    @NamedQuery(name = "CellNoiseInfo.findByCellId", query = "SELECT c FROM CellNoiseInfo c WHERE c.cellId = :cellId"),
    @NamedQuery(name = "CellNoiseInfo.findByFreq", query = "SELECT c FROM CellNoiseInfo c WHERE c.freq = :freq"),
    @NamedQuery(name = "CellNoiseInfo.findByLon", query = "SELECT c FROM CellNoiseInfo c WHERE c.lon = :lon"),
    @NamedQuery(name = "CellNoiseInfo.findByLat", query = "SELECT c FROM CellNoiseInfo c WHERE c.lat = :lat"),
    @NamedQuery(name = "CellNoiseInfo.findByAzimuth", query = "SELECT c FROM CellNoiseInfo c WHERE c.azimuth = :azimuth"),
    @NamedQuery(name = "CellNoiseInfo.findByRtwp", query = "SELECT c FROM CellNoiseInfo c WHERE c.rtwp = :rtwp"),
    @NamedQuery(name = "CellNoiseInfo.findByNumDayInterf", query = "SELECT c FROM CellNoiseInfo c WHERE c.numDayInterf = :numDayInterf"),
    @NamedQuery(name = "CellNoiseInfo.findByCreateDate", query = "SELECT c FROM CellNoiseInfo c WHERE c.createDate = :createDate"),
    @NamedQuery(name = "CellNoiseInfo.findByStatus", query = "SELECT c FROM CellNoiseInfo c WHERE c.status = :status"),
    @NamedQuery(name = "CellNoiseInfo.findByCauseInfo", query = "SELECT c FROM CellNoiseInfo c WHERE c.causeInfo = :causeInfo"),
    @NamedQuery(name = "CellNoiseInfo.findByProcessInfo", query = "SELECT c FROM CellNoiseInfo c WHERE c.processInfo = :processInfo"),
    @NamedQuery(name = "CellNoiseInfo.findByFiles", query = "SELECT c FROM CellNoiseInfo c WHERE c.files = :files")})
public class CellNoiseInfo implements Serializable {
    @Size(max = 100)
    @Column(name = "num_request")
    private String numRequest;
    @Size(max = 250)
    @Column(name = "request_file")
    private String requestFile;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Size(max = 100)
    @Column(name = "license_number")
    private String licenseNumber;
    @Size(max = 100)
    @Column(name = "organisation_name")
    private String organisationName;
    @Size(max = 100)
    @Column(name = "organisation")
    private String organisation;
    @Size(max = 100)
    @Column(name = "phone_number")
    private String phoneNumber;
    @Size(max = 100)
    @Column(name = "mobile_phone")
    private String mobilePhone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;
    @Size(max = 100)
    @Column(name = "radio_name")
    private String radioName;
    @Size(max = 100)
    @Column(name = "freq_interf")
    private String freqInterf;
    @Size(max = 100)
    @Column(name = "localtion")
    private String localtion;
    @Size(max = 100)
    @Column(name = "direction_ranger")
    private String directionRanger;
    @Size(max = 100)
    @Column(name = "time_interf")
    private String timeInterf;
    @Size(max = 100)
    @Column(name = "hour_interf")
    private String hourInterf;
    @Size(max = 100)
    @Column(name = "level_affect")
    private String levelAffect;
    @Size(max = 100)
    @Column(name = "phenomena_interf")
    private String phenomenaInterf;
    @Size(max = 100)
    @Column(name = "phenomena_desc")
    private String phenomenaDesc;
    @Size(max = 100)
    @Column(name = "add_info")
    private String addInfo;
    @Size(max = 100)
    @Column(name = "reson_freq_interf")
    private String resonFreqInterf;
    @Size(max = 100)
    @Column(name = "ident_cause_radio")
    private String identCauseRadio;
    @Size(max = 100)
    @Column(name = "location_radio_cause")
    private String locationRadioCause;
    @Size(max = 100)
    @Column(name = "time_statistic")
    private String timeStatistic;
    @Size(max = 100)
    @Column(name = "province")
    private String province;
    @Size(max = 100)
    @Column(name = "cell_id")
    private String cellId;
    @Size(max = 100)
    @Column(name = "freq")
    private String freq;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;
    @Size(max = 100)
    @Column(name = "azimuth")
    private String azimuth;
    @Size(max = 100)
    @Column(name = "rtwp")
    private String rtwp;
    @Size(max = 100)
    @Column(name = "num_day_interf")
    private String numDayInterf;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Size(max = 45)
    @Column(name = "status")
    private String status;
    @Size(max = 500)
    @Column(name = "cause_info")
    private String causeInfo;
    @Size(max = 500)
    @Column(name = "process_info")
    private String processInfo;
    @Size(max = 1000)
    @Column(name = "files")
    private String files;
    @OneToMany(mappedBy = "cellNoiseInfoId")
    private Collection<CellNoise> cellNoiseCollection;

    public CellNoiseInfo() {
    }

    public CellNoiseInfo(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRadioName() {
        return radioName;
    }

    public void setRadioName(String radioName) {
        this.radioName = radioName;
    }

    public String getFreqInterf() {
        return freqInterf;
    }

    public void setFreqInterf(String freqInterf) {
        this.freqInterf = freqInterf;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public String getDirectionRanger() {
        return directionRanger;
    }

    public void setDirectionRanger(String directionRanger) {
        this.directionRanger = directionRanger;
    }

    public String getTimeInterf() {
        return timeInterf;
    }

    public void setTimeInterf(String timeInterf) {
        this.timeInterf = timeInterf;
    }

    public String getHourInterf() {
        return hourInterf;
    }

    public void setHourInterf(String hourInterf) {
        this.hourInterf = hourInterf;
    }

    public String getLevelAffect() {
        return levelAffect;
    }

    public void setLevelAffect(String levelAffect) {
        this.levelAffect = levelAffect;
    }

    public String getPhenomenaInterf() {
        return phenomenaInterf;
    }

    public void setPhenomenaInterf(String phenomenaInterf) {
        this.phenomenaInterf = phenomenaInterf;
    }

    public String getPhenomenaDesc() {
        return phenomenaDesc;
    }

    public void setPhenomenaDesc(String phenomenaDesc) {
        this.phenomenaDesc = phenomenaDesc;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    public String getResonFreqInterf() {
        return resonFreqInterf;
    }

    public void setResonFreqInterf(String resonFreqInterf) {
        this.resonFreqInterf = resonFreqInterf;
    }

    public String getIdentCauseRadio() {
        return identCauseRadio;
    }

    public void setIdentCauseRadio(String identCauseRadio) {
        this.identCauseRadio = identCauseRadio;
    }

    public String getLocationRadioCause() {
        return locationRadioCause;
    }

    public void setLocationRadioCause(String locationRadioCause) {
        this.locationRadioCause = locationRadioCause;
    }

    public String getTimeStatistic() {
        return timeStatistic;
    }

    public void setTimeStatistic(String timeStatistic) {
        this.timeStatistic = timeStatistic;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCellId() {
        return cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getAzimuth() {
        return azimuth;
    }

    public void setAzimuth(String azimuth) {
        this.azimuth = azimuth;
    }

    public String getRtwp() {
        return rtwp;
    }

    public void setRtwp(String rtwp) {
        this.rtwp = rtwp;
    }

    public String getNumDayInterf() {
        return numDayInterf;
    }

    public void setNumDayInterf(String numDayInterf) {
        this.numDayInterf = numDayInterf;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCauseInfo() {
        return causeInfo;
    }

    public void setCauseInfo(String causeInfo) {
        this.causeInfo = causeInfo;
    }

    public String getProcessInfo() {
        return processInfo;
    }

    public void setProcessInfo(String processInfo) {
        this.processInfo = processInfo;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    @XmlTransient
    public Collection<CellNoise> getCellNoiseCollection() {
        return cellNoiseCollection;
    }

    public void setCellNoiseCollection(Collection<CellNoise> cellNoiseCollection) {
        this.cellNoiseCollection = cellNoiseCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CellNoiseInfo)) {
            return false;
        }
        CellNoiseInfo other = (CellNoiseInfo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.CellNoiseInfo[ id=" + id + " ]";
    }

    public String getNumRequest() {
        return numRequest;
    }

    public void setNumRequest(String numRequest) {
        this.numRequest = numRequest;
    }

    public String getRequestFile() {
        return requestFile;
    }

    public void setRequestFile(String requestFile) {
        this.requestFile = requestFile;
    }
    
}
