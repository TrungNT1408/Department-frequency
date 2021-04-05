package vn.osp.adfilex.Departmentfrequency.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SEND_EMAIL")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder

public class SendEmail
        implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id

    @Basic(optional = false)
    @NotNull
    @Column(name = "SEND_EMAIL_ID")
    private Long sendEmailId;
    @Column(name = "SEND_USER_ID")
    private Long sendUserId;
    @Size(max = 250)
    @Column(name = "SEND_USER_NAME")
    private String sendUserName;
    @Column(name = "OBJECT_ID")
    private Long objectId;
    @Column(name = "OBJECT_TYPE")
    private Long objectType;
    @Column(name = "CREATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "SEND_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    @Size(max = 500)
    @Column(name = "TITLE")
    private String title;
    @Size(max = 4000)
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STATUS")
    private Long status;
    @Column(name = "IS_ACTIVE")
    private Long isActive;
    @Column(name = "RECEIVE_USER_ID")
    private Long receiveUserId;
    @Column(name = "RECEIVE_DEPT_ID")
    private Long receiveDeptId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIME_RETRY")
    private short timeRetry;
    @Column(name = "EMAIL_CONFIG_ID")
    private Long emailConfigId;

    @Column(name = "IS_READ")
    private Short isRead;

    @Size(max = 100)
    @Column(name = "ACTION_NAME")
    private String actionName;
}
