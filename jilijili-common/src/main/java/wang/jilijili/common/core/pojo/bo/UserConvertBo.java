package wang.jilijili.common.core.pojo.bo;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import wang.jilijili.common.core.pojo.dto.UserCreateDto;
import wang.jilijili.common.core.pojo.dto.UserDto;
import wang.jilijili.common.core.pojo.dto.UserQueryDto;
import wang.jilijili.common.core.pojo.entity.User;
import wang.jilijili.common.core.pojo.request.UserUpdateRequest;
import wang.jilijili.common.core.pojo.vo.UserVo;

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
     * 转VO
     * @param userQueryDto
     * @return vo
     */
    UserVo toVo(UserQueryDto userQueryDto);

    /**
     * UserUpdateRequest 转 User
     * @author Amani
     * @date 2023/3/5 11:34
     * @param userUpdateRequest userUpdateRequest
     * @return wang.jilijili.music.pojo.entity.User
     */
    User toUserEntity(UserUpdateRequest userUpdateRequest);


    /**
     * UserCreateDto 转 User
     * @author Amani
     * @date 2023/3/5 11:34
     * @param userCreateDto userCreateDto
     * @return wang.jilijili.music.pojo.entity.User
     */
    User toUserEntity(UserCreateDto userCreateDto);

    /**
     * 转 entity
     * @param userQueryDto
     * @return entity
     */
    User toUser(UserQueryDto userQueryDto);


}
