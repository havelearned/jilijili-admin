package wang.jilijili.music.pojo.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wang.jilijili.music.common.enums.Gender;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @TableName user
 */
@EqualsAndHashCode(callSuper = true)
@Entity(name = "user")
@Data
public class User extends AbstractEntity implements Serializable, UserDetails {


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;

    @Column(unique = true, name = "username")
    private String username;

    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer locked = 0;

    private Integer enabled = 1;

    private String lastLoginIp;

    private Date lastLoginTime;


    private static final long serialVersionUID = 1L;

    /**
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


//    /**
//     * @return
//     */
//    @Override
//    public boolean isEnabled() {
//        return this.enabled == 1;
//    }
}
