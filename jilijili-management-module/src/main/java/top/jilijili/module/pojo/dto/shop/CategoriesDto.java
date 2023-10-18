package top.jilijili.module.pojo.dto.shop;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.common.group.Insert;
import top.jilijili.module.pojo.dto.SuperDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品分类表
 *
 * @TableName shop_categories
 */
@TableName(value = "shop_categories")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CategoriesDto extends SuperDto implements Serializable {
    /**
     * 分类ID
     */
    private String categoryId;

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称不能为空", groups = Insert.class)
    @NotNull(message = "分类名称不能为空", groups = Insert.class)
    private String categoryName;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

    /**
     * 菜单
     */
    @NotBlank(message = "parentId不能为空", groups = Insert.class)
    @NotNull(message = "parentId不能为空", groups = Insert.class)
    private String parentId;

    private static final long serialVersionUID = 1L;
}