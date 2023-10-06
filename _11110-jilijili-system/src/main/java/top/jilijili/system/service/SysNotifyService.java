package top.jilijili.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.module.entity.dto.SysNotifyDto;
import top.jilijili.module.entity.vo.Result;
import top.jilijili.module.entity.vo.SysNotifyVo;

/**
* @author admin
* @description 针对表【sys_notify】的数据库操作Service
* @createDate 2023-10-06 16:05:18
*/
public interface SysNotifyService extends IService<SysNotify> {

    /**
     * 通过通知id查询通知信息
     *
     * @param notifyId 通知id
     * @return 通知对象
     */
    Result<SysNotifyVo> getNotifyById(Long notifyId);

    /**
     * 查询通知所有信息
     *
     * @param sysNotifyDto 查询对象
     * @return
     */
    Result<IPage<SysNotifyVo>> getNotifyList(SysNotifyDto sysNotifyDto);


    /**
     * 添加或者修改通知信息
     *
     * @param sysNotifyDto
     * @return 操作是否成功
     */
    Result<String> addOrEditNotify(SysNotifyDto sysNotifyDto);
}
