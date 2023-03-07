package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.vo.UserVo;
import wang.jilijili.music.service.UserService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void passwordTest() {
        String encode = passwordEncoder.encode("123456");
        boolean pass = passwordEncoder.matches("123456", "$2a$10$NuV9AwpJuzsxdaiYBBOTYOVlvNStVxcWHOV3RkkbKnlbLY5ADvflq");
        System.out.println(encode);
    }

    @Test
    void search() throws ParseException {
        IPage<User> page = new Page<>(1, 5);
        UserQueryDto userQueryDto = new UserQueryDto();

        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 2019-02-01 13:42:49.000000
        Date myDate1 = dateFormat2.parse("2016-02-01 22:36:01");
        Date myDate2 = dateFormat2.parse("2019-02-01 22:36:01");
        userQueryDto.setCreatedTime(myDate1);
        userQueryDto.setSpecifyTime(myDate2);
        userQueryDto.setUnseal(1);
        IPage<UserVo> search = this.userService.search(page, userQueryDto);
        System.out.println(search.getRecords());
    }
}