package Proyek2;

class Node {
    String data;
    Node next;
    public Node(){
        next = null;
    }
    public Node(String dt){
        data = dt;
        next = null;
    }

}

public class SingleLinkedList {
    private Node head, tail;
    private int size;

    public void init(){
        this.head = this.tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = this.head;
        while (temp != null) {
            sb.append(temp.data);
            if (temp.next != null) sb.append(", ");
            temp = temp.next;
        }
        return sb.toString();
    }

    public void add(String dt){
        Node data = new Node(dt);
        if (head == null) {
            this.head = this.tail = data;
        }else{
            Node temp = this.head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = data;
            this.tail = data;
        }
        this.size++;
    }

    public boolean contains(String data){
        Node temp = this.head;
        while (temp != null){
            if (temp.data.equals(data)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}
