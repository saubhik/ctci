package StacksAndQueues;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;

/*
 * Stack Of Plates: Imagine a (literal) stack of plates.
 * If the stack gets too high, it might topple. Therefore,
 * in real life, we would likely start a new stack when
 * the previous stack exceeds some threshold. Implement a
 * data structure SetOfStacks that mimics this. SetOfStacks
 * should be composed of several stacks and should create
 * a new stack once the previous one exceeds capacity.
 * SetOfStacks.push() and SetOfStacks.pop() should behave
 * identically to a single stack (that is, pop() should return
 * the same values as it would if there were just a single
 * stack).
 * FOLLOW UP: Implement a function popAt(int index) which
 * performs a pop operation on a specific substack.
 */
public class StackOfPlates {
    private List<Stack<Integer>> stacks;
    private int capacity;

    public StackOfPlates(int capacity) {
        this.capacity = capacity;
        stacks = new ArrayList<>();
    }

    public Stack<Integer> getLastStack() {
        if (stacks.isEmpty()) return null;
        return stacks.get(stacks.size() - 1);
    }

    public Integer getNumStacks() {
        return stacks.size();
    }

    public void push(int value) {
        Stack<Integer> lastStack = getLastStack();
        if (lastStack == null || lastStack.size() == capacity) {
            Stack<Integer> newStack = new Stack<>();
            newStack.push(value);
            stacks.add(newStack);
        } else {
            lastStack.push(value);
        }
    }

    public Integer pop() throws EmptyStackException {
        Stack<Integer> lastStack = getLastStack();
        if (lastStack == null) {
            throw new EmptyStackException();
        }
        int val = lastStack.pop();
        if (lastStack.isEmpty()) {
            stacks.remove(stacks.size() - 1);
        }
        return val;
    }

    public Integer peek() throws EmptyStackException {
        Stack<Integer> lastStack = getLastStack();
        if (lastStack == null) {
            throw new EmptyStackException();
        }
        return lastStack.peek();
    }

    public Integer popAt(int index) throws IndexOutOfBoundsException {
        Stack<Integer> stack = stacks.get(index);
        int val = stack.pop();
        leftShift(index);
        return val;
    }

    public void leftShift(int index) {
        /* Stack at index has empty top. Fill it. */
        if (index >= getNumStacks() - 1) return;
        Stack<Integer> currentStack = stacks.get(index);
        Stack<Integer> nextStack = stacks.get(index + 1);
        currentStack.push(nextStack.get(0));
        nextStack.remove(0);
        if (nextStack.isEmpty()) stacks.remove(stacks.size() - 1);
        if (index < getNumStacks() - 2) leftShift(index + 1);
    }

    public void printStacks() {
        for (int i = 0; i < getNumStacks(); ++i) {
            System.out.println("Stack " + i + ": " + stacks.get(i));
        }
    }

    public static void main(String[] args) {
        StackOfPlates set = new StackOfPlates(5);
        for (int i = 0; i < 34; ++i) set.push(i);
        set.printStacks();
        for (int i = 0; i < 34; ++i) System.out.println("Popped: " + set.pop());
    }
}
