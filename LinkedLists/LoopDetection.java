package LinkedLists;

/*
 * Loop Detection: Given a circular linked list, implement an algorithm that returns the node at the beginning of the
 * loop.
 * INPUT: A -> B -> C -> D -> E -> C
 * OUTPUT: C
 *
 * LeetCode: https://leetcode.com/problems/linked-list-cycle-ii/submissions/
 */
public class LoopDetection {
    /*
     * Use FastRunner and SlowRunner pointers to detect loop. If length of non loop region is k, SlowRunner is k steps
     * behind FastRunner. If the cycle length is less than k, then consider K = k % CYCLE_LENGTH. In other words,
     * FastRunner is CYCLE_LENGTH - K steps behind SlowRunner. FastRunner closes the gap 1 node per step. So, it takes
     * SlowRunner will be CYCLE_LENGTH - K steps into the loop when both meet. We reset FastRunner to head node, and
     * move each pointer by 1 node at a time. FastRunner will traverse k steps, and SlowRunner will traverse
     * (CYCLE_LENGTH - K) + k, and will be at first node of the loop and meet FastRunner there.
     */
    public static LinkedListNode getLoopBeginning(LinkedListNode head) {
        LinkedListNode fastRunner = head, slowRunner = head;
        while (fastRunner != null && fastRunner.next != null) {
            slowRunner = slowRunner.next;
            fastRunner = fastRunner.next.next;
            if (slowRunner == fastRunner)
                break;
        }

        if (fastRunner == null || fastRunner.next == null)
            return null;

        fastRunner = head;

        while (slowRunner != fastRunner) {
            fastRunner = fastRunner.next;
            slowRunner = slowRunner.next;
        }

        return slowRunner;
    }

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(1, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(4, new LinkedListNode(5)))));
        node.next.next.next.next.next = node.next.next;

        LinkedListNode loopBeginningNode = getLoopBeginning(node);

        System.out.println(loopBeginningNode.data);
    }
}
