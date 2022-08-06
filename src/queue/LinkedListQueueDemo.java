package queue;

public class LinkedListQueueDemo {

    public static void main(String[] args) {
        LinkedListQueue listQueue = new LinkedListQueue();
        for(int i=1;i<=10;i++){
            listQueue.addQueue(new Node(i));
        }
        System.out.println(listQueue.getCount());
        listQueue.list();
        listQueue.list();
    }

}

class LinkedListQueue {

    private Node head = null;
    private Node rear = null;

    /* 入队 */
    public void addQueue(Node node){
        if(rear == null){
            head = node;
            rear = node;
            return;
        }
        rear.next = node;
        rear = rear.next;
    }

    /* 出队 */
    public Node getQueue(){
        if(head == null){
            System.out.println("当前栈无数据~");
            return null;
        }
        Node node = head;
        head = head.next;
        // 当栈中只有一个数据时，rear应该为null
        if(head == null){
            rear = null;
        }
        return node;
    }

    /* 显示队列所有数据 */
    public void list(){
        Node p = head;
        while(p != null){
            System.out.println(p);
            p = p.next;
        }
    }

    /* 获取队列中元素个数 */
    public int getCount(){
        int count = 0;
        Node p = head;
        while(p != null){
            count++;
            p = p.next;
        }
        return count;
    }

}

class Node {

    public Node next;
    public Integer no;

    public Node(Integer no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}


