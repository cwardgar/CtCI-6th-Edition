package Q4_02_Minimal_Tree

import CtCILibrary.BTreePrinter
import CtCILibrary.TreeNode

/**
 Minimal Tree: Given a sorted (increasing order) array with unique integer elements, write an
 algorithm to create a binary search tree with minimal height.
 *
 * @author cwardgar
 * @since 2017-10-09
 */

TreeNode createBSTfromSortedArray(int[] array) {
    createBSTfromSortedArrayAux array, 0, array.length
}

TreeNode createBSTfromSortedArrayAux(int[] array, int startIdx, int endIdx) {  // [startIdx, endIdx)
    if (startIdx == endIdx) {
        return null  // Empty sub-tree.
    } else if (endIdx - startIdx == 1) {
        return new TreeNode(array[startIdx])
    }
    
    int midIdx = (startIdx + endIdx) / 2
    
    TreeNode node = new TreeNode(array[midIdx])
    node.left = createBSTfromSortedArrayAux array, startIdx, midIdx
    node.right = createBSTfromSortedArrayAux array, midIdx + 1, endIdx
    
    node
}

int[] array = new int[10]
for (int i = 0; i < array.length; ++i) {
    array[i] = i
}

TreeNode root = createBSTfromSortedArray array
BTreePrinter.printNode root
