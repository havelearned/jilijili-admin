package wang.jilijili.common.core.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import wang.jilijili.common.core.pojo.entity.SysMenu;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.core.pojo.vo.UserVo;

import java.util.List;

/**
 * @author admin
 * @description 针对表【user(用户表)】的数据库操作Mapper
 * @createDate 2023-02-12 15:32:36
 * @Entity wang.jilijili.music.pojo.entity.User
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 通过id查询用户角色
     *
     * @param id
     * @return wang.jilijili.music.pojo.entity.User
     * @author Amani
     * @date 2023/3/5 11:49
     */
    User userRoles(String id);

    /**
     * 通过名称查询用户
     *
     * @param username
     * @return wang.jilijili.music.pojo.entity.User
     * @author Amani
     * @date 2023/3/5 11:49
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

    /**
     * 通过userId获取菜单
     *
     * @param userId
     * @return 菜单列表
     */
    List<SysMenu> getMenu(@Param("userId") String userId);

    List<UserVo> pageQuery(IPage<UserVo> pageEntity,@Param("ew") QueryWrapper<UserVo> queryWrapper);
}




