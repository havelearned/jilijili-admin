package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.jilijili.music.mapper.RoleMapper;
import wang.jilijili.music.pojo.entity.Role;
import wang.jilijili.music.service.RoleService;

/**
* @author admin
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2023-02-12 15:32:36
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




