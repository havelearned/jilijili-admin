package top.jilijili.system.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年06月22日 11:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class SuperDto {
    @NotNull
    protected Integer page = 0;

    @NotNull
    protected Integer size = 10;

    /**
     * 关键字
     */
    protected String keyword;
    /**
     * 比较时间
     */
    protected Date comparisonTime;
}
