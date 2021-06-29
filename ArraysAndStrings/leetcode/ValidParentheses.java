import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
	class Solution {
		/*
		 * Time: O(n).
		 * Space: O(n).
		 */
		public boolean isValid(String s) {
			Stack<Character> stack;
			Map<Character, Character> brackets;
			
			stack = new Stack<>();
			brackets = Map.of(')', '(', '}', '{', ']', '[');
			
			for (char ch : s.toCharArray()) {
				if (ch == '(' || ch == '{' || ch == '[') {
					stack.push(ch);
				} else if (stack.isEmpty() || stack.pop() != brackets.get(ch)) {
					return false;
				}
			}
			
			return stack.isEmpty();
		}
	}
}