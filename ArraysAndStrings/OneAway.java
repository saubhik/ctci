package ArraysAndStrings;

/*
 * One Away: There are 3 types of edits that can be performed on strings:
 * insert a character, remove a character, or replace a character. Given two
 * strings, write a function to check if they are one edit (or zero edits)
 * away.
 * LeetCode: https://leetcode.com/problems/one-edit-distance
 */
public class OneAway {
	/*
	 * Check if s is one or more edits away from t.  Note the following:
	 * - Inserting a character increases length by 1. This is the opposite of
	 *   removing a character.
	 * - Replacing a character keeps the length same.
	 */
	public static boolean oneEditAway(String s, String t) {
		// Add || s.equals(t) condition for the LeetCode problem.
		if (Math.abs(s.length() - t.length()) > 1)
			return false;
		// else if (s.length() > t.length())
		// 	return oneInsert(t, s);
		// else if (s.length() < t.length())
		// 	return oneInsert(s, t);
		// else
		// 	return oneReplace(s, t);
		return s.length() < t.length() ? oneEdit(s, t) : oneEdit(t, s);
	}

	public static boolean oneInsert(String s1, String s2) {
		// s2.length() > s1.length().
		int ind1 = 0, ind2 = 0;
		while (ind1 < s1.length() && ind2 < s2.length()) {
			if (s1.charAt(ind1) != s2.charAt(ind2)) {
				if (ind1 != ind2)
					return false;
				++ind2;
			} else {
				++ind1;
				++ind2;
			}
		}
		return true;
	}

	public static boolean oneReplace(String s1, String s2) {
		// s1.length() == s2.length().
		int ind1 = 0, ind2 = 0;
		boolean replaceDone = false;
		while (ind1 < s1.length() && ind2 < s2.length()) {
			if (s1.charAt(ind1) != s2.charAt(ind2)) {
				if (replaceDone)
					return false;
				replaceDone = true;
			}
			++ind1;
			++ind2;
		}
		return true;
	}

	// Combine the above two methods.
	public static boolean oneEdit(String s1, String s2) {
		int ind1 = 0, ind2 = 0;
		boolean replaceDone = false;
		while (ind1 < s1.length() && ind2 < s2.length()) {
			if (s1.charAt(ind1) != s2.charAt(ind2)) {
				if (replaceDone || ind1 != ind2)
					return false;
				replaceDone = true;
				if (s1.length() == s2.length())
					++ind1;
				++ind2;
			} else {
				++ind1;
				++ind2;
			}
		}
		return true;
	}

    public static void main(String[] args) {
		// Case 1.
		String a = "palee", b = "pale";
		System.out.println(a + ", " + b + ": " + oneEditAway(a, b));

		// Case 2.
		a = "pale";
		b = "pkle";
		System.out.println(a + ", " + b + ": " + oneEditAway(a, b));

		// Case 3.
		a = "ab";
		b = "c";
		System.out.println(a + ", " + b + ": " + oneEditAway(a, b));
    }
}
