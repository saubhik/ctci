package LinkedLists;

import java.util.Stack;

/*
 * Palindrome: Implement a function to check if a linked list is a palindrome.
 */
public class Palindrome {
    static class Solution1 {
        /* Solution 1: Reverse and Compare */
        public static boolean palindrome(LinkedListNode node) {
            ListMeta listMeta = reverseAndClone(node);
            return checkListsEqual(node, listMeta.node, listMeta.length);
        }

        static class ListMeta {
            public LinkedListNode node;
            public int length;
        }

        public static ListMeta reverseAndClone(LinkedListNode node) {
            int length = 0;
            LinkedListNode head = null;
            while (node != null) {
                head = new LinkedListNode(node.data, head);
                node = node.next;
            }

            ListMeta listMeta = new ListMeta();
            listMeta.length = length;
            listMeta.node = head;

            return listMeta;
        }

        public static boolean checkListsEqual(LinkedListNode node1,
                                              LinkedListNode node2,
                                              int lengthToCheck) {
            /* Check only first half of both lists. */
            while (node1 != null && node2 != null && lengthToCheck > 0) {
                if (node1.data != node2.data)
                    return false;

                node1 = node1.next;
                node2 = node2.next;
                lengthToCheck -= 2;
            }
            return true;
        }
    }

    static class Solution2 {
        /* Iterative approach. */
        public static boolean palindrome(LinkedListNode node) {
            LinkedListNode slowPointer = node, fastPointer = node;
            Stack<Integer> stack = new Stack<>();
            while (fastPointer != null && fastPointer.next != null) {
                stack.push(slowPointer.data);
                slowPointer = slowPointer.next;
                fastPointer = fastPointer.next.next;
            }

            /* Skip the mid element if list is odd length. */
            if (fastPointer != null)
                slowPointer = slowPointer.next;

            while (slowPointer != null) {
                if (stack.pop() != slowPointer.data)
                    return false;
                slowPointer = slowPointer.next;
            }

            return true;
        }
    }

    static class Solution3 {
        /* Recursive Approach. */
        public static boolean palindrome(LinkedListNode node) {
            // 0 1 2 1 0, 5
            //   1 2 1 0, 3
            //     2 1 0, 1
            //       1 0, -1
            Result result = helper(node, getListLength(node));
            return result.check;
        }

        static class Result {
            public LinkedListNode node;
            public boolean check;

            public Result(LinkedListNode node, boolean check) {
                this.node = node;
                this.check = check;
            }
        }

        public static Result helper(LinkedListNode node, int length) {
            if (length <= 0)
                return new Result(node, true);

            if (length == 1)
                return new Result(node.next, true);

            Result result = helper(node.next, length - 2);
            if (!result.check || result.node.data != node.data)
                return new Result(node.next, false);

            return new Result(result.node.next, true);
        }

        public static int getListLength(LinkedListNode node) {
            int length = 0;
            while (node != null) {
                length++;
                node = node.next;
            }
            return length;
        }
    }

    public static void main(String[] args) {
        LinkedListNode node = new LinkedListNode(0, new LinkedListNode(1,
                new LinkedListNode(2, new LinkedListNode(1,
                        new LinkedListNode(0, null)))));

        System.out.println(Solution1.palindrome(node));
        System.out.println(Solution2.palindrome(node));
        System.out.println(Solution3.palindrome(node));
    }
}
