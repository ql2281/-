package MyHeap;

public class test {
    public static void main(String[] args) {
        minHeap minHeap = new minHeap(10);
        minHeap.offer(5);
        minHeap.offer(2);
        minHeap.offer(3);
        minHeap.update(0, 1);
        System.out.println(minHeap.toString());
        minHeap.peek();
        minHeap.poll();
        minHeap.poll();

        minHeap minHeap1 = new minHeap(new int[]{3, 4, 5, 0});
        System.out.println(minHeap1.toString());
        minHeap1.update(1, 10);
        System.out.println();
        System.out.println(minHeap1.toString());
    }
}
