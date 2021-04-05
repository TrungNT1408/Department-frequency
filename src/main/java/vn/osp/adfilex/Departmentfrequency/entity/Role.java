package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * 
 * vn.osp.adfilex.entity 
 * @author LuongTN : 9:17:29 AM
 * 
 * 
 * Role.java
 */
@Entity
@Table(name = "osp_role")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Role extends Auditable<String> implements Serializable {

  private static final long serialVersionUID = 2679118992937555761L;

  @Id
  @Column(name = "role_id")
  private Long roleId;

  private String description;

  @Column(name = "role_code", unique = true, nullable = false, length = 25,
      columnDefinition = "varchar(25)", updatable = false)
  private String roleCode;

  @Column(name = "role_name", unique = true, nullable = false, length = 50,
      columnDefinition = "nvarchar(50)", updatable = false)
  private String roleName;

  @Column(name = "status", nullable = false, updatable = false)
  private Integer status;

  @Column(name = "role_level", nullable = false, updatable = false)
  private Integer roleLevel;

  @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
  private List<UserRole> userRoleList;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_type_id", nullable = false)
  private RoleType roleType;

  public Role(Long roleId) {
    this.roleId = roleId;
  }

  public Role(Long roleId, String description, String roleCode, String roleName) {
    this.roleId = roleId;
    this.description = description;
    this.roleCode = roleCode;
    this.roleName = roleName;
  }

}
