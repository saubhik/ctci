import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacterString {
    /*
     * We can maintain a hashmap. First pass, we update the counts.
     * In the second pass, we return the index of first character that has count = 1.
     *
     * We could only use 2 bits to maintain that state:
     * 00: not encountered
     * 01: encountered once
     * 11: encountered more than once
     *
     * Time Complexity: O(n)
     * Space Complexity: O(26) = O(1)
     */
    public int firstUniqChar(String s) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        for (int i = 0; i < s.length(); ++i)
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);        
        
        for (int i = 0; i < s.length(); ++i)
            if (map.get(s.charAt(i)) == 1)
                return i;
        
        return -1;
    }
}
