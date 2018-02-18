import java.util.LinkedHashMap;
//https://leetcode.com/problems/lru-cache/description/
class LRUCache {

    private final LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key);
            map.remove(key);
            map.put(key, value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
            return;
        } else if (map.size() < capacity) {
            map.put(key, value);
        } else {
            int removingKey = map.keySet().iterator().next();
            map.remove(removingKey);
            map.put(key, value);
        }
    }
}