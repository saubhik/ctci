import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
	class Solution {
		public int romanToInt(String s) {
			int ans;
			
			ans = 0;
			
			for (int i = 0; i < s.length(); ++i) {
				switch (s.charAt(i)) {
					case 'I':
						if (i+1 < s.length() && s.charAt(i+1) == 'V') {
							ans += 4;
							++i;
						} else if (i+1 < s.length() && s.charAt(i+1) == 'X') {
							ans += 9;
							++i;
						} else {
							ans += 1;
						}
						break;
					case 'V':
						ans += 5;
						break;
					case 'X':
					   if (i+1 < s.length() && s.charAt(i+1) == 'L') {
							ans += 40;
							++i;
						} else if (i+1 < s.length() && s.charAt(i+1) == 'C') {
							ans += 90;
							++i;
						} else {
							ans += 10;
						}
						break;
					case 'L':
						ans += 50;
						break;
					case 'C':
					   if (i+1 < s.length() && s.charAt(i+1) == 'D') {
							ans += 400;
							++i;
						} else if (i+1 < s.length() && s.charAt(i+1) == 'M') {
							ans += 900;
							++i;
						} else {
							ans += 100;
						}
						break;
					case 'D':
						ans += 500;
						break;
					case 'M':
						ans += 1000;
						break;
				}
			}
			
			return ans;
		}
	}

	static class SolutionTwo {
		/*
		 * Elegant right to left pass. Note that roman characters
		 * increase from right to left, except for the 6
		 * subtraction cases.
		 */
		static Map<Character, Integer> values = new HashMap<>();
		
		static {
			values.put('I', 1);
			values.put('V', 5);
			values.put('X', 10);
			values.put('L', 50);
			values.put('C', 100);
			values.put('D', 500);
			values.put('M', 1000);
		}
	
		public int romanToInt(String s) {
			int ans;
			int currentValue;
			int lastValue;
			
			ans = 0;
			lastValue = -1;
	
			for (int i = s.length() - 1; i >= 0; --i) {
				currentValue = values.get(s.charAt(i));
				
				if (currentValue < lastValue) {
					ans -= currentValue;
				} else {
					ans += currentValue;
				}
				
				lastValue = currentValue;
			}
			
			return ans;
		}
	}
}
