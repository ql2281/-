package MyHeap;

import java.util.Arrays;

public class minHeap {
    private int[] array;
    private int size;  // 代表当前array有多少元素

    public minHeap(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    // overload: 输入是一个array，需要进行heapify
    public minHeap(int[] nums) {
        this.array = Arrays.copyOf(nums, nums.length * 2);  // copy array 并且设置新的长度
        size = nums.length;
        heapify();
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "minHeap{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    // 从最后一个元素的parent开始做percolate down，一直到root。
    private void heapify() {
        for (int i = (size - 2) / 2; i >= 0; i--) {  // size比最后一个元素的idx大一位，parent idx = (size - 2) / 2
            percolateDown(i);
        }
    }

    // 把新元素放在最后，然后依次percolate up
    public void offer(int item) {
        if (size >= array.length) {
            array = Arrays.copyOf(array, size * 2);
        }

        array[size] = item;
        percolateUp(size);
        size++;
    }

    // 把要删除的元素跟最后一个元素交换，然后将他依次percolate down
    public Integer poll() {
        if (size == 0) throw new NullPointerException("No element in the heap");
        int res = array[0];
        array[0] = array[size-1];
        percolateDown(0);
        size--;
        System.out.println("The poll value is " + res);
        return res;
    }

    public Integer peek() {
        if (size ==0) throw new NullPointerException("No element in the heap");
        System.out.println("The peek value is " + array[0]);
        return array[0];
    }

    public void update(int idx, int val) {
        if (idx < 0 || idx > size) throw new ArrayIndexOutOfBoundsException("idx is not within the array");

        int tmp = array[idx];
        array[idx] = val;
        if (tmp > val) {
            percolateUp(idx);
        } else if (tmp < val) {
            percolateDown(idx);
        }
    }

    private void percolateUp(int idx) {
        while (idx > 0) {
            int parentIdx = getParentIdx(idx);
            if (array[idx] < array[parentIdx]) {
                swap(array, idx, parentIdx);
                idx = parentIdx;
            } else {
                break;
            }
        }
    }

    private void percolateDown(int idx) {
        // 如果当前idx是最后一个idx的parent，就执行最后一次。并且要check右child是否存在
        while (idx * 2 + 1 + 1 <= size) {
            int leftIdx = getLeftChildIdx(idx);
            int rightIdx = getRightChildIdx(idx);
            if (rightIdx < size && array[idx] > array[rightIdx]) {  // 先check右child是否存在
                swap(array, idx, rightIdx);
                idx = rightIdx;
            } else if (array[idx] > array[leftIdx]) {
                swap(array, idx, leftIdx);
                idx = leftIdx;
            } else {
                break;
            }
        }
    }

    private Integer getLeftChildIdx(int idx) {
        return idx * 2 + 1;
    }

    private Integer getRightChildIdx(int idx) {
        return idx * 2 + 2;
    }

    private Integer getParentIdx(int idx) {
        return (idx - 1) / 2;
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
