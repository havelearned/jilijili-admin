package top.jilijili.module.pojo.vo.common;

import lombok.*;

import java.util.List;

/**
 * UI Quasar 树形结构
 *
 * @author Amani
 * @date 2023年10月19日 22:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QTree {

    protected String label;


    protected String icon;

    protected String iconColor;


    protected String avatar;
    protected List<QTree> children;


    public List<QTree> getUpChildren() {
        return children;
    }

    public void setUpChildren(List<QTree> children) {
        this.children = children;
    }
}
