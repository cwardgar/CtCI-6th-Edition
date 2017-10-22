package CtCILibrary

/**
 *
 *
 * @author cwardgar
 * @since 2017-10-03
 */
class MyLinkedList<E> {
    Node<E> head
    Node<E> tail
    
    MyLinkedList() {}
    
    MyLinkedList(List<E> vals) {
        appendNodes vals
    }
    
    Node<E> appendNode(E val) {
        if (!head) {
            head = new Node<>(value: val)
            tail = head
        } else {
            tail.next = new Node<>(value: val)
            tail = tail.next
        }
        tail
    }
    
    void appendNodes(List<E> vals) {
        for (int val : vals) {
            appendNode(val)
        }
    }
    
    Node<E> prependNode(E val) {
        if (!tail) {
            head = new Node<>(value: val)
            tail = head
        } else {
            Node<E> node = new Node<>(value: val, next: head)
            head = node
        }
        head
    }
    
    @Override
    String toString() {
        StringBuilder sb = new StringBuilder()
        for (Node<E> node = head; node; node = node.next) {
            sb << node << ' '
        }
        sb.toString()
    }
    
    static class Node<E> {
        E value
        Node<E> next
        
        @Override
        String toString() {
            value.toString()
        }
    }
    
    static void main(String[] args) {
        MyLinkedList ll = new MyLinkedList()
        ['A', 'B', 'C', 'D'].each {
            ll.prependNode(it)
        }
        println ll
    }
}
