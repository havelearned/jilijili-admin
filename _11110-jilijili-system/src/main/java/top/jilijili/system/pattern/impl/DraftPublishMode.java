package top.jilijili.system.pattern.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import top.jilijili.module.entity.SysNotify;
import top.jilijili.system.pattern.PublishMode;

import java.util.List;

/**
 * 实现草稿的逻辑
 */
@Component
@AllArgsConstructor
public class DraftPublishMode implements PublishMode {


    @Override
    public void handlePublish() {

    }

    @Override
    public void handlePublish(List<SysNotify> sysNotifyList) {

    }
}
