package wang.jilijili.music.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.ksuid.KsuidGenerator;
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
        // 2LrC8wCWcA8AGUV65OeLCJiQRAw
        // 2LrCBBMHDhbLHmdyzRZ8CBdfQc6
        System.out.println(KsuidGenerator.generate());
    }
}