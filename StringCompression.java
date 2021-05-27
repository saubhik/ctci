/*
 * String Compression: Implement a method to perform basic string compression
 * using the counts of repeated characters. For example, the string
 * "aabcccccaaa" would become "a2b1c5a3". If the compressed string would not
 * become smaller than the original string, your method should return the
 * original string. You can assume the string has only uppercase and
 * lowercase letters (a-z).
 */
public class StringCompression {
    public static String compressed(String str) {
        int charCount = 0;
        StringBuilder compressed = new StringBuilder();
        for (int i = 0; i < str.length(); ++i) {
            charCount++;
            if ((i == str.length() - 1)
                    || (str.charAt(i) != str.charAt(i + 1))) {
                compressed.append(str.charAt(i));
                compressed.append(charCount);
                charCount = 0;
            }
        }
        return compressed.length() < str.length() ? compressed.toString() : str;
    }

    public static void main(String[] args) {
        String str = "aaaaabbbbaaaabbddc";
        System.out.println(str + ":\"" + compressed(str) + "\"");
        str = "abc";
        System.out.println(str + ":\"" + compressed(str) + "\"");
    }
}
