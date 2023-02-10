package wang.jilijili.music.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import java.io.Serializable;

/**
 * @TableName user_role
 */
@EqualsAndHashCode(callSuper = true)
@Entity(name = "user_role")
@Data
public class UserRole extends AbstractEntity implements Serializable {

  @Column(name = "user_id")
  private String userId;

  @Column(name = "role_id")
  private String roleId;

  private static final long serialVersionUID = 1L;
}
