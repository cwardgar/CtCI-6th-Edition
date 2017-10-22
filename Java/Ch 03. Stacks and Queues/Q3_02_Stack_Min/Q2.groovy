package Q3_02_Stack_Min

import CtCILibrary.MyStack

/**
 *
 *
 * @author cwardgar
 * @since 2017-10-08
 */

MyStack<Integer> stack = new MyStack<>()

[ 10, 5, 9, 4, 8, 3, 7, 2, 6, 1 ].each {
    stack.push(it)
    println "top = ${stack.peek()}   min = ${stack.min()}"
}

println()

for (MyStack.StackNode<Integer> node; !stack.isEmpty(); stack.pop()) {
    println "top = ${stack.peek()}   min = ${stack.min()}"
}
