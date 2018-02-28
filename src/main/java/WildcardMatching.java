import java.util.HashMap;
import java.util.Map;

public class WildcardMatching {

    private Map<String, Map<String, Boolean>> cache = new HashMap<>();

    private boolean cachingIsMatch(String s, String p) {
        if (p.startsWith("*")) {
            for (String s1 : cache.keySet()) {
                if (s1.endsWith(s)) {
                    if(cache.get(s1).containsKey(p)) {
                        return cache.get(s1).get(p);
                    }
                }
            }
        }
        if (!cache.containsKey(s) || !cache.get(s).containsKey(p)) {
            cache.putIfAbsent(s, new HashMap<>());
            cache.get(s).put(p, isMatch(s, p));
        }
        return cache.get(s).get(p);
    }

    public boolean isMatch(String s, String p) {
        while (p.contains("**")) {
            p = p.replaceAll("\\*\\*", "*");
        }
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        switch (p.charAt(0)) {
            case '?':
                if (s.isEmpty()) {
                    return false;
                }
                return cachingIsMatch(s.substring(1), p.substring(1));
            case '*':
                if (p.length() == 1) {
                    return true;
                }
                for (int i = 0; i < s.length(); i++) {
                    if (cachingIsMatch(s.substring(i), p.substring(1))) {
                        return true;
                    }
                }
                return false;
            default:
                if (s.isEmpty()) {
                    return false;
                }
                if (p.charAt(0) != s.charAt(0)) {
                    return false;
                }
                return cachingIsMatch(s.substring(1), p.substring(1));
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        new WildcardMatching().isMatch(
                "aabbbbaababbabababaabbbbabbabbaabbbabbbabaabbaaaababababbababbabbbbabaaabaaabaabbaaaabbbbabaaabbbbbabbbaabbbbbabaabababaaabaaababaababbaaabaabbabaababbabababaaababbabbabaabbbbabbbbabaabbaababaaabababbab",
                "a*b*a*b*aaaa*abaaa**b*a***b*a*bb****ba*ba*b******a********a**baba*ab***a***bbba*b**a*b*ba*a*aaaa*ab**");
        System.out.println(System.currentTimeMillis() - start);
    }
}
