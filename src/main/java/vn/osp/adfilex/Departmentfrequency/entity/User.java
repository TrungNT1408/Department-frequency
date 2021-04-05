package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * vn.osp.adfilex.entity
 *
 * @author LuongTN : 9:18:27 AM
 *
 *
 * User.java
 */
@Entity
@Table(name = "osp_user")
@Setter
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class User extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 6174016284429444079L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String avatar;

    @Column(name = "phone_number"/* , unique = true */, length = 25)
    private String phoneNumber;

    @Column(name = "email"/* , unique = true */, length = 40)
    private String email;

    @Column(name = "username", unique = true, nullable = false, updatable = false, length = 25)
    private String username;

    @Column(name = "first_name", length = 25, columnDefinition = "nvarchar(25)", nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 25, columnDefinition = "nvarchar(25)", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "address", length = 255, columnDefinition = "nvarchar(255)")
    private String address;

    @Column(name = "create_by_user_id", nullable = false, updatable = false)
    private Long createByUserId;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<UserRole> userRoleList;

    @Transient
    private Double favDurationThreshold;

    @Transient
    private String checkDomain;

    @Transient
    private String checkPassword;

    public User(String username, String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
    }

    public User(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public User(Long userId, String username, Integer status,
            Date createdDate) {
        super();
        this.userId = userId;
        this.username = username;
        this.status = status;
        this.createdDate = createdDate;
    }

}
