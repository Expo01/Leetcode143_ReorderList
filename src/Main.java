public class Main {
    public static void main(String[] args) {
        ListNode hi = new ListNode(1);
        ListNode pass = hi;
        hi.next = new ListNode(2);
        hi = hi.next;
        hi.next = new ListNode(3);
        hi = hi.next;
        hi.next = new ListNode(4);
        hi = hi.next;
        hi.next = new ListNode(5);

        new Solution().reorderList(pass);

    }
}
// i was super close...
class Solution {
    public void reorderList(ListNode head) {
        if (head == null) return;

        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
// do not assign slow.next as null. need it to point to its next node but neext node will pooint back to null

        ListNode prev = null, curr = slow, tmp;
        while (curr != null) {
            tmp = curr.next;

            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        ListNode first = head, second = prev;
        while (second.next != null) {
            tmp = first.next;
            first.next = second;
            first = tmp;

            tmp = second.next;
            second.next = first;
            second = tmp;
        }
    }// loop terminates where last node from 1st half already points to new tail in second half so don't need to add final
    // pointer to that node
}


class Solution {
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null){ // find middle to split into halves
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;

        ListNode prev = null;
        ListNode after = head2;
        while(head2 != null){ // reverse 'halves'
            after = head2.next;
            head2.next = prev;
            prev = head2;
            head2 = after;
        }

        ListNode dummy = new ListNode(0), mergeTemp = dummy; // merge
        while(head != null && prev != null){
            mergeTemp.next = head;
            mergeTemp = mergeTemp.next;
            mergeTemp.next = prev;
            mergeTemp = mergeTemp.next;
            head = head.next;
            prev = prev.next;
        } // ultimately head will equal final node if odd original list and prev will = null. mergeTemp = final
        // node from smaller half. must add final node from 'long half'
        if(head != null){
            mergeTemp.next = head;
        }
    }
}

// basically need to add from ends inwarrd L R L R
// find middle, split, reverse second half, merg. will want prev = dummy so can loop
// prev.next = l1. l1.next = l2. prev = l2 with condition of L1 and L2 != null. then if null add last node
// this would happen with odd where mid.next = null so L half is say 3 nodes and R is two nodes. last
// iteration add final odd