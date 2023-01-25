package wang.jilijili.music.pojo.entity;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

/**
 * @TableName role
 */
@Entity(name="role")
@Data
public class Role extends AbstractEntity implements Serializable {

    private String name;

    private String title;



    private static final long serialVersionUID = 1L;
}
