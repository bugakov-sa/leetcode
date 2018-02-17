import java.util.*;

//https://leetcode.com/problems/word-ladder-ii/description/
public class WordLadderII {

    private class Graph {
        private List<String> words;
        private BitSet graph;

        public Graph(List<String> words) {
            this.words = words;
            graph = new BitSet(words.size() * words.size());
            for (int i = 0; i < words.size(); i++) {
                for (int j = 0; j < words.size(); j++) {
                    int diffCharCount = 0;
                    for (int k = 0; k < words.get(i).length(); k++) {
                        if (words.get(i).charAt(k) != words.get(j).charAt(k)) {
                            diffCharCount++;
                        }
                    }
                    graph.set(i * words.size() + j, diffCharCount == 1);
                }
            }
        }

        List<String> routes(String word) {
            List<String> res = new ArrayList<>();
            int i = words.indexOf(word);
            if (i < 0) {
                return res;
            }
            for (int j = 0; j < words.size(); j++) {
                if (graph.get(i * words.size() + j)) {
                    res.add(words.get(j));
                }
            }
            return res;
        }
    }

    private class NetLevel {
        private Map<String, Set<String>> nodes = new HashMap<>();

        void addWord(String word) {
            nodes.putIfAbsent(word, new TreeSet<>());
        }

        void addPrevWord(String word, String prevWord) {
            nodes.putIfAbsent(word, new TreeSet<>());
            nodes.get(word).add(prevWord);
        }

        Set<String> getPrevWords(String word) {
            return nodes.getOrDefault(word, Collections.emptySet());
        }

        Set<String> listAllLevelWords() {
            return nodes.keySet();
        }

        boolean containsWord(String word) {
            return nodes.containsKey(word);
        }
    }

    private class Net {
        private List<NetLevel> levels = new ArrayList<>();

        public Net(String rootWord) {
            NetLevel level = new NetLevel();
            level.addWord(rootWord);
            levels.add(level);
        }

        void add(NetLevel level) {
            levels.add(level);
        }

        NetLevel lastLevel() {
            return levels.get(levels.size() - 1);
        }

        int levelsCount() {
            return levels.size();
        }

        NetLevel get(int i) {
            return levels.get(i);
        }
    }

    private Net createRoutesNet(Graph graph, String start, String end) {
        Net net = new Net(start);
        Set<String> lastWords = new HashSet<>();
        lastWords.add(start);
        boolean isLastWordsChanged = true;
        while (isLastWordsChanged) {
            NetLevel netLevel = new NetLevel();
            for (String word : net.lastLevel().listAllLevelWords()) {
                for (String nextWord : graph.routes(word)) {
                    if (!lastWords.contains(nextWord)) {
                        netLevel.addPrevWord(nextWord, word);
                    }
                }
            }
            net.add(netLevel);
            if (netLevel.containsWord(end)) {
                break;
            }
            lastWords.addAll(netLevel.listAllLevelWords());
        }
        return net;
    }

    private List<List<String>> createRoutes(Net net, String end) {
        List<List<String>> routes = new ArrayList<>();
        if (net.lastLevel().containsWord(end)) {
            routes.add(Arrays.asList(end));
            for (int i = net.levelsCount() - 1; i > 0; i--) {
                List<List<String>> newRoutes = new ArrayList<>();
                for (List<String> route : routes) {
                    for (String word : net.get(i).getPrevWords(route.get(0))) {
                        List<String> newRoute = new ArrayList<>();
                        newRoute.add(word);
                        newRoute.addAll(route);
                        newRoutes.add(newRoute);
                    }
                }
                routes = newRoutes;
            }
        }
        return routes;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<String> words = new ArrayList<>(wordList);
        if (!words.contains(beginWord)) {
            words.add(beginWord);
        }
        Graph graph = new Graph(words);
        Net net = createRoutesNet(graph, beginWord, endWord);
        return createRoutes(net, endWord);
    }

    public static void main(String[] args) {
        WordLadderII w = new WordLadderII();
        List<List<String>> res = w.findLadders("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        System.out.println(res);
    }
}
