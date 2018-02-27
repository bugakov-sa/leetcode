public class WildcardMatching {
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
                return isMatch(s.substring(1), p.substring(1));
            case '*':
                if (p.length() == 1) {
                    return true;
                }
                for (int i = 0; i < s.length(); i++) {
                    if (isMatch(s.substring(i), p.substring(1))) {
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
                return isMatch(s.substring(1), p.substring(1));
        }
    }
}
