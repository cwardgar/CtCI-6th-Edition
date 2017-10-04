package Q2_05_Sum_Lists

import CtCILibrary.MyLinkedList

/**
 Sum Lists: You have two numbers represented by a linked list, where each node contains a single
 digit. The digits are stored in reverse order, such that the 1 's digit is at the head of the list. Write a
 function that adds the two numbers and returns the sum as a linked list.
 EXAMPLE
 Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is, 617 + 295.
 Output: 2 -> 1 -> 9. That is, 912.
 
 FOLLOW UP
 Suppose the digits are stored in forward order. Repeat the above problem.
 EXAMPLE
 Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is, 617 + 295.
 Output: 9 -> 1 -> 2. That is, 912.
 *
 * @author cwardgar
 * @since 2017-10-04
 */

MyLinkedList sumLists(MyLinkedList ll1, MyLinkedList ll2) {
//    MyLinkedList.Node node1 = ll1.head
//    MyLinkedList.Node node2 = ll2.head
    MyLinkedList.Node node1 = ll1.tail
    MyLinkedList.Node node2 = ll2.tail
    
    MyLinkedList ret = new MyLinkedList()
    int carry = 0
    
    while (node1 || node2 || carry) {
        int val = carry
        
        if (node1) {
            val += node1.value
            node1 = node1.next
        }
        if (node2) {
            val += node2.value
            node2 = node2.next
        }
        
        if (val < 10) {
            ret.appendNode(val)
            carry = 0
        } else {
            ret.appendNode(val % 10)
            carry = 1
        }
    }
    
    ret
}

println sumLists(new MyLinkedList([7, 1, 6]), new MyLinkedList([5, 9, 2]))
println sumLists(new MyLinkedList([9]), new MyLinkedList([9, 2, 1]))
println sumLists(new MyLinkedList([5, 5, 5]), new MyLinkedList([5, 5, 5]))
