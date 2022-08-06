package queue;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue1 arrayQueue = new ArrayQueue1(3);
        arrayQueue.addQueue(1);
    }

}

/* ArrayQueue */
class ArrayQueue1 {

    private int maxSize; // 数组最大容量
    private int front; // 队列头
    private int rear; // 队列尾
    private int[] arr; // 数据(队列)

    /* 初始化队列 */
    public ArrayQueue1(int arrMaxSize) {
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = -1; // 指向队列头部(头部元素的前一个)
        rear = -1; // 指向队列尾部(包含元素)
    }

    /* 判断队列是否为满 */
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    /* 判断队列是否为空 */
    public boolean isEmpty() {
        return front == rear;
    }

    /* 添加数据到队列 */
    public void addQueue(int data) {
        if (isFull()) {
            System.out.println("队列已满!");
            return;
        }
        arr[++rear] = data;
    }

    /* 获取队列的数据，出队列 */
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        return arr[++front];
    }

    /* 显示队列所有数据 */
    public void showQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            System.out.println("队列为空!");
            return;
        }
        for (int i = front + 1; i <= rear; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    /* 显示队列头数据 */
    public int headQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空!");
        }
        return arr[front + 1];
    }
}
