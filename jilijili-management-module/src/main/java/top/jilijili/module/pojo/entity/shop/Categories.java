package top.jilijili.module.pojo.entity.shop;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.jilijili.common.utils.TreeNode;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 商品分类表
 *
 * @TableName shop_categories
 */
@TableName(value = "shop_categories")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Categories implements Serializable, TreeNode {
    /**
     * 分类ID
     */
    @TableId(value = "category_id")
    private String categoryId;

    /**
     * 分类名称
     */
    @TableField(value = "category_name")
    private String categoryName;

    /**
     * 创建时间
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 更新时间
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    /**
     * 菜单
     */
    @TableField(value = "parent_id")
    private String parentId;


    @TableField(exist = false)
    List<Categories> chilendList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


    @Override
    public void editChildNodes(List<? extends TreeNode> childNodes) {
        this.chilendList = (List<Categories>) childNodes;
    }
}