package LinkedLists;

import java.util.HashSet;
import java.util.ArrayList;

/*
 * Remove Dups: Write code to remove duplicates from an unsorted linked list.
 * How would you solve this problem if a temporary buffer is not allowed?
 */
public class RemoveDups {
    /*
     * Remove duplicates using a hashset.
     * Time: O(n)
     * Space: O(n)
     */
    public static void removeDups1(LinkedListNode node) {
        HashSet<Integer> set = new HashSet<>();
        LinkedListNode pre = null;
        while (node != null) {
            if (!set.contains(node.data)) {
                set.add(node.data);
                pre = node;
            } else
                // Remove current node.
                // pre does not change.
                pre.next = node.next;
            node = node.next;
        }
    }

    /*
     * Remove duplicates with constant space.
     * Time: O(n^2)
     * Space: O(1)
     */
    public static void removeDups2(LinkedListNode head) {
        LinkedListNode current = head, runner, pre;
        while (current != null) {
            pre = current;
            runner = current.next;
            while (runner != null) {
                if (runner.data == current.data) {
                    pre.next = runner.next;
                } else pre = runner;
                runner = runner.next;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(12), node;
        node = head;
        for (int i : new int[]{12, 13, 12, 1, 13, 23}) {
            node.next = new LinkedListNode(i);
            node = node.next;
        }
        removeDups2(head);
        ArrayList<Integer> listData = new ArrayList<>();
        node = head;
        while (node != null) {
            listData.add(node.data);
            node = node.next;
        }
        System.out.println(listData);
    }
}
