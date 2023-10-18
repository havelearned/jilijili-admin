package top.jilijili.common.utils;

import java.util.List;

public interface TreeNode {
    String getParentId();

    String getCategoryId();

    void editChildNodes(List<? extends TreeNode> childNodes);
}
