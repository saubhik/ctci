class Solution {
    /*
     * 1234567891
     * In each iteration, take modulo 10.
     * Iteration 0. One.
     * Iteration 1. Ninety. If it was 1, we need special treatment with next digit.
     * Iteration 2. Eight Hundred.
     * Iteration 3. Seven Thousand.
     * Iteration 4. Sixty.
     * Iteration 5. Five Hundred.
     * Iteration 6. Four Million.
     * Iteration 7. Thirty.
     * Iteration 8. Two Hundred.
     * Iteration 9. One Billion.
     * We can keep prepending LinkedList<String>.
     */
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        
        LinkedList<String> words = new LinkedList<>();
        
        String[] terms = new String[]{ 
            "", "Thousand", "Million", "Billion"
        };
        
        String[] digitToWord = new String[]{
            "", "One", "Two", "Three", "Four", 
            "Five", "Six", "Seven", "Eight", 
            "Nine"
        };
        
        String[] tenthDigitToWord = new String[]{
            "", "", "Twenty", "Thirty", "Forty", 
            "Fifty", "Sixty", "Seventy", "Eighty", 
            "Ninety"
        };
        
        String[] oneTenthDigitToWord = new String[] {
            "Ten", "Eleven", "Twelve", "Thirteen", 
            "Fourteen", "Fifteen", "Sixteen", 
            "Seventeen", "Eighteen", "Nineteen"
        };
        
        for (int power = 0, digit = -1, lastDigit = -1, secondLastDigit; num > 0; num /= 10, ++power) {
            secondLastDigit = lastDigit;
            lastDigit = digit;
            digit = num % 10;
            if (digit == 0) continue;
            switch (power % 3) {
                case 0 -> {
                    words.addFirst(terms[power/3]);
                    words.addFirst(digitToWord[digit]);
                }
                case 1 -> {
                    if (lastDigit == 0) words.addFirst(terms[power/3]);
                    if (digit == 1) {
                        if (lastDigit > 0) words.poll();
                        words.addFirst(oneTenthDigitToWord[lastDigit]);
                    } else words.addFirst(tenthDigitToWord[digit]);
                }
                case 2 -> {
                    if (lastDigit == 0 && secondLastDigit == 0) words.addFirst(terms[power/3]);
                    words.addFirst("Hundred");
                    words.addFirst(digitToWord[digit]);
                }
            };
        }
        
        return String.join(" ", words).trim();
    }
}
