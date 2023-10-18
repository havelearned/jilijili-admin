package top.jilijili.system.pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jilijili.module.pojo.entity.sys.SysNotify;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PublishContext {
    private final Map<Integer, PublishMode> publishModes;

    @Autowired
    public PublishContext(PublishMode immediatePublishMode,
                          PublishMode draftPublishMode,
                          PublishMode scheduledPublishMode) {
        this.publishModes = new HashMap<>();
        publishModes.put(1, immediatePublishMode);
        publishModes.put(2, draftPublishMode);
        publishModes.put(3, scheduledPublishMode);
    }

    public void publish(Integer publishMode) {
        if (publishModes.containsKey(publishMode)) {
            publishModes.get(publishMode).handlePublish();
        } else {
            throw new IllegalArgumentException("不支持的发布模式");
        }
    }

    public void publish(Integer publishMode, List<SysNotify> sysNotifies) {
        if (publishModes.containsKey(publishMode)) {
            publishModes.get(publishMode).handlePublish(sysNotifies);
        } else {
            throw new IllegalArgumentException("不支持的发布模式");
        }

    }
}
