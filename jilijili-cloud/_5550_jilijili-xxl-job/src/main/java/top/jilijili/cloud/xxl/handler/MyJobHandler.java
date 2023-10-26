package top.jilijili.cloud.xxl.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyJobHandler {

//    @Autowired
//    private ISchoolFileConfigService schoolFileConfigService;

    @XxlJob("lessExpireSchoolFileJob")
    public ReturnT<String> lessExpireSchoolFileJob(String param) {
        String jobParam = XxlJobHelper.getJobParam();
        return ReturnT.SUCCESS;
    }
}
