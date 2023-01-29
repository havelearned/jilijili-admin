package wang.jilijili.music.pojo.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @TableName role
 */
@EqualsAndHashCode(callSuper = true)
@Entity(name="role")
@Data
public class Role extends AbstractEntity implements Serializable {

    private String name;

    private String title;



    private static final long serialVersionUID = 1L;
}
