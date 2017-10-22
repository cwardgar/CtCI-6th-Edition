package Q4_09_BST_Sequences

import CtCILibrary.TreeNode

/**
 BST Sequences: A binary search tree was created by traversing through an array from left to right
 and inserting each element. Given a binary search tree with distinct elements, print all possible
 arrays that could have led to this tree.
 *
 * @author cwardgar
 * @since 2017-10-13
 */

void printBstSequences(TreeNode node) {
    if (node == null) {
        return
    } else {
        printBstSequencesAux([node] as LinkedList, [] as Stack)
    }
}

void printBstSequencesAux(Deque<TreeNode> unprocessed, Stack<Integer> sequence) {
    if (unprocessed.isEmpty()) {
        println sequence
        return
    }
    
    for (int i = 0; i < unprocessed.size(); ++i) {
        TreeNode curNode = unprocessed.removeFirst()
        sequence.push(curNode.data)
        if (curNode.left) {
            unprocessed << curNode.left
        }
        if (curNode.right) {
            unprocessed << curNode.right
        }
    
        printBstSequencesAux unprocessed, sequence
        
        // Undo our changes.
        if (curNode.right) {
            unprocessed.removeLast()
        }
        if (curNode.left) {
            unprocessed.removeLast()
        }
        sequence.pop()
        unprocessed.addLast(curNode)
        
        // At the start of the iteration, curNode is the first element in 'unprocessed'.
        // However, at the end of the iteration, curNode is the last element in 'unprocessed'.
        // That is the ONLY difference when you compare beginning vs ending values.
        //
        // 'sequence' ending value is identical to 'sequence' starting value.
    }
}

////////////////////////////////////////////////////////

//def list = [2, 1, 3]
//def list = [3, 1, 5, 0, 2, 4, 6]
def list = [7,4,11,2,6,9,13,1,3,5,7,8,10,12,14]
TreeNode root

list.each {
    if (!root) {
        root = new TreeNode(it)
    } else {
        root.insertInOrder(it)
    }
}

root.print()

printBstSequences root.find(4)
