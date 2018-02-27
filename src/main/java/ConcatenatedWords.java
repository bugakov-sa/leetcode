import java.util.*;
import java.util.stream.Collectors;

public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        return set.stream().filter(word -> isConcatenated(word, set)).collect(Collectors.toList());
    }

    private boolean isConcatenated(String s, Set<String> words) {
        for (int i = 1; i < s.length(); i++) {
            String head = s.substring(0, i);
            if (words.contains(head)) {
                String tail = s.substring(i);
                if (words.contains(tail) || isConcatenated(tail, words)) {
                    return true;
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
