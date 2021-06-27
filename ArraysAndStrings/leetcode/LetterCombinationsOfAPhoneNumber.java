import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber {
	private List<String> letterCombinations = new ArrayList<>();
	private Map<Character, String> letters = Map.of(
		'2', "abc",
		'3', "def",
		'4', "ghi",
		'5', "jkl",
		'6', "mno",
		'7', "pqrs",
		'8', "tuv",
		'9', "wxyz"
	);
	
	public List<String> letterCombinations(String digits) {
		if (digits.length() == 0) {
			return letterCombinations;
		}
		
		StringBuilder combination = new StringBuilder();
		
		backtrack(combination, digits);
		
		return letterCombinations;
	}
	
	private void backtrack(StringBuilder combination, String digits) {
		int index;
		String lettersInDigit;
		
		index = combination.length();
		
		if (index == digits.length()) {
			letterCombinations.add(combination.toString());
			return;
		}
		
		lettersInDigit = letters.get(digits.charAt(index));
		
		for (char ch : lettersInDigit.toCharArray()) {
			combination.append(ch);
			backtrack(combination, digits);
			combination.setLength(combination.length() - 1);
		}
	}
}
