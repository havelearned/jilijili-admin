package top.jilijili.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.pojo.entity.sys.SysNotify;
import top.jilijili.module.pojo.dto.sys.SysNotifyDto;
import top.jilijili.common.entity.Result;
import top.jilijili.module.pojo.vo.sys.SysNotifyVo;

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

    /**
     * @param notifyId  通过id查询通知并发布
     * @param sysNotify 通过已知通知发布公告
     */
    void sendSysNotify(Long notifyId, SysNotify sysNotify);

    /**
     * 发送系统通知
     * @param sysNotify 系统通知对象
     */
     void sendSysNotify(SysNotify sysNotify);


}
