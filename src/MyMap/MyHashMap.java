package MyMap;
import java.util.*;

public class MyHashMap<K, V> {
    private static final double LOAD_FACTOR = 0.75d;
    private List<Cell<K, V>>[] buckets;
    private int capacity;
    private int size;

    public MyHashMap() {
        this.capacity = 256;
        this.size = 0;
        buckets = new List[capacity];
    }

    public MyHashMap(int custSize) {
        this.capacity = custSize;
        this.size = 0;
        buckets = new List[capacity];
    }

    private int getIndex(K key) {
        //对于所有的null，只要保证得到同一个idx，落到同一个bucket
        return key == null ? 0 : Math.abs(key.hashCode() % capacity);  // 防止溢出，取负数
    }

    public V get(K key) {
        int idx = getIndex(key);
        List<Cell<K ,V>> bucket = buckets[idx];
        if (bucket == null) {
            buckets[idx] = new ArrayList<>();  //ArrayList 做删除操作：先swap到最后，然后删除最后的元素
        }
        for (Cell cell: bucket) {
            if (cell.keyEquals(key)) {
                return (V) cell.getValue();
            }
        }
        return null; //如果没找到直接返回null
    }

    public boolean put(K key, V val) {
        int idx = getIndex(key);
        if (buckets[idx] == null) {
            buckets[idx] = new ArrayList<>();
        }
        for (Cell cell: buckets[idx]) {
            if (cell.keyEquals(key)) {
                cell.setValue(val);
                return false;  //update
            }
        }
        buckets[idx].add(new Cell<K,V>(key, val));  //insert
        size++;
        if (size > capacity * LOAD_FACTOR) {
            rehash();
        }
        return true;
    }

    public boolean remove(K key) {
        int idx = getIndex(key);
        if (buckets[idx] == null) {
            return false;
        }
        List<Cell<K,V>> bucket = buckets[idx];
        int len = bucket.size();
        for (int i = 0; i < len; i++) {
            if (bucket.get(i).keyEquals(key)) {
                Cell<K, V> lastCell = bucket.get(len-1);
                bucket.get(i).setKey(lastCell.getKey());
                bucket.get(i).setValue(lastCell.getValue());
                bucket.remove(len-1);
                size--;
                return true;
            }
        }
        return false;
    }

    private void rehash() {
        this.capacity *= 2;
        List<Cell<K,V>>[] newBuckets = new List[capacity];
        for (List<Cell<K,V>> bucket: this.buckets) {
            for (Cell<K,V> cell: bucket) {
                int idx = getIndex(cell.getKey());
                if (newBuckets[idx] == null) {
                    newBuckets[idx] = new ArrayList<>();
                }
                newBuckets[idx].add(cell);
            }
        }
        this.buckets = newBuckets;
    }
}
