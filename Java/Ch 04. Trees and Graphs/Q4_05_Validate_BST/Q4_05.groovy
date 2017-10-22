package Q4_05_Validate_BST

import CtCILibrary.TreeNode

/**
 Validate BST: Implement a function to check if a binary tree is a binary search tree.
 *
 * @author cwardgar
 * @since 2017-10-09
 */

boolean isBst(TreeNode node) {
    isBstAux node, Integer.MIN_VALUE, Integer.MAX_VALUE
}

boolean isBstAux(TreeNode node, int min, int max) {  // [min, max]
    if (!node) {
        true
    } else {
        min <= node.data &&
        node.data < max &&
        isBstAux(node.left, min, node.data) &&
        isBstAux(node.right, node.data, max)
    }
}

int[] array = new int[13]

for (int i = 0; i < array.length; ++i) {
    array[i] = i
}
TreeNode root = TreeNode.createMinimalBST(array)
println isBst(root)

for (int i = 0; i < array.length; ++i) {
    array[i] = array.length - i
}
TreeNode other = TreeNode.createMinimalBST(array)
println isBst(other)
