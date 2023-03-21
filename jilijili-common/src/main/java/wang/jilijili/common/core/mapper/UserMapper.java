package wang.jilijili.common.core.mapper;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.jilijili.common.core.pojo.entity.User;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2023-02-12 15:32:36
 * @Entity wang.jilijili.music.pojo.entity.User
 */
@DS("master")
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过id查询用户角色
     * @author Amani
     * @date 2023/3/5 11:49
     * @param id
     * @return wang.jilijili.music.pojo.entity.User
     */
    User userRoles(String id);

    /**
     * 通过名称查询用户
     * @author Amani
     * @date 2023/3/5 11:49
     * @param username
     * @return wang.jilijili.music.pojo.entity.User
     */
    User getUserByUsername(String username);

    /**
     * 添加用户时初始化角色
     *
     * @param userId
     * @param roleId
     * @author Amani
     * @date 2023/3/5 11:50
     */
    void initUserRole(@Param("userId") String userId, @Param("roleId") String roleId);
}




