package wang.jilijili.music.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.github.ksuid.KsuidGenerator;
import lombok.extern.java.Log;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MybatisPlus 自动填充
 *
 * @author Amani
 * @date 2023年02月12日 19:01
 */
@Log
@Component
public class MPMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictUpdateFill
                (metaObject, "lastLoginTime", Date.class, new Date()); // 起始版本 3.3.0(推荐)
        this.strictInsertFill(metaObject, "createdTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictUpdateFill
                (metaObject, "lastLoginTime", Date.class, new Date()); // 起始版本 3.3.0(推荐)
        this.strictInsertFill(metaObject, "updatedTime", Date.class, new Date()); // 起始版本 3.3.0(推荐使用)
    }
}
