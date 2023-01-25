package wang.jilijili.music.pojo.entity;

import jakarta.persistence.*;
import lombok.Data;
import wang.jilijili.music.common.enums.Gender;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @TableName user
 */
@Entity(name = "user")
@Data
public class User extends AbstractEntity implements Serializable {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Column(unique = true, name = "username")
    private String username;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer locked;

    private Integer enabled;

    private String lastLoginIp;

    private Date lastLoginTime;


    private static final long serialVersionUID = 1L;
}
