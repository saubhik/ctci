/*
 * 1.1 Implement an algorithm to determine if a string has all unique
 * characters. What if you cannot use additional data structures?
 */
public class IsUnique {
	/* This is with the array data structure. */
	public static boolean isUniqueChars1(String word) {
		if (word.length() > 128)
			return false;
		boolean[] charSet = new boolean[128];
		for (int i = 0; i < word.length(); ++i) {
			int val = word.charAt(i);
			if (charSet[val])
				return false;
			else
				charSet[val] = true;
		}
		return true;
	}

	/* Without any data structures. Assume only characters 'a' to 'z' in word.
	 */
	public static boolean isUniqueChars2(String word) {
		if (word.length() > 26)
			return false;
		int checker = 0;
		for (int i = 0; i < word.length(); ++i) {
			int val = word.charAt(i) - 'a';
			if ((checker & (1 << val)) > 0)
				return false;
			checker |= 1 << val;
		}
		return true;
	}

	public static void main(String[] args) {
		String[] words = { "abcde", "hello", "apple", "kite", "padle" };
		for (String word : words) {
			System.out.println(word + ":" + isUniqueChars1(word));
			System.out.println(word + ":" + isUniqueChars2(word));
		}
	}
}
