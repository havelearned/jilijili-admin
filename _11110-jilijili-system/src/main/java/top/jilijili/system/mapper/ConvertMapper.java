package top.jilijili.system.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.*;
import top.jilijili.module.entity.dto.*;
import top.jilijili.module.entity.vo.*;

/**
 * @author Amani
 * @date 2023年06月22日 13:41
 */
@Mapper(componentModel = "spring")
@Component
public interface ConvertMapper {
    /*-----------------system----------------*/
    SysUser toSysUserEnetity(SysUserDto sysUserDto);

    SysUserVo toSysUserVo(SysUser sysUser);

    SysRole toSysRoleEnetity(SysRoleDto sysRoleDto);

    SysRoleVo toSysRoleVo(SysRole sysRole);

    SysMenu toSysMenuEntity(SysMenuDto sysMenuDto);

    SysMenuVo toSysMenuVo(SysMenu sysMenu);

    SysDict toSysDict(SysDictDto sysDictDto);


    /*---------------music singer------------------*/
    MusicSinger toSingerEntity(MusicSingerDto musicSingerDto);

    MusicSingerVo toSingerVo(MusicSinger musicSinger);

    MusicSingerDto toSingerDto(MusicSinger musicSinger);

    /*---------------music song------------------*/
    MusicSong toSongEntity(MusicSongDto musicSongDto);

    MusicSongVo toSongVo(MusicSong musicSong);

    /*---------------music album------------------*/
    MusicAlbum toAlbumEntity(MusicAlbumDto musicAlbumDto);

    MusicAlbumVo toAlbumVo(MusicAlbum musicAlbum);


    /*---------------多媒体通讯------------------*/
    CmRecord toCmRecord(CmRecordDto cmRecordDto);

    CmRecordVo toCmRecordVo(CmRecord cmRecord);


    /*---------------文件管理------------------*/
    FileManagementVo toFileManagementVo(FileManagement fileManagement);

    /*---------------通知------------------*/
    SysNotifyVo toNotifyVo(SysNotify sysNotify);

    SysNotify toNotify(SysNotifyDto sysNotifyDto);

}
