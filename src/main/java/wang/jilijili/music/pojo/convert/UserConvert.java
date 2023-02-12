package wang.jilijili.music.pojo.convert;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import wang.jilijili.music.pojo.dto.UserCreateDto;
import wang.jilijili.music.pojo.dto.UserDto;
import wang.jilijili.music.pojo.dto.UserQueryDto;
import wang.jilijili.music.pojo.entity.User;
import wang.jilijili.music.pojo.query.UserCreateRequest;
import wang.jilijili.music.pojo.query.UserUpdateRequest;
import wang.jilijili.music.pojo.vo.UserVo;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:39
 * @Description: User mapper to Dto or Vo or Bo structural
 */
@Mapper(componentModel = "spring")
@Component
public interface UserConvert {



    UserDto toDto(User user);

    UserVo toVo(UserDto userDto);

    User toUserEntity(UserCreateRequest userCreateRequest);
    User toUserEntity(UserUpdateRequest userUpdateRequest);

    User toUserEntity(UserCreateDto userCreateDto);

    User toUserEntity(UserQueryDto userQueryDto);




}
