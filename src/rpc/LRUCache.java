package rpc;

import java.util.*;

public class LRUCache {
	private Map<Integer, Node> map;
	private final int capacity;
	private Node dummyHead;
	private Node dummyTail;
	
	public int getCapacity() {
		return capacity;
	}
	
	public int getCurSize () {
		return map.size();
	}
	
	public LRUCache (int size) {
		capacity = size;
		map = new HashMap<>();
		
		dummyHead = new Node(-1, -1);
		dummyTail = new Node(-1, -1);
		
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}
	
	private void moveToHead (Node cur) {
		Node oldHead = dummyHead.next;
		dummyHead.next = cur;
		cur.prev = dummyHead;
		oldHead.prev = cur;
		cur.next = oldHead;
	}
	
	public int get (int key) {
		if (!map.containsKey(key)) return -1;
		Node cur = map.get(key);
		
		moveToHead(cur);
		
		return map.get(key).value;
	}
	
	public int put (int key, int value) {
		if (map.containsKey(key)) {
			Node cur = map.get(key);
			int prevVal = cur.value;
			cur.value = value;
			cur.prev.next = cur.next;
			cur.next.prev = cur.prev;
			moveToHead(cur);
			
			return prevVal;
		} else {
			Node cur = new Node(key, value);
			map.put(key, cur);
			moveToHead(cur);
			if (map.size() > capacity) {
				return remove();
			} else {
				return -1;
			}
		}
	}
	
	private int remove () {
		Node tail = dummyTail.prev;
		
		tail.prev.next = dummyTail;
        dummyTail.prev = tail.prev;
      
        map.remove(tail.key);
        tail.next = null;
        tail.prev = null;
        return tail.value;
	}
}

class Node {
	int key;
	int value;
	Node prev = null;
	Node next = null;
	Node (int key, int value) {
		this.key = key;
		this.value = value;
	}
}
