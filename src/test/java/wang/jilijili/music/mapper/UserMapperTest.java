package wang.jilijili.music.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.jilijili.music.pojo.entity.User;

/**
 * @Auther: Amani
 * @Date: 2023/1/23 19:05
 * @Description:
 */
@SpringBootTest
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void findUserByUsername() {

    }
}
