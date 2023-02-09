package wang.jilijili.music.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.service.UserService;

import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void getAllLoginUsers() {
        List<UserDto> allLoginUsers =
                userService.getAllLoginUsers();
        System.out.println(allLoginUsers);
    }
}