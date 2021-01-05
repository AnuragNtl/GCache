package com.gcache.entities;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class CacheLinkedList {

    private Node head, tail; 

    private ConcurrentHashMap<String, Node> keyWiseMap = new ConcurrentHashMap<>();

    private int capacity;

    public CacheLinkedList(int capacity) {
        this.capacity = capacity;
    }

    private synchronized void insertAfterTail(Node node) {

        log.info("Inserting {} after tail {}", node, tail);
        if(head == node) {
            head = node.getNext();
        }
        node.insertAfter(tail);
        tail = node;
        log.info("Head {}, Tail {}, size {}", head, tail, keyWiseMap.size());
    }

    public synchronized void insert(String key, Object item) {
        if(keyWiseMap.containsKey(key)) {
            Node node = keyWiseMap.get(key);
            keyWiseMap.remove(key);
            insertAfterTail(node);
        } else {
            if(keyWiseMap.size() >= capacity) {
                removeLRUItem();
            }
            if(head == null) {
                head = new Node(key, item);
                tail = head;
            } else {
                Node node = new Node(key, item);
                insertAfterTail(node);
            }
        }
        keyWiseMap.put(key, tail);
    }

    public synchronized void removeLRUItem() {
        log.info("removing LRU item {}", head);
        Node oldHead = head;
        head = head.getNext();
        oldHead.remove();
        keyWiseMap.remove(oldHead.getKey());
    }

    public Node getHeadNode() {
        return head;
    }

    public Node getNodeAt(String key) {
        return keyWiseMap.get(key);
    }

    
};

