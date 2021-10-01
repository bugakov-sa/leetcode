public class RemoveNthFromEnd {
    class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            ListNode p1 = head, p2 = head;
            int i = 0;
            while(p1.next != null) {
                if( i >= n) {
                    p2 = p2.next;
                }
                p1 = p1.next;
                i++;
            }
            if(p2 == head && i < n) {
                return head.next;
            }
            p2.next = p2.next.next;
            return head;
        }
    }
}
