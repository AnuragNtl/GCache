package com.gcache.entities;

import java.util.Iterator;

import lombok.ToString;

@ToString(exclude = {"next", "previous"})
public class Node {

    
    private Object value;
    private Node next;
    private Node previous;
    private String key;

    public Node(String key, Object value) {
        this(key, null, value);
    }


    public Object getValue() {
		return value;
	}


	public void setValue(Object value) {
		this.value = value;
	}


	public Node(String key, Node previous, Object value) {
        this.previous = previous;
        if(previous != null) {
            previous.next = this;
        }
        this.setValue(value);
        this.key = key;
    }

    public synchronized Node remove() {
        if(next != null) {
            next.previous = previous;
        }
        if(previous != null) {
            previous.next = next;
        }
        this.previous = null;
        this.next = null;
        return this;
    }

    public synchronized void insertAfter(Node node) {
        remove();
        node.next = this;
        this.previous = node;
        this.next = null;
    }


    public Node getNext() {

        return next;
    }

    public String getKey() {

        return key;
    }



};

