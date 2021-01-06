//package com.

package com.gcache;

import java.util.ArrayList;
import java.util.List;

import com.gcache.entities.CacheLinkedList;
import com.gcache.entities.Node;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

//O(1)
/*
 * {
 *
 * }
 * [a, b, p, q]
 * */
@Slf4j
public class QueueLRUCache implements Cache {

    private CacheLinkedList cacheStructure;

    public QueueLRUCache(int capacity) {
        this.cacheStructure = new CacheLinkedList(capacity);
    }

	@Override
	public <V> void insert(String key, V value) {
        cacheStructure.insert(key, value);
	}

	@Override
	public <V> V fetch(String key) {
        Node node = cacheStructure.getNodeAt(key);
        Object value = node != null ? node.getValue() : null;
        if(value != null) {
            cacheStructure.insert(key, value);
        }
        return (V)value;
	}

	@Override
	public List<Object> fetchAll() {
        List<Object> values = new ArrayList<>();
        for(Node node = cacheStructure.getHeadNode(); node != null; node = node.getNext()) {
            values.add(node.getValue());
        }
        return values;
	}


};

