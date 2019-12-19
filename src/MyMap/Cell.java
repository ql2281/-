package MyMap;

public class Cell<K, V> {
    private K key;
    private V value;

    public Cell(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {  //在桶里面遍历找是否存在这个key
        if (o instanceof Cell<?,?>) {  //check传进去的object是不是cell类型
            Cell<K,V> that = (Cell<K,V>) o;  //强制数据类型转换成cell
            if (this.key == null) {
                return that.key == null;
            } else {
                return this.key.equals(that.key);
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {  //找桶的时候需要hashcode
        return this.key == null ? 0 : this.key.hashCode();
    }

    @Override
    public String toString() {
        return "Cell{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }

    public boolean keyEquals(K thatKey) {  //查看key是不是传进来的key是不是跟cell里的key相同
        if (this.key == null) {
            return thatKey == null;
        } else {
            return this.key.equals(thatKey);
        }
    }
}
