package Q4_06_Successor

import CtCILibrary.TreeNode

/**
 Successor: Write an algorithm to find the "next" node (i.e., in-order successor) of a given node in a
 binary search tree. You may assume that each node has a link to its parent.
 *
 * @author cwardgar
 * @since 2017-10-09
 */

TreeNode successor(TreeNode node) {
    getBstMin(node.right) ?: getNearestAncestorGreaterThan(node)
}

TreeNode getBstMin(TreeNode node) {  // Node is the root of a BST.
    if (!node) {
        return null
    }
    
    while (node.left) {
        node = node.left
    }
    
    return node
}

TreeNode getNearestAncestorGreaterThan(TreeNode node) {
    if (!node || !node.parent) {
        return null
    }
    
    while (node.parent && !isLeftChildOfParent(node)) {
        node = node.parent
    }
    
    return node.parent
}

boolean isLeftChildOfParent(TreeNode node) {
    return node == node.parent.left
}


int[] array = new int[13]
for (int i = 0; i < array.length; ++i) {
    array[i] = i
}

TreeNode root = TreeNode.createMinimalBST(array)
root.print()

TreeNode n1 = root.find(1)
println successor(n1)

TreeNode n2 = root.find(6)
println successor(n2)

TreeNode n3 = root.find(11)
println successor(n3)
