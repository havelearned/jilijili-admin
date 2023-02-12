package wang.jilijili.music.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.jilijili.music.pojo.entity.Role;
import wang.jilijili.music.pojo.entity.User;

import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void Query() {


        User user = userMapper.userRoles("00064bdf-0fd7-1");
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            System.out.println(role);

        }

    }
}