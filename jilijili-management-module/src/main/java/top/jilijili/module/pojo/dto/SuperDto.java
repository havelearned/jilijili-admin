package top.jilijili.module.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField(exist = false)
    protected Integer page = 0;

    @NotNull
    @TableField(exist = false)
    protected Integer size = 10;

    /**
     * 关键字
     */
    @TableField(exist = false)
    protected String keyword;
    /**
     * 比较时间
     */
    @TableField(exist = false)
    protected Date comparisonTime;
}
