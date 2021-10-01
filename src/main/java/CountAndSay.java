import java.util.HashMap;
import java.util.Map;

public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        String s = countAndSay(n - 1);
        for (int i = 0; i < s.length(); ) {
            int counter = 1;
            int j = i + 1;
            while (j < s.length() && s.charAt(i) == s.charAt(j)) {
                counter++;
                j++;
            }
            sb.append(counter);
            sb.append(s.charAt(i));
            i = j;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        System.out.println(countAndSay.countAndSay(4));
    }
}
