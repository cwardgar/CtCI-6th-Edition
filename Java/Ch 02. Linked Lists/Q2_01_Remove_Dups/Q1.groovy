package Q2_01_Remove_Dups

import CtCILibrary.MyLinkedList

/**
 Remove Dups! Write code to remove duplicates from an unsorted linked list.
 FOLLOW UP
 How would you solve this problem if a temporary buffer is not allowed?
 *
 * @author cwardgar
 * @since 2017-10-03
 */

MyLinkedList removeDups(MyLinkedList ll) {
    Set<Integer> uniqueElems = new HashSet<>()
    uniqueElems.add ll.head.value
    
    for (MyLinkedList.Node node = ll.head; node.next; ) {
        if (!uniqueElems.add(node.next.value)) {  // node.next.value is a dup
            node.next = node.next.next  // remove node.next
        } else {
            node = node.next
        }
    }
    
    ll
}


MyLinkedList ll = new MyLinkedList([1, 5, 9, 3, 1, 7, 9, 5, 1, 6, 8, 3, 4, 5, 7])
println removeDups(ll)
