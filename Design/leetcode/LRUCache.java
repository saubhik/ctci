import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }
    
    public int get(int key) {
        return super.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        super.put(key, value);
    }
    
    @Override
    protected boolean removeEldestEntry
        (Map.Entry<Integer, Integer> eldest) {
        return size() > capacity;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
class LRUCacheTwo {
    private class ListNode {
        int key;
        int value;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }
    
    private class LinkedList {
        private ListNode head;
        private ListNode tail;
        
        LinkedList() {
            this.head = null;
            this.tail = null;
        }
        
        void addNodeTail(ListNode node) {
            if (this.tail != null) {
                this.tail.next = node;
            }
            
            node.prev = this.tail;
            node.next = null;
            this.tail = node;
            
            if (this.head == null) {
                this.head = node;
            }            
        }

        ListNode removeHead() {
            ListNode node;

            node = this.head;
            this.head = node.next;
            if (this.head != null) {
                this.head.prev = null;
            } else {
                this.tail = null;
            }
            
            node.next = null;
            
            return node;
        }

        void removeNode(ListNode node) {
            ListNode prev;
            ListNode next;

            if (this.head == node) {
                this.head = node.next;
            }

            if (this.tail == node) {
                this.tail = node.prev;
            }

            prev = node.prev;
            next = node.next;

            if (prev != null) {
                prev.next = next;
            }

            if (next != null) {
                next.prev = prev;
            }

            node.prev = null;
            node.next = null;
        }
    }
    
    private Map<Integer, ListNode> store;
    private LinkedList list;
    private int capacity;
    
    public void LRUCache(int capacity) {
        this.store = new HashMap<>(capacity);
        this.list = new LinkedList();
        this.capacity = capacity;
    }
    
    /*
     * Everytime we do get(), we need to move key
     * to top. We need to do this in O(1) time.
     * We can have a hashmap from key to linked list
     * node.
     * get():
     * - retrieve node from hashmap.
     * - remove node from linked list.
     * - append node after linked list tail.
     * - add node as value to hashmap key.
     * - return the node's value.
     */
    public int get(int key) {
        ListNode node;
                
        if (this.store.containsKey(key)) {
            node = this.store.get(key);
            this.list.removeNode(node);
            this.list.addNodeTail(node);
            this.store.put(key, node);
            return node.value;
        }
        
        return -1;
    }
    
    /*
     * put():
     * If size greater than capacity:
     * - get head of the linked list.
     * - pop element from head of linked list.
     * - remove the element from the hashmap.
     */
    public void put(int key, int value) {
        ListNode node;
        
        if (this.store.containsKey(key)) {
            node = this.store.get(key);
            this.list.removeNode(node);
        }
        
        node = new ListNode(key, value);
        this.list.addNodeTail(node);
        this.store.put(key, node);
        
        if (this.store.size() > this.capacity) {
            node = this.list.removeHead();
            this.store.remove(node.key);
        }        
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */