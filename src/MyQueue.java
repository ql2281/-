class ListNode {
    int val;
    ListNode next;
    ListNode prev;
    public ListNode(int val) {
        this.val = val;
        next = null;
        prev = null;
    }
}
// tail           head
//   2 <--> 4 <--> 5
// offer from head
// peek or poll from tail

public class MyQueue {
    ListNode head;
    ListNode tail;
    int size;

    public MyQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // offer
    public void offer(int val) {
        ListNode node = new ListNode(val);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            head.next = node;
            node.prev = head;
        }
        size++;
    }

    // peek
    public int peek() {
        if (size == 0) {
            throw new NullPointerException("There is nothing in the queue");
        } else {
            return tail.val;
        }
    }

    // poll
    public int poll() {
        if (size == 0) {
            throw new NullPointerException("There is nothing in the queue");
        } else if (size == 1) {
            int val = tail.val;
            head = null;
            tail = null;
            size--;
            return val;
        } else {
            ListNode tmp = tail;
            tail = tail.next;
            tmp.next = null;
            size--;
            return tmp.val;
        }
    }
}
