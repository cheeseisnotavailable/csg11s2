package com.example.csg11s2.util;

public class MyQueue {
    int pointer;
    int size = 0;

    private class Node{
        int value;
        Node next;

        public Node(int value){
            this.next = null;
            this.value = value;
        }
    }

    private Node front;
    private Node back;

    public MyQueue(){
        pointer = 0;
        front = null;
        back = null;
    }


    public void enqueue(int value){
        Node n = new Node(value);

        if(back == null){
            back = n;
            front = n;
        } else{
            back.next = n;
            back  = n;
        }
        size++;
    }

    public int dequeue(){
        if(front == null){
            throw new IllegalStateException("Your queue is empty! There's nothing to dequeue!");
        }else{
            int ret = front.value;
            front = front.next;
            size --;
            return ret;
        }
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return front == null;
    }
}
