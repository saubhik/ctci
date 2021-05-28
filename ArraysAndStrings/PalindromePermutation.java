package ArraysAndStrings;

/*
 * Palindrome Permutation: Given a string, write a function to check if it is a
 * permutation of a palindrome. A palindrome is a word or phrase that is the
 * same forwards and backwards. A permutation is a rearrangement of letters.
 * The palindrome does not need to be limited to just dictionary words.
 * LeetCode: https://leetcode.com/problems/palindrome-permutation/
 */
public class PalindromePermutation {
	// This is O(n) time.
	public static boolean isPalindromePermutation1(String str) {
		int checker = 0;	
		for (int i = 0; i < str.length(); ++i)
			checker ^= 1 << (str.charAt(i) - 'a'); 
		return (checker & (checker - 1)) == 0;
	}
	// This is O(n) time.
	public static boolean isPalindromePermutation2(String str) {
		int[] table = buildCharFrequencyTable(str);
		return checkMaxOneOdd(table);
	}
	// This is O(n) time.
	public static boolean isPalindromePermutation3(String str) {
		int countOdd = 0;
		int[] table = new int[Character.getNumericValue('z') -
			Character.getNumericValue('a') + 1];
		for (char ch : str.toCharArray()) {
			int x = getCharNumber(ch);
			if (x != -1) {
				table[x]++;
				if (table[x] % 2 == 1)
					countOdd++;
				else
					countOdd--;
			}
		}
		return countOdd <= 1;
	}
	// This is O(n) time.
	public static boolean isPalindromePermutation4(String str) {
		int bitVector = createBitVector(str);
		return bitVector == 0 || checkExactlyOneBitSet(bitVector);
	}
	public static int createBitVector(String str) {
		int bitVector = 0;
		for (char ch : str.toCharArray()) {
			int x = getCharNumber(ch);
			bitVector = toggle(bitVector, x);
		}
		return bitVector;
	}
	public static int toggle(int bitVector, int index) {
		if (index < 0)
			return bitVector;
		int mask = 1 << index;
		if ((bitVector & mask) == 0) {
			bitVector |= mask;
		} else {
			bitVector &= ~mask;
		}
		return bitVector;
	}
	public static boolean checkExactlyOneBitSet(int bitVector) {
		return (bitVector & (bitVector - 1)) == 0;
	}
	public static boolean checkMaxOneOdd(int[] table) {
		boolean foundOdd = false;
		for (int count : table)
			if (count % 2 == 1) {
				if (foundOdd)
					return false;
				foundOdd = true;
			}
		return true;
	}
	public static int getCharNumber(Character c) {
		int a = Character.getNumericValue('a');
		int z = Character.getNumericValue('z');
		int val = Character.getNumericValue(c);
		if (a <= val && val <= z)
			return val - a;
		return -1;
	}
	public static int[] buildCharFrequencyTable(String str) {
		/* Character.getNumericValue() maps each character to a number. This is
		 * case insensitive. Non-letter characters map to -1. */
		int[] table = new int[Character.getNumericValue('z') -
			Character.getNumericValue('a') + 1];
		for (char ch: str.toCharArray()) {
			int x = getCharNumber(ch);
			if (x != -1)
				table[x]++;
		}
		return table;
	}
	public static void main(String[] args) {
		String str = "Rats live on no evil star";
		System.out.println(isPalindromePermutation1(str));
		System.out.println(isPalindromePermutation2(str));
		System.out.println(isPalindromePermutation3(str));
		System.out.println(isPalindromePermutation4(str));
	}
}
