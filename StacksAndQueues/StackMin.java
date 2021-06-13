package StacksAndQueues;

import java.util.Stack;

/*
 * Stack Min: How would you design a stack which,
 * in addition to push and pop, has a function min
 * which returns the minimum element? Push, pop and
 * min should all operate in O(1) time.
 */
public class StackMin extends Stack<Integer> {
  private Stack<Integer> minStack;

  public StackMin() {
    minStack = new Stack<>();
  }

	public void push(int value) {
    if (value <= min()) minStack.push(value);
    super.push(value);
	}

	public Integer pop() {
    int value = super.pop();
    if (value == min()) minStack.pop();
    return value;
	}

	public int min() {
    if (minStack.isEmpty()) return Integer.MAX_VALUE;
    return minStack.peek();
  }

  public static void main(String[] args) {
    StackMin stackMin = new StackMin();
    for (int value : new int[] { 2, 1, 3, 1 }) stackMin.push(value);
    for (int i = 0; i < 4; ++i) {
      System.out.println("Popped: " + stackMin.pop());
      System.out.println("Minimum: " + stackMin.min());
    }
  }
}
