import java.util.Arrays;


public class CheckPermutation {
	public static String sort(String s) {
		char[] content = s.toCharArray();
		Arrays.sort(content);
		return new String(content);
	}
	public static boolean checkPermutation1(String str1, String str2) {
		return sort(str1).equals(sort(str2));
	}
	/* We assume ASCII here. This uses character counts. */
	public static boolean checkPermutation2(String str1, String str2) {
		if (str1.length() != str2.length())
			return false;
		int[] charCounts = new int[128];
		for (int i = 0; i < str1.length(); ++i)
			charCounts[str1.charAt(i)]++;
		for (int i = 0; i < str2.length(); --i)
			charCounts[str2.charAt(i)]--;
		for (int i = 0; i < 128; ++i)
			if (charCounts[i] != 0)
				return false;
		return true;
	}
	public static void main(String[] args) {
		String[][] pairs = {{ "apple", "papel" }, { "carrot", "tarroc" }, {
			"hello", "llloh" }};
		for (String[] pair : pairs) {
			System.out.println(pair[0] + "," + pair[1] + ": " +
					checkPermutation1(pair[0], pair[1]));
		}
	}
}
