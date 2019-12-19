package ZongHeYanLian;

//Move Zeros with same order and O(n) O(1)
// {2 0 1 3 0 0 5 7 2}
//  f
// 2 1 3 5 7 2 0 0 0 0
//                    s
//                    f
// [0, slow): the numbers to return without 0s
// [slow, fast): I don't care
// [fast, n - 1]: to explore

import org.junit.Test;

import java.util.*;

public class MoveZeros {
    public List<Integer> moveZeros(int[] array) {
        int slow = 0;
        for (int fast = 0; fast < array.length; fast++) {
            if (array[fast] == 0) {
                continue;
            } else {
                swap(array, fast, slow);
                slow++;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }

        return list;
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    @Test
    public void test() {
        int[] array = new int[] {0, 2, 0, 3, 0, 0, 5};
        List<Integer> res = moveZeros(array);
        System.out.println(res);
    }
}
