public class StringToInteger {
    public int myAtoi(String s) {
        int ans;
        int digit;
        boolean begin;
        boolean negative;
        boolean sign;
        
        ans = 0;
        begin = true;
        negative = false;
        sign = false;
        
        for (char ch : s.toCharArray()) {
            if (begin && ch == ' ') {
                continue;
            }
            
            if (!sign && begin && ch == '-') {
                negative = true;
                sign = true;
                begin = false;
                continue;
            }
            
            if (!sign && begin && ch == '+') {
                sign = true;
                begin = false;
                continue;
            }
            
            if (Character.isDigit(ch)) {
                if (begin) {
                    begin = false;
                }
                
                if (ans < Integer.MIN_VALUE / 10) {
                    return Integer.MIN_VALUE;
                }
                
                if (ans > Integer.MAX_VALUE / 10) {
                    return Integer.MAX_VALUE;
                }
                
                ans *= 10;       
                digit = ch - '0';
                
                if (negative) {
                    if (ans < Integer.MIN_VALUE + digit) {
                        return Integer.MIN_VALUE;
                    }
                    
                    ans -= digit;
                } else {
                    if (ans > Integer.MAX_VALUE - digit) {
                        return Integer.MAX_VALUE;
                    }
                    
                    ans += digit;
                }
                
            } else {
               break;
            }
        }
        
        return ans;
    }
}
