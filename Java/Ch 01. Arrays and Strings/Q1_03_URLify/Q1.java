package Q1_03_URLify;

/**
 URLify: Write a method to replace all spaces in a string with '%20'. You may assume that the string
 has sufficient space at the end to hold the additional characters, and that you are given the "true"
 length of the string. (Note: If implementing in Java, please use a character array so that you can
 perform this operation in place.)

 EXAMPLE
 Input: "Mr John Smith    ", 13
 Output: "Mr%20John%20Smith"
 *
 * @author cwardgar
 * @since 2017-10-02
 */
public class Q1 {
    public static char[] urlify(char[] a, int origLen) {
        int spaces = 0;
        for (char c : a) {
            if (c == ' ') {
                ++spaces;
            }
        }

        for (int origPos = origLen - 1, newPos = origLen + 2 * spaces - 1; origPos > 0; --origPos, --newPos) {
            if (a[origPos] == ' ') {
                a[newPos] = '0';
                a[--newPos] = '2';
                a[--newPos] = '%';
            } else {
                a[newPos] = a[origPos];
            }
        }

        return a;
    }

    public static void main(String[] args) {
        char[] a = toCharArrayWithLen("Mr John Smith", 17);
        int origLen = 13;
        char[] ret = urlify(a, origLen);
        System.out.println(new String(ret));
    }

    public static char[] toCharArrayWithLen(String s, int len) {
        char[] a = new char[len];
        for (int i = 0; i < s.length(); ++i) {
            a[i] = s.charAt(i);
        }
        return a;
    }
}

