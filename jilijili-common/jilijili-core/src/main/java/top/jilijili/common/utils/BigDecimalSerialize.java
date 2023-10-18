package top.jilijili.common.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @author Amani
 * @date 2023年10月17日 10:51
 */
public class BigDecimalSerialize extends JsonSerializer {

    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (Objects.nonNull(o) && o instanceof BigDecimal bigDecimal) {
            jsonGenerator.writeString(bigDecimal.setScale(2, BigDecimal.ROUND_DOWN) + "");
        }
    }
}