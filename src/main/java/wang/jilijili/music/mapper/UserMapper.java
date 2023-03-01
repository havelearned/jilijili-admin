package wang.jilijili.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import wang.jilijili.music.pojo.entity.User;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2023-02-12 15:32:36
 * @Entity wang.jilijili.music.pojo.entity.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {

    User userRoles(String id);

    User getUserByUsername(String username);

    int initUserRole(@Param("userId") String userId, @Param("roleId") String roleId);
}




