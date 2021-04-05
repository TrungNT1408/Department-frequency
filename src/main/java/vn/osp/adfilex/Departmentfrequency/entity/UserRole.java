package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * vn.osp.adfilex.entity 
 * @author LuongTN : 9:18:32 AM
 * 
 * 
 * UserRole.java
 */
@Entity
@Table(name = "osp_user_role",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class UserRole extends Auditable<String> implements Serializable {

  private static final long serialVersionUID = -793769159059711100L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_role_id")
  private Long userRoleId;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne
  @JoinColumn(name = "role_id", nullable = false)
  private Role role;

  public UserRole(Long userRoleId) {
    this.userRoleId = userRoleId;
  }

}
