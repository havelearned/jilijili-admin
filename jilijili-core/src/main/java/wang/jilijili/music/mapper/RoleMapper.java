package wang.jilijili.music.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import wang.jilijili.music.pojo.entity.Role;

/**
 * @author admin
 * @description 针对表【role(角色表)】的数据库操作Mapper
 * @createDate 2023-02-12 15:32:36
 * @Entity wang.jilijili.music.pojo.entity.Role
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {
}




