package wang.jilijili.music.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

/**
 * @Auther: Amani
 * @Date: 2023/1/23 17:30
 * @Description:
 */
public class KsuidGenerator implements IdentifierGenerator {


    @Override
    public Number nextId(Object entity) {
        Snowflake snowflake = IdUtil.createSnowflake(1, 1);
        long id = snowflake.nextId();
        return null;
    }
}
