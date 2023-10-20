package top.jilijili.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Amani
 * @date 2023年10月17日 23:49
 */
public class TreeUtils<T> {

    /**
     * 递归设置树结构
     *
     * @param parentId 当前父类的ID
     * @param records  所有的记录
     * @return 树结构
     */
    public static <T extends TreeNode> List<T> buildTree(String parentId, List<T> records) {
        List<T> childNodes = new ArrayList<>();
        for (T node : records) {
            if (node.getParentId().equals(parentId)) {
                List<T> subNodes = buildTree(node.getCategoryId(), records);
                node.editChildNodes(subNodes);
                childNodes.add(node);
            }
        }
        if (childNodes.isEmpty()) {
            return null;
        }
        return childNodes;
    }


}



