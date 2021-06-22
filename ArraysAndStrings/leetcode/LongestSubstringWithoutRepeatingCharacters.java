import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
    /*
     * Example: "pwwkew"
     * We can maintain a char list. Check if char does not
     * belong to char list, keep incrementing.
     * Step 1: "p", 1, 1
     * Step 2: "pw", 2, 2
     * Step 3: "w", 1, 2
     * Step 4: "wk", 2, 2
     * Step 5: "wke", 3, 3
     * Step 6: "kew", 3, 3
     * In step 6, we check the position of 'w' in the char
     * list, and throw the left sub-list.
     */
    private static class Node {
        public char data;
        public Node next = null;
        public Node(char data) {
            this.data = data;
        };
    }
    
    public void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.next;
        }
        System.out.println();
    }

    /*
     * Two pointers approach using hashmap.
     * pwwkew
     * 012345
     * i=0,j=0
     * i=0,j=1
     * i=2,j=2
     * i=2,j=3
     * i=2,j=4
     * i=3,j=5
     */
    public int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        for (int en = 0, st = 0; en < s.length(); ++en) {
            if (map.containsKey(s.charAt(en))) {
                st = Math.max(map.get(s.charAt(en)) + 1, st);
            }
            map.put(s.charAt(en), en);
            ans = Math.max(ans, en - st + 1);
        }
        return ans;
    }

    public int lengthOfLongestSubstring(String s) {
        Node head = new Node('0');
        Node curr;
        Node tail = head;
        
        int ans = 0;
        int length = 0;
        int counter;
        
        for (char ch : s.toCharArray()) {
            /* Find if ch exists in list. */
            curr = head.next;
            counter = 1;
            while (curr != null) {
                if (curr.data == ch) {
                    length -= counter;
                    break;
                }
                curr = curr.next;
                ++counter;
            }
            
            /* Make curr.next as head. */
            if (curr != null)
                head = curr;
            
            /* Append ch to list. */
            tail.next = new Node(ch);
            tail = tail.next;
            ++length;
            
            // printList(head);
            // System.out.println(length);

            ans = Math.max(length, ans);
        }
        return ans;
    }
}