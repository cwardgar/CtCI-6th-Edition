package CtCILibrary

/**
 * 
 *
 * @author cwardgar
 * @since 2017-10-03
 */
class MyLinkedList {
    Node head
    Node tail
    
    MyLinkedList() {}
    
    MyLinkedList(List<Integer> vals) {
        appendNodes vals
    }
    
    void appendNode(int val) {
        if (!head) {
            head = new Node(value: val)
            tail = head
        } else {
            tail.next = new Node(value: val)
            tail = tail.next
        }
    }
    
    void appendNodes(List<Integer> vals) {
        for (int val : vals) {
            appendNode(val)
        }
    }
    
    void prependNode(int val) {
        if (!tail) {
            head = new Node(value: val)
            tail = head
        } else {
            Node node = new Node(value: val, next: head)
            head = node
        }
    }
    
    @Override
    String toString() {
        StringBuilder sb = new StringBuilder()
        for (Node node = head; node; node = node.next) {
            sb << node.value << ' '
        }
        sb.toString()
    }
    
    static class Node {
        int  value
        Node next
    }
    
    static void main(String[] args) {
        MyLinkedList ll = new MyLinkedList()
        [1,2,3,4].each {
            ll.prependNode(it)
        }
        println ll
    }
}
