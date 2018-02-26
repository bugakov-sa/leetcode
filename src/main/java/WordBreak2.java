import java.util.*;

public class WordBreak2 {

    private Map<String, List<String>> stringSentences = new HashMap<>();

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> res = new ArrayList<>();
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (word.length() < s.length()) {
                    String substring = s.substring(word.length());
                    if (!stringSentences.containsKey(substring)) {
                        stringSentences.put(substring, wordBreak(substring, wordDict));
                    }
                    for (String sentence : stringSentences.get(substring)) {
                        res.add(word + " " + sentence);
                    }
                } else {
                    res.add(word);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> dict = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(new WordBreak2().wordBreak(s, dict));
    }
}
