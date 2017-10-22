package Q2_08_Loop_Detection

import CtCILibrary.MyLinkedList

/**
 Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the
 beginning of the loop.
 DEFINITION
 Circular linked list: A (corrupt) linked list in which a node's next pointer points to an earlier node, so
 as to make a loop in the linked list.
 EXAMPLE
 Input: A - > B - > C - > D - > E - > C [the same C as earlier]
 Output: C
 *
 * @author cwardgar
 * @since 2017-10-07
 */

MyLinkedList.Node detectLoop(MyLinkedList ll) {
    MyLinkedList.Node slowRunner = ll.head
    MyLinkedList.Node fastRunner = ll.head
    
    while (fastRunner.next && fastRunner.next.next) {
        slowRunner = slowRunner.next
        fastRunner = fastRunner.next.next
        
        if (slowRunner == fastRunner) {
            break
        }
    }
    
    if (!fastRunner.next || !fastRunner.next.next) {
        return null  // Linked list doesn't contain a loop.
    }
    
    // We've found a node in the loop. We can determine the loop's size by moving around the loop unti we
    // encounter it again.
    MyLinkedList.Node nodeInLoop = slowRunner
    int loopSize = 0
    
    while (true) {
        slowRunner = slowRunner.next
        loopSize++
        
        if (slowRunner == nodeInLoop) {
            break
        }
    }
    
    // Place slowRunner at HEAD and fastRunner at HEAD+loopSize. Move each forward 1 node at a time until they
    // point to the same node. That node is the first node in the loop.
    slowRunner = ll.head
    fastRunner = ll.head
    for (int i = 0; i < loopSize; ++i) {
        fastRunner = fastRunner.next
    }
    
    while (slowRunner != fastRunner) {
        slowRunner = slowRunner.next
        fastRunner = fastRunner.next
    }
    
    slowRunner
}

MyLinkedList ll = new MyLinkedList()
MyLinkedList.Node a = ll.appendNode 'A'
MyLinkedList.Node b = ll.appendNode 'B'
MyLinkedList.Node c = ll.appendNode 'C'
MyLinkedList.Node d = ll.appendNode 'D'
MyLinkedList.Node e = ll.appendNode 'E'

println detectLoop(ll)  // Ought to be null; no loop yet.

e.next = c

println detectLoop(ll)
