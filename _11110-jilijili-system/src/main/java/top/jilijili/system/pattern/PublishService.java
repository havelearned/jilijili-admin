package top.jilijili.system.pattern;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import top.jilijili.module.entity.SysNotify;

import java.util.List;

@Service
@AllArgsConstructor
public class PublishService {
    private final PublishContext publishContext;


    /**
     * 通知发布,
     *
     * @param publishMode
     */
    public void publishNotify(int publishMode, List<SysNotify> sysNotifyList) {

        publishContext.publish(publishMode, sysNotifyList);
    }
}
