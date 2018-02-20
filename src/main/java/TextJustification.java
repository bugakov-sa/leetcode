import java.util.ArrayList;
import java.util.List;

public class TextJustification {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < words.length; ) {
            int minWidth = words[i].length();
            int startI = i;
            for (i++; i < words.length; i++) {
                int newWidth = minWidth + 1 + words[i].length();
                if (newWidth <= maxWidth) {
                    minWidth = newWidth;
                } else {
                    break;
                }
            }
            res.add(concat(words, startI, i, minWidth, maxWidth, i == words.length));
        }
        return res;
    }

    private String concat(String[] words, int startI, int endI, int minWidth, int maxWidth, boolean lastRow) {
        if (lastRow || (startI + 1 == endI)) {
            String row = words[startI];
            for (int i = startI + 1; i < endI; i++) {
                row += " " + words[i];
            }
            while (row.length() < maxWidth) {
                row += " ";
            }
            return row;
        }
        int spaces = endI - startI - 1;
        int n = (maxWidth - minWidth) / spaces;
        int m = (maxWidth - minWidth) % spaces;
        String space = "";
        for (int i = 0; i <= n; i++) {
            space += " ";
        }
        String res = words[startI];
        for (int i = 0; i < spaces; i++) {
            res += space;
            if (i < m) {
                res += " ";
            }
            res += words[startI + 1 + i];
        }
        return res;
    }

    public static void main(String[] args) {
        new TextJustification().fullJustify(new String[]{
                "This", "is", "an", "example", "of", "text", "justification."
        }, 16).stream().forEach(s -> System.out.println("-" + s + "-"));
    }
}
