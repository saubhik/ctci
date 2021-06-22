class Solution {
    public int myAtoi(String s) {
        boolean begin = true, negative = false;
        long ans = 0, max = (1 << 31) - 1, min = -(1 << 31);
        for (char ch : s.toCharArray()) {
            System.out.println(negative);
            if (begin) {
                switch (ch) {
                    case ' ': continue;
                    case '-': negative = true;
                    case '+': {
                        begin = false;
                        continue;
                    }
                }
            }
            if ('0' <= ch && ch <= '9') {
                ans = 10 * ans + (ch - '0');
                if (begin) begin = false;
            } else return negative ? (int) -ans : (int) ans;
        }
        if (negative) ans *= -1;
        return (int) Math.max(min, Math.min(max, ans));
    }
}

public class StringToInteger {
    public static void main(String[] args) {
        Solution s = new Solution();
        // System.out.println(s.myAtoi("  -91283472332"));
        // System.out.println(s.myAtoi("3.14159"));
        System.out.println(s.myAtoi("  -0012a42"));
    }
}