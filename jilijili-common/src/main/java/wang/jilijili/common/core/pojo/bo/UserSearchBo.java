package wang.jilijili.common.core.pojo.bo;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.util.StringUtils;
import wang.jilijili.common.core.pojo.dto.UserQueryDto;
import wang.jilijili.common.core.pojo.entity.User;

/**
 * 为用户的查询业务条件封装
 *
 * @author Amani
 * @date 2023年03月02日 10:48
 */
public class UserSearchBo {

    /**
     * @param userQueryDto 查询条件构造
     * @return LambdaQueryWrapper<User>
     */
    public static LambdaQueryWrapper<User> getUserLambdaQueryWrapper(UserQueryDto userQueryDto) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StringUtils.hasText(userQueryDto.getNickname()), User::getNickname, userQueryDto.getNickname().trim())
                .like(StringUtils.hasText(userQueryDto.getUsername()), User::getUsername, userQueryDto.getUsername().trim())
                .like(StringUtils.hasText(userQueryDto.getGender()), User::getGender, userQueryDto.getGender())
                .eq(StringUtils.hasText(userQueryDto.getId()), User::getId, userQueryDto.getId().trim())
                .eq(userQueryDto.getUnseal() != null, User::getUnseal, userQueryDto.getUnseal())
                .between(userQueryDto.getSpecifyTime() != null && userQueryDto.getCreatedTime() != null,
                        User::getCreatedTime, userQueryDto.getCreatedTime(), userQueryDto.getSpecifyTime())
                .orderBy(true, false, User::getCreatedTime);
        return queryWrapper;
    }


}
