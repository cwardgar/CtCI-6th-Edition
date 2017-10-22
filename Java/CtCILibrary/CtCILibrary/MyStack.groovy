package CtCILibrary

class MyStack<T> {
    private StackNode<T> top
    private StackNode<StackNode<T> > min
    
    MyStack() {}
    
    MyStack(List<T> items) {
        pushAll items
    }
    
    T pop() {
        if (top == null) {
            throw new EmptyStackException()
        }
        
        if (top == min.data) {
            min = min.next
        }
        
        T item = top.data
        top = top.next
        item
    }
    
    void push(T item) {
        StackNode<T> t = new StackNode<>(item)
        t.next = top
        top = t
        
        if (!min || item < min.data.data) {
            StackNode<StackNode<T> > m = new StackNode<>(t)
            m.next = min
            min = m
        }
    }
    
    void pushAll(List<T> items) {
        items.each {
            push it
        }
    }
    
    T peek() {
        if (top == null) {
            throw new EmptyStackException()
        }
        top.data
    }
    
    boolean isEmpty() {
        return top == null
    }
    
    T min() {
        if (min == null) {
            throw new EmptyStackException()
        }
        min.data.data
    }
    
    @Override
    String toString() {
        StringBuilder sb = new StringBuilder()
        
        for (StackNode<T> node = top; node; node = node.next) {
            sb << node << '\n'
        }
    
        sb << "-----"
        sb.toString()
    }
    
    
    static class StackNode<T> {
        private T            data
        private StackNode<T> next
        
        StackNode(T data) {
            this.data = data
        }
        
        @Override
        String toString() {
            data.toString()
        }
    }
}
