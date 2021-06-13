package StacksAndQueues;

import java.util.Arrays;
import java.util.EmptyStackException;

class FullStackException extends Exception {
    public FullStackException() { super(); }
    public FullStackException(String message) { super(message); }
}

/* Fixed Division */
class FixedMultiStack {
    private int numberOfStacks = 3;
    private int stackSize;
    private int[] values;
    private int[] sizes;

    public FixedMultiStack(int stackSize) {
        this.stackSize = stackSize;
        values = new int[stackSize * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void push(int stackNum, int value) throws FullStackException {
        if (isFull(stackNum)) throw new FullStackException();
        ++sizes[stackNum];
        values[indexOfTop(stackNum)] = value;
    }

    public int pop(int stackNum) throws EmptyStackException{
        if (isEmpty(stackNum)) throw new EmptyStackException();
        int ind = indexOfTop(stackNum);
        int val = values[ind];
        values[ind] = 0;
        --sizes[stackNum];
        return val;
    }

    public int peek(int stackNum) throws EmptyStackException {
        if (isEmpty(stackNum)) throw new EmptyStackException();
        return values[indexOfTop(stackNum)];
    }

    public boolean isFull(int stackNum) {
        return sizes[stackNum] == stackSize;
    }

    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public int indexOfTop(int stackNum) {
        return stackNum * stackSize + sizes[stackNum] - 1;
    }

    public void printStacks() {
        System.out.println("-------------------");
        for (int i = 0; i < numberOfStacks; ++i) {
            System.out.print("Stack " + (i + 1) + ": ");
            for (int j = 0; j < sizes[i]; ++j)
                System.out.print(values[j + i * stackSize] + " ");
            System.out.println();
        }
    }
}

/* Flexible Boundaries */
class MultiStack {
    private int numberOfStacks = 3;
    private int defaultCapacity;
    private int[] values;
    private int[] sizes;  /* How much full? */
    private int[] capacities;  /* Total how much a stack can accomodate? */

    public MultiStack(int defaultCapacity) {
        this.defaultCapacity = defaultCapacity;
        capacities = new int[numberOfStacks];
        Arrays.fill(capacities, defaultCapacity);
        values = new int[defaultCapacity * numberOfStacks];
        sizes = new int[numberOfStacks];
    }

    public void push(int stackNum, int value) throws FullStackException {
        if (allStacksFull()) throw new FullStackException();
        if (isFull(stackNum)) rshift(stackNum + 1, value);
        else values[getTopIndex(stackNum) + 1] = value;
        ++sizes[stackNum];
    }

    public int pop(int stackNum) throws EmptyStackException {
        if (isEmpty(stackNum)) throw new EmptyStackException();
        int ind = getTopIndex(stackNum);
        int val = values[ind];
        values[ind] = 0;
        --sizes[stackNum];
        if (capacities[stackNum] > defaultCapacity) --capacities[stackNum];
        for (int i = stackNum + 1; i < numberOfStacks && capacities[i] < defaultCapacity; ++i)
            ++capacities[i];
        if (isFull(stackNum)) lshift(stackNum + 1);
        return val;
    }

    public int peek(int stackNum) throws EmptyStackException {
        if (isEmpty(stackNum)) throw new EmptyStackException();
        return values[getTopIndex(stackNum)];
    }

    public boolean isEmpty(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public boolean isFull(int stackNum) {
        return sizes[stackNum] >= capacities[stackNum];
    }

    public boolean allStacksFull() {
        for (int i = 0; i < numberOfStacks; ++i) {
            if (sizes[i] < capacities[i]) return false;
        }
        return true;
    }

    public void rshift(int stackNum, int shiftVal) {
        int topVal = -1;
        if (isFull(stackNum)) topVal = values[getTopIndex(stackNum)];
        for (int i = getTopIndex(stackNum) + 1; i > getBottomIndex(stackNum); --i)
            values[i] = values[i - 1];
        values[getBottomIndex(stackNum)] = shiftVal;
        --capacities[stackNum];
        ++capacities[stackNum - 1];
        if (isFull(stackNum) && stackNum < numberOfStacks) rshift(stackNum + 1, topVal);
    }

    public void lshift(int stackNum) {
        for (int i = getBottomIndex(stackNum); i <= getTopIndex(stackNum); ++i)
            values[i] = values[i + 1];
        if (isFull(stackNum) && stackNum < numberOfStacks) lshift(stackNum + 1);
    }

    public int getTopIndex(int stackNum) {
        int index = 0;
        for (int i = 0; i < stackNum; ++i)
            index += capacities[i];
        return index + sizes[stackNum] - 1;
    }

    public int getBottomIndex(int stackNum) {
        int index = 0;
        for (int i = 0; i < stackNum; ++i)
            index += capacities[i];
        return index;
    }

    public void printStacks() {
        System.out.println("-------------------");
        for (int i = 0, baseIndex = 0; i < numberOfStacks; ++i) {
            System.out.print("Stack " + i + ": ");
            for (int j = 0; j < sizes[i]; ++j)
                System.out.print(values[baseIndex + j] + " ");
            baseIndex += capacities[i];
            System.out.println();
        }
    }
}

/*
 * Three in One: Describe how you could use a single array 
 * to implement 3 stacks.
 */
public class ThreeInOne {
    public static void main(String[] args) throws Exception {
        // FixedMultiStack stacks = new FixedMultiStack(4);
        MultiStack stacks = new MultiStack(4);
        stacks.printStacks();
        stacks.push(0, 10);
        stacks.printStacks();
        stacks.push(1, 20);
        stacks.printStacks();
        stacks.push(2, 30);
        stacks.printStacks();

        stacks.push(0, 40);
        stacks.push(0, 50);
        stacks.push(0, 60);
        stacks.printStacks();

        stacks.push(0, 70); // throws FullStackException
        stacks.printStacks();

        stacks.pop(0);
        stacks.printStacks();

        stacks.push(1, 80);
        stacks.push(1, 90);
        stacks.push(1, 100);
        stacks.printStacks();

        stacks.push(1, 110);
        stacks.printStacks();

        stacks.push(2, 120);
        stacks.push(2, 130);
        stacks.printStacks();

        stacks.pop(1);
        stacks.printStacks();

        stacks.push(2, 140);
        stacks.printStacks();

        stacks.push(0, 150); // Throws FullStackException!
    }
}