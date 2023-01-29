package wang.jilijili.music.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import wang.jilijili.music.pojo.entity.User;

import java.util.Optional;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2023-01-23 17:17:42
 * @Entity wang.jilijili.music.pojo.entity.User
 */
public interface UserMapper extends JpaRepository<User, String> {

    public abstract Optional<User> findUserByUsername(String username);

    public abstract  User findByUsername(String username);
}




