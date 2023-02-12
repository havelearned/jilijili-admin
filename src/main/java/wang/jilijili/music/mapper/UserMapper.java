package wang.jilijili.music.mapper;

import org.springframework.stereotype.Repository;
import wang.jilijili.music.pojo.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2023-02-12 15:32:36
* @Entity wang.jilijili.music.pojo.entity.User
*/
@Repository
public interface UserMapper extends BaseMapper<User> {

    User userRoles(String id);
}




