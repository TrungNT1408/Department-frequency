package vn.osp.adfilex.Departmentfrequency.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author LuongTN : 9:17:34 AM
 * 
 * 
 * RoleType.java
 */
@Entity
@Table(name = "osp_role_type")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoleType extends Auditable<String> implements Serializable {

  private static final long serialVersionUID = 2679118992937555761L;

  @Id
  @Column(name = "role_type_id")
  private Long roleTypeId;

  private String description;

  @Column(name = "role_type_code", unique = true, nullable = false, length = 25,
      columnDefinition = "varchar(25)")
  private String roleTypeCode;

  @Column(name = "role_type_name", unique = true, nullable = false, length = 50,
      columnDefinition = "nvarchar(50)")
  private String roleTypeName;

  @OneToMany(mappedBy = "roleType")
  private List<Role> roleList;

}
