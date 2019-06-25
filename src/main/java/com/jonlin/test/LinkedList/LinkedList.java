package com.jonlin.test.LinkedList;


/**
 * description 自定义链表
 * Author Jonlin
 * Date 2019/6/14 13:09
 **/
public class LinkedList<T> implements ILinkedList<T>{
    private Node head; // 头
    private Node last; // 最后一个节点
    private int size; // 元素个数

    /**
     * 节点类
     */
    class Node{
        T data;
        Node next;
        Node prov;

        public Node(T data, Node next, Node prov) {
            this.data = data;
            this.next = next;
            this.prov = prov;
        }
    }

    @Override
    public boolean add(T t) {
        Node temp = this.last; // 最后节点
        Node newnode = new Node(t,null,temp); // 将最新的节点置为last节点
        this.last = newnode; // 将最新的节点置为last节点

        if(this.head == null){ // 若位第一个元素，则视其位头
            this.head = newnode;
        }else{
            temp.next = newnode; // 否则将新元素添加到末尾
        }
        this.size++;
        return true;
    }

    @Override
    public void printLink() {
        T[] obj = toArray();
        if(obj == null)return;
        for(Object i:obj){
            System.out.print(i+"  ");
        }
        System.out.println();
    }

    private T removeNode(Node node){
        Node prov = node.prov;
        T elementData = node.data;
        Node next = node.next;
        if(node.prov == null){
            this.head = next;
        }else{
            prov.next = next;
            node.prov= null;
        }
        if(node.next == null){
            this.last = prov;
        }else{
            next.prov = prov;
            node.next = null;
        }
        node.data = null;
        return elementData;

    }

    @Override
    public T remove(T t) {
        for(Node temp = this.head;temp != null;temp= temp.next){
            if(temp.data == t ||
                    ( t != null && t.equals(temp.data))){
                this.size--;
                return removeNode(temp);

            }
        }
        return null;
    }

    @Override
    public T set(int index, T t) {
        if(index<0 && index >size-1){
            return null;
        }
        Node node = find(index);
        T elementData = node.data;
        node.data = t;
        return elementData;
    }

    private  Node find(int index){
        if(index<(size>>1)) {
            Node temp = this.head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }
        else{
            Node temp = this.last;
            for(int i=size-1;i>index;i--){
                temp = temp.prov;
            }
            return temp;
        }
    }

    @Override
    public T get(int index) {
        if(index<0 && index >size-1){
            return null;
        }
        return find(index).data;
    }

    @Override
    public boolean contains(T t) {
        for(Node temp = this.head;temp != null;temp = temp.next){
            if(t == temp.data ||
                    (t !=null && t.equals(temp.data))){
                return true;
            }
        }
        return false;
    }

    @Override
    public T[] toArray() {
        if(size == 0){
            return null;
        }
        T[] obj = (T[])new Object[size];
        int i=0;
        for(Node temp = this.head;temp != null;temp=temp.next){
            obj[i++] = temp.data;
        }
        return obj;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean clean() {
        for(Node temp = this.head;temp != null;){
            Node node = temp.next;
            temp.next =temp.prov = null;
            temp.data = null;
            temp =node;
            size--;
        }
        return true;
    }
}
