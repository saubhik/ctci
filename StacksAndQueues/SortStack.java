package StacksAndQueues;

import java.util.Arrays;
import java.util.Stack;

/*
 * Sort Stack: Write a program to sort a stack
 * such that the smallest items are on the top.
 * You can use an additional temporary stack, but
 * you may not copy the elements into any other data
 * structure (such as an array). The stack supports the
 * following operations: push, pop, peek and isEmpty.
 * 
 * Iterations:
 * Step 1:
 * [3, 4, 1, 5]
 * []
 * 
 * Step 2:
 * [3, 4, 1]
 * [5]
 * 
 * Step 3:
 * tmp = 1
 * [3, 4]
 * [5]
 * If stack.pop() < otherStack.peek(), use a temporary
 * variable. Then stack.push(otherStack.pop()) till
 * tmp > otherStack.peek(). Then otherStack.push(tmp).
 * Then push back elements from other stack.
 * [3, 4]
 * [1, 5]
 * 
 * Step 4:
 * [3]
 * [1, 4, 5]
 * 
 * Step 5:
 * []
 * [1, 3, 4, 5]
 * 
 * Step 6:
 * Shift from otherStack to stack.
 * [5, 4, 3, 1]
 * []
 * 
 * Time Complexity: O(n^2).
 * At each step, we are sorting the rightmost part, by comparing
 * with the first element of the sorted part. In the worst case,
 * we would compare with all the sorted elements. So, the time
 * complexity is 1 + 2 + 3 + ... + n = O(n^2).
 *
 * Space Complexity: O(n).
 * We use an additional stack as space.
 */
public class SortStack {
    static void sort(Stack<Integer> stack) {
        Stack<Integer> otherStack = new Stack<>();
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            while (!otherStack.isEmpty() && tmp < otherStack.peek()) {
                stack.push(otherStack.pop());
            }
            otherStack.push(tmp);
        }
        while (!otherStack.isEmpty()) stack.push(otherStack.pop());
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        for (int val : new int[] { 3, 4, 1, 5 }) stack.push(val);
        sort(stack);
        System.out.println(Arrays.toString(stack.toArray()));
    }
}
