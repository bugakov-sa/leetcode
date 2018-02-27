import java.util.*;

public class ConcatenatedWords {

    private Map<String, Boolean> cache1 = new HashMap<>();
    private Map<String, Boolean> cache2 = new HashMap<>();

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if(!cache2.containsKey(word)){
                cache2.put(word, isConcatenated(word, words, true));
            }
            if (cache2.get(word)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean isConcatenated(String s, String[] words, boolean isFirstString) {
        for (String word : words) {
            if(word.length() > s.length()) {
                return false;
            }
            if ((isFirstString && !word.equals(s)) || !isFirstString) {
                if (0 < word.length() && s.startsWith(word)) {
                    if (word.length() < s.length()) {
                        String substring = s.substring(word.length());
                        if(!cache1.containsKey(substring)){
                            cache1.put(substring, isConcatenated(substring, words, false));
                        }
                        if (cache1.get(substring)) {
                            return true;
                        }
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new ConcatenatedWords().findAllConcatenatedWordsInADict(new String[]{
                "cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"
        }));
    }
}
