public class IntegerToRoman {
    /*
     *
     * Subproblem 1:
     * Extract the digits by significance.
     * We can extract the digits and keep in stack.
     * 49 modulo 10 = 9. Push to stack.
     * Divide by 10, get 4.
     * 4 modulo 10 = 4. Push to stack.
     * Also, maintain the number of digits.
     * Since num <= 3999, we can use array of size 4.
     *
     * Subproblem 2:
     * Pop stack. If 4 or 9, handle subtraction.
     * Otherwise, do if else logic.
     */
    public String intToRoman(int num) {
        int[] digits;
        int i;
        int power;
        StringBuilder roman;
        
        digits = new int[4];
        i = 3;
        
        while (num > 0) {
            digits[i--] = num % 10;
            num /= 10;
        }
        
        power = 10000;
        roman = new StringBuilder();
        
        for (i = 0; i < 4; ++i) {
            power /= 10;

            if (digits[i] * power >= 1000) {
                roman.append("M".repeat(digits[i]));
                continue;
            }
            
            if (digits[i] * power >= 500) {
                if (digits[i] == 9) {
                    roman.append("CM");
                    continue;
                } else {
                    roman.append("D");
                    digits[i] -= 5;
                }
            }
            
            if (digits[i] * power >= 100) {
                if (digits[i] == 4) {
                    roman.append("CD");
                    continue;
                } else {
                    roman.append("C".repeat(digits[i]));
                }
                continue;
            }
            
            if (digits[i] * power >= 50) {
                if (digits[i] == 9) {
                    roman.append("XC");
                    continue;
                } else {
                    roman.append("L");
                    digits[i] -= 5;
                }
            }
            
            if (digits[i] * power >= 10) {
                if (digits[i] == 4) {
                    roman.append("XL");
                    continue;
                } else {
                    roman.append("X".repeat(digits[i]));
                }
                continue;
            }
            
            if (digits[i] * power >= 5) {
                if (digits[i] == 9) {
                    roman.append("IX");
                    continue;
                } else {
                    roman.append("V");
                    digits[i] -= 5;
                }
            }
            
            if (digits[i] * power >= 1) {
                if (digits[i] == 4) {
                    roman.append("IV");
                    continue;
                } else {
                    roman.append("I".repeat(digits[i]));
                }
                continue;
            }
        }
        
        return roman.toString();
    }
}