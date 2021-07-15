import java.util.ArrayDeque;
import java.util.Deque;

public class MinStack {
    private Deque<Integer> stack;
    private Deque<Integer> minStack;

    /** initialize your data structure here. */
    /*
     * [-2,-5,0,-3]
     * [-2,-5]
     * Pop element from stack. Put in x.
     * Check x and minStack top:
     * - If x <= minStack.top, then minStack.push(x)
     * - If x > minStack.top, do nothing.
     * minStack.top is always the min element.
     * If same element as minStack.top is popped, then
     * pop minStack.top also.
     */
    public MinStack() {
        this.stack = new ArrayDeque<>();
        this.minStack = new ArrayDeque<>();
    }
    
    public void push(int val) {
        this.stack.push(val);
        if (this.minStack.isEmpty() || 
            val <= this.minStack.peek()) {
            this.minStack.push(val);
        }
    }
    
    public void pop() {
        int elem;
        
        elem = this.stack.pop();
        if (elem == this.minStack.peek()) {
            this.minStack.pop();
        }
    }
    
    public int top() {
        return this.stack.peek();
    }
    
    public int getMin() {
        return this.minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */