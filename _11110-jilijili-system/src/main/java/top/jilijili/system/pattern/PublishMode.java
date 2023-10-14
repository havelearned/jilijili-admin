package top.jilijili.system.pattern;

import top.jilijili.module.entity.SysNotify;

import java.util.List;

/**
 * 设计者模式:状态模式
 * 通知发布模式
 * 1: 添加并且立即发布
 * 2: 草稿
 * 3: 定时发布
 */
public interface PublishMode {
    void handlePublish();

    void handlePublish(List<SysNotify> sysNotifyList);


}