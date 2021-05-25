/*
 * URLify: Write a method to replace all spaces in a string with '%20'. You may
 * assume that the string has sufficient space at the end to hold the
 * additional characters, and that you are given the "true" length of the
 * string. Note: If implementing in Java, please use a character array so that
 * you can perform this operation in place.
 */
public class URLify {
	public static void urlify(char[] arr, int trueLength) {
		int spaces = 0, pos;
		for (int i = 0; i < trueLength; ++i)
			if (arr[i] == ' ')
				spaces++;
		pos = trueLength + spaces * 2;
		if (pos < arr.length)
			arr[pos] = '\0';
		for (int i = trueLength - 1; i >= 0; --i) {
			if (arr[i] != ' ')
				arr[--pos] = arr[i]; 
			else {
				arr[--pos] = '0';
				arr[--pos] = '2';
				arr[--pos] = '%';
			}
		}
	}
	public static void main(String[] args) {
		String str = "Mr John Smith       ";
		char[] arr = str.toCharArray();
		urlify(arr, 13);
		StringBuilder buffer = new StringBuilder(arr.length);
		for (char ch : arr) {
			if (ch == 0)
				break;
			buffer.append(ch);
		}
		System.out.println("\"" + buffer.toString() + "\"");
	}
}
