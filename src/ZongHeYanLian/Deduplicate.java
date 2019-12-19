package ZongHeYanLian;

//        Given sorted?/adjacent array (string), deduplicate with one remaining，zero remaining
//        {3 2 4 1}
//        3 3 2 2 2 2 2 4 4 1
//        f
//        3 2 4 1
//        s
//        3 3 2 2 2 2 2 4 4 1
//        s
//        f
//        [0, s] return new String(array, 0 , s);
//        [0, s) solution so far → array[s++] / s == 1
//        [s, f) I don't care
//        [f, length -1] to explore


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Deduplicate {
    public List<Integer> deduplicate(int[] array) {
        List<Integer> list = new ArrayList<>();
        if (array == null || array.length <= 2) {
            for (Integer item: array) {
                list.add(item);
            }
            return list;
        }

        int slow = 1;  // 如果保留k个重复的值，slow = k，并且后面比较的是slow-k和fast对应的值
        for (int fast = 1; fast < array.length; fast++) {
            if (array[slow-1] == array[fast]) {
                continue;
            } else {
                array[slow] = array[fast];  // inplace 把slow指向的值改为fast走到的值
                slow++;
            }
        }

        for (int i = 0; i < slow; i++) {
            list.add(array[i]);
        }
        return list;
    }

    // 如果去掉所有的重复元素
    public List<Integer> deduplicateWithZeroRemaining(int[] array) {
        List<Integer> list = new ArrayList<>();
        if (array == null || array.length <= 1) {
            for (Integer item: array) {
                list.add(item);
            }
            return list;
        }

        int slow = 1;
        boolean duplicate = false;
        for (int fast = 1; fast < array.length; fast++) {
            if (array[slow-1] == array[fast]) {
                duplicate = true;
            } else {
                if (!duplicate) {
                    array[slow++] = array[fast];
                } else {
                    array[slow-1] = array[fast];
                    duplicate = false;
                }
            }
        }

        // post processing
        if (duplicate) {
            slow--;
        }

        for (int i = 0; i < slow; i++) {
            list.add(array[i]);
        }
        return list;
    }

    @Test
    public void test() {
        int[] arr = new int[]{1,1,2,3,3,4,4};
        // System.out.println(deduplicate(arr));
        System.out.println(deduplicateWithZeroRemaining(arr));
    }
}
