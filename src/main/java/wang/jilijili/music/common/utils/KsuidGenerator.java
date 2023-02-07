package wang.jilijili.music.common.utils;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

/**
 * @Auther: Amani
 * @Date: 2023/1/23 17:30
 * @Description:
 */
public class KsuidGenerator implements IdentifierGenerator {
    @Override
    public String generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return com.github.ksuid.KsuidGenerator.generate();
    }
}
