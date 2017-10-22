package Q4_08_First_Common_Ancestor

import CtCILibrary.TreeNode

/**
 First Common Ancestor: Design an algorithm and write code to find the first common ancestor
 of two nodes in a binary tree. Avoid storing additional nodes in a data structure. NOTE: This is not
 necessarily a binary search tree.
 *
 * @author cwardgar
 * @since 2017-10-13
 */

TreeNode findFirstCommonAncestor(TreeNode tree, TreeNode node1, TreeNode node2) {
    if (node1 == tree) {
        throw new IllegalArgumentException("'$node1' is the root of the tree; it has no ancestor.")
    } else if (node2 == tree) {
        throw new IllegalArgumentException("'$node2' is the root of the tree; it has no ancestor.")
    }
    
    Tuple2<TreeNode, TreeNode> ancestors = findFirstCommonAncestorAux(tree, node1, node2)
    
    if (!ancestors.first) {
        throw new IllegalArgumentException("'$node1' does not exist in the tree.")
    } else if (!ancestors.second) {
        throw new IllegalArgumentException("'$node2' does not exist in the tree.")
    } else {
        assert ancestors.first == ancestors.second
        return ancestors.first
    }
}

// If node1 doesn't exist in the tree, then ret.first == null.
// If node2 doesn't exist in the tree, then ret.second == null.
// Otherwise, ret.first != null && ret.second != null && ret.first == ret.second.
//
// If node1 == tree, then ret.first  == tree. ret.first  is not a true ancestor of node1.
// If node2 == tree, then ret.second == tree. ret.second is not a true ancestor of node2.
//
// Otherwise, node1 != ret.first && node2 != ret.second. ret.first is a true FCA of the two nodes!
Tuple2<TreeNode, TreeNode> findFirstCommonAncestorAux(TreeNode tree, TreeNode node1, TreeNode node2) {
    if (!tree) {
        return new Tuple2<TreeNode, TreeNode>(null, null)
    }
    
    Tuple2<TreeNode, TreeNode> left = findFirstCommonAncestorAux(tree.left, node1, node2)
    if (left.first && left.second) {  // Both nodes are in the left subtree.
        // One of the nodes is the ROOT of the left subtree. We need its first ancestor, which is simply "tree".
        if (tree.left == node1 || tree.left == node2) {
            return new Tuple2<TreeNode, TreeNode>(tree, tree)
        } else {
            return left  // We're done; the FCA is in the left subtree.
        }
    }
    
    Tuple2<TreeNode, TreeNode> right = findFirstCommonAncestorAux(tree.right, node1, node2)
    if (right.first && right.second) {  // Both nodes are in the right subtree.
        // One of the nodes is the ROOT of the right subtree. We need its first ancestor, which is simply "tree".
        if (tree.right == node1 || tree.right == node2) {
            return new Tuple2<TreeNode, TreeNode>(tree, tree)
        } else {
            return right  // We're done; the FCA is in the right subtree.
        }
    }
    
    assert !(left.first  && right.first)  : "node1's ancestor can't be in both the left and right subtrees."
    assert !(right.first && right.second) : "node2's ancestor can't be in both the left and right subtrees."
    
    TreeNode ancestor1 = left.first ?: right.first    // Could be null.
    if (ancestor1 || tree == node1) {
        ancestor1 = tree
    }
    
    TreeNode ancestor2 = left.second ?: right.second  // Could be null.
    if (ancestor2 || tree == node2) {
        ancestor2 = tree
    }
    
    new Tuple2<TreeNode, TreeNode>(ancestor1, ancestor2)
}

//////////////////////////////////////////////////

int[] array = new int[15]
for (int i = 0; i < array.length; ++i) {
    array[i] = i
}

TreeNode root = TreeNode.createMinimalBST(array)
root.print()

TreeNode node1 = root.find(0)
TreeNode node2 = root.find(14)

println "The first common ancestor of '$node1' and '$node2' is: '${findFirstCommonAncestor(root, node1, node2)}'"
