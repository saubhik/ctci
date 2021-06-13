package StacksAndQueues;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/*
 * Queue via Stacks: Implement a MyQueue class which
 * implements a queue using two stacks.
 */
public class QueueViaStacks<T> {
    Stack<T> stackNewest, stackOldest;

    public QueueViaStacks() {
        stackNewest = new Stack<>();
        stackOldest = new Stack<>();
    }

    public int size() {
        return stackNewest.size() + stackOldest.size();
    }

    public void add(T value) {
        stackNewest.push(value);
    }

    public T remove() {
        shiftStacks();
        return stackOldest.pop();
    }

    public T peek() {
        shiftStacks();
        return stackOldest.peek();
    }

    public void shiftStacks() {
        if (stackOldest.empty()) {
            while (!stackNewest.isEmpty()) {
                stackOldest.push(stackNewest.pop());
            }
        }
    }

    public static void main(String[] args) {
        QueueViaStacks<Integer> qvs = new QueueViaStacks<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < 100; ++i) {
            qvs.add(i);
            q.add(i);
            
            if (i % 10 == 1) {
                System.out.println(
                    "qvs.remove: " + qvs.remove() + 
                    ", q.remove: " + q.remove());
                System.out.println(
                    "qvs.peek: " + qvs.peek() +
                    ", q.peek: " + q.peek());
                System.out.println(
                    "qvs.size: " + qvs.size() + 
                    ", q.size: " + q.size());
            }
        }
    }
}