package wang.jilijili.music.mapper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wang.jilijili.music.pojo.entity.Role;

import java.util.List;

/**
 * @author admin
 * @description 针对表【role(角色表)】的数据库操作Mapper
 * @createDate 2023-01-23 17:17:42
 * @Entity wang.jilijili.music.pojo.entity.Role
 */
@Repository
public interface RoleMapper extends JpaRepository<Role, String> {



}




