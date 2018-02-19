import java.util.HashMap;
import java.util.Map;

class LFUCache {

    private class DataNode {
        int key;
        int value;
        int frequency;
        DataNode prev;
        DataNode next;
    }

    private Map<Integer, DataNode> map = new HashMap<>();
    private DataNode leastUsed;
    private int capacity;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    private void sordNodesByFrequency(DataNode node) {
        while (node.next != null && node.next.frequency <= node.frequency) {
            DataNode nextNode = node.next;
            DataNode prevNode = node.prev;
            node.next = nextNode.next;
            if (node.next != null) {
                node.next.prev = node;
            }
            if (prevNode != null) {
                prevNode.next = nextNode;
            }
            nextNode.prev = prevNode;
            nextNode.next = node;
            node.prev = nextNode;
            if (leastUsed == node) {
                leastUsed = nextNode;
            }
        }
    }

    private void incFrequency(int key) {
        if (map.containsKey(key)) {
            map.get(key).frequency++;
        }
    }

    public int get(int key) {
        incFrequency(key);
        DataNode node = map.get(key);
        if (node == null) {
            return -1;
        }
        sordNodesByFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if(capacity < 1) {
            return;
        }
        incFrequency(key);
        if (map.containsKey(key)) {
            DataNode node = map.get(key);
            node.value = value;
            sordNodesByFrequency(node);
        } else if (map.size() < capacity) {
            simplePut(key, value);
        } else {
            map.remove(leastUsed.key);
            leastUsed = leastUsed.next;
            if (leastUsed != null) {
                leastUsed.prev = null;
            }
            simplePut(key, value);
        }
    }

    private void simplePut(int key, int value) {
        DataNode node = new DataNode();
        node.key = key;
        node.value = value;
        if (leastUsed == null) {
            leastUsed = node;
        } else {
            node.next = leastUsed;
            leastUsed.prev = node;
            leastUsed = node;
        }
        map.put(key, node);
        sordNodesByFrequency(node);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        System.out.println(cache.get(4));
        System.out.println(cache.get(3));
        System.out.println(cache.get(2));
        System.out.println(cache.get(1));
    }
}