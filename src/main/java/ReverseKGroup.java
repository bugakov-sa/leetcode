/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
//https://leetcode.com/problems/reverse-nodes-in-k-group/description/
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode resHead = null, resTail = null;
        ListNode localHead = null, localTail = null;
        int i = 0;
        while (head != null) {
            if (localTail == null) {
                localTail = head;
            }
            ListNode next = head.next;
            head.next = localHead;
            localHead = head;
            head = next;
            i++;
            if (i == k) {
                if (resHead == null) {
                    resHead = localHead;
                }
                if (resTail != null) {
                    resTail.next = localHead;
                }
                resTail = localTail;
                localHead = null;
                localTail = null;
                i = 0;
            }
        }
        if (localHead != null) {
            ListNode prevNode = null;
            while (localHead != null) {
                ListNode nextNode = localHead.next;
                localHead.next = prevNode;
                prevNode = localHead;
                localHead = nextNode;
            }
            if (resTail != null) {
                resTail.next = prevNode;
            } else {
                resHead = prevNode;
            }
        }
        return resHead;
    }

    private static void print(ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();
    }

    private static ListNode list(int n) {
        ListNode head = new ListNode(0);
        ListNode node = head;
        for (int i = 1; i < n; i++) {
            ListNode next = new ListNode(i);
            node.next = next;
            node = next;
        }
        return head;
    }

    public static void main(String[] args) {
        int k = 3;
        for (int m = 1; m < 10; m++) {
            print(new ReverseKGroup().reverseKGroup(list(m), k));
        }
    }
}
