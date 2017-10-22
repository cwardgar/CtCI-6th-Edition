package Q4_03_List_of_Depths

import CtCILibrary.TreeNode

/**
 Check Balanced: Implement a function to check if a binary tree is balanced. For the purposes of
 this question, a balanced tree is defined to be a tree such that the heights of the two subtrees of any
 node never differ by more than one.
 
 The height of a node is the number of edges on the longest path between that node and a leaf.
   --> The height of a leaf is 0.
 *
 * @author cwardgar
 * @since 2017-10-09
 */

class Ret {
    boolean isBalanced
    int height
}

boolean isBalanced(TreeNode root) {
    isBalancedAux(root).isBalanced
}

Ret isBalancedAux(TreeNode node) {
    if (node == null) {
        return new Ret(isBalanced: true, height: -1)
    }
    
    Ret leftRet = isBalancedAux(node.left)
    Ret rightRet = isBalancedAux(node.right)
    
    boolean isBalanced = leftRet.isBalanced && rightRet.isBalanced &&
                           Math.abs(leftRet.height - rightRet.height) <= 1
    int height = Math.max(leftRet.height, rightRet.height) + 1
    
    new Ret(isBalanced: isBalanced, height: height)
}

int[] array = new int[13]
for (int i = 0; i < array.length; ++i) {
    array[i] = i
}
TreeNode root = TreeNode.createMinimalBST(array)

println isBalanced(root)
