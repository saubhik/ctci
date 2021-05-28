package ArraysAndStrings;

// LeetCode: https://leetcode.com/problems/rotate-string
public class StringRotation {
    public static boolean checkRotation(String s2, String s1) {
        String s2s2 = s2 + s2;
        return s1.length() == s2.length() && isSubstring(s1, s2s2);
    }

    public static boolean isSubstring(String s1, String s2) {
        return s2.contains(s1);
    }

    public static void main(String[] args) {
        String s1 = "waterbottle", s2 = "erbottlewat";
        System.out.println(checkRotation(s2, s1));
    }
}
