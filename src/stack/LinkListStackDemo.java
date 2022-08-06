package stack;

public class LinkListStackDemo {

    public static void main(String[] args) {
        LinkedListStack listStack = new LinkedListStack();
        for(int i=1;i<=10;i++){
            listStack.push(new Node(i));
        }
        listStack.list();
    }

}

class LinkedListStack {

    Node head; // 栈顶
    int size;  // 栈中元素个数

    public LinkedListStack() {
        size = 0;
        head = null;
    }

    /* 入栈 */
    public void push(Node node){
        if(head == null){
            head = node;
            return;
        }
        node.next = head;
        head = node;
        size++;
    }

    /* 出栈 */
    public Node pop(Node node){
        if(head == null){
            System.out.println("栈为空~");
            return null;
        }
        Node data = head;
        head = head.next;
        size--;
        return data;
    }

    /* 显示栈中所有元素 */
    public void list(){
        if(head == null){
            System.out.println("栈为空~");
            return;
        }
        Node p = head;
        while(p != null){
            System.out.println(p);
            p = p.next;
        }
    }

}

class Node {

    Integer no;
    Node next;

    public Node(Integer no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node1{" +
                "no=" + no +
                '}';
    }

}
