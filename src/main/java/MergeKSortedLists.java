import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/merge-k-sorted-lists/description/

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            return null;
        }
        Queue<ListNode> currNodes = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists) {
            if (list != null) {
                currNodes.add(list);
            }
        }
        ListNode resHead = null, resTail = null;
        while (!currNodes.isEmpty()) {
            ListNode minNode = currNodes.poll();
            if (minNode.next != null) {
                currNodes.add(minNode.next);
            }
            if (resHead == null) {
                resHead = minNode;
            } else {
                resTail.next = minNode;
            }
            resTail = minNode;
        }
        return resHead;
    }
}
