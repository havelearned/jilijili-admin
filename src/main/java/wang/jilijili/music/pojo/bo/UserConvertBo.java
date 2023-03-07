package wang.jilijili.music.pojo.bo;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.UserVo;

/**
 * @author admin
 * @Date: 2023/1/24 11:39
 * @Description: User mapper to Dto or Vo or Bo structural
 */
@Mapper(componentModel = "spring")
@Component
public interface UserConvertBo {


    /**
     * User 转 UserDto
     * @author Amani
     * @date 2023/3/5 11:31
     * @param user 实体类
     * @return wang.jilijili.music.pojo.dto.UserDto
     */
    UserDto toDto(User user);

    /**
     *  UserDto 转 UserVo
     * @author Amani
     * @date 2023/3/5 11:32
     * @param userDto  dto
     * @return UserVo
     */
    UserVo toVo(UserDto userDto);

    /**
     * UserUpdateRequest 转 User
     * @author Amani
     * @date 2023/3/5 11:34
     * @param userUpdateRequest
     * @return wang.jilijili.music.pojo.entity.User
     */
    User toUserEntity(UserUpdateRequest userUpdateRequest);


    /**
     * UserCreateDto 转 User
     * @author Amani
     * @date 2023/3/5 11:34
     * @param userCreateDto
     * @return wang.jilijili.music.pojo.entity.User
     */
    User toUserEntity(UserCreateDto userCreateDto);


}
