package algorithm;

import java.util.HashMap;
import java.util.Map;

public class LeastRecentlyUse<K, V> {

    private int capital;
    //head、tail只是标杆，不存实际值
    private Node<K, V> head;
    private Node<K, V> tail;

    //缓存，方便更新、查找
    private Map<K,Node<K, V>> nodeMap;

    public LeastRecentlyUse(int capital) {
        if(capital < 1) {
            throw new IllegalArgumentException(String.valueOf(capital));
        }
        this.capital = capital;

        nodeMap = new HashMap<>();

        Node<K, V> headNode = new Node<>(null, null);
        Node<K, V> tailNode = new Node<>(null,null);
        this.head = headNode;
        this.tail = tailNode;
        this.head.next = tailNode;
        this.tail.pre = headNode;
    }

    //如果key已存在，则更新value,并移动到最前面；否则创建Node插入最前面。
    //保证节点是按照使用的先后顺序排列（最近使用的排在最前面）
    public void put(K key, V value) {
        if(key == null){
            throw new IllegalArgumentException("null");
        }
        Node<K, V> node = nodeMap.get(key);
        if(node != null) {
            node.value = value;
            moveToFirst(node);
        }else {
            if(nodeMap.size() == this.capital) {
                removeLastNode();
            }
            node = new Node<>(key, value);
            addToFirst(node);
        }
        nodeMap.put(key, node);
    }

    public V get(K key) {
        if(key == null){
            throw new IllegalArgumentException("null");
        }
        Node<K, V> node = nodeMap.get(key);
        if(node != null ) {
            moveToFirst(node);
            return node.value;
        }
        return null;
    }

    private void removeLastNode() {
        Node<K, V> lastNode = this.tail.pre;
        this.tail.pre = lastNode.pre;
        lastNode.pre.next = this.tail;
    }

    private void moveToFirst(Node<K, V> node) {
        //remove node
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //add node to fist
        addToFirst(node);
    }

    private void addToFirst(Node<K, V> node) {
        Node<K, V> headnext = this.head.next;
        headnext.pre = node;
        node.next = headnext;
        node.pre = head;
        head.next = node;
    }

    /**
     * This is for test
     */
    public void print() {
        Node<K,V> node = this.head.next;
        //the tailNode is nullNode;
        while(node != null && node.key != null) {
            System.out.println(node.key+" : "+node.value);
            node = node.next;
        }
    }

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> pre;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
