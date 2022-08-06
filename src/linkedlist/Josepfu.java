package linkedlist;


public class Josepfu {

    public static void main(String[] args) {
        CircleLinkedList c = new CircleLinkedList();

        c.move(10,20,125);
    }

}

class CircleLinkedList{

    // 先创建一个 first 节点
    private Boy first;

    // 添加节点
    public void add(int nums){
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        // for循环创建
        Boy curBoy = null;
        for(int i=1;i<=nums;i++){
            Boy boy = new Boy(i);
            // 如果是第一个小孩
            if(i == 1){
                first = boy;
                first.next = first;
                curBoy = first; // 指向第一个小孩(尾部)
            }else{
                curBoy.next = boy;
                boy.next = first;
                curBoy = boy;
            }
        }


    }

    // 遍历
    public void list(){
        if(first == null){
            System.out.println("链表为空~");
            return;
        }
        Boy curBoy = first;
        while(true){
            System.out.println(curBoy);
            if(curBoy.next == first){
                return;
            }
            curBoy = curBoy.next;
        }
    }

    /**
     *
     * @param startNo 表示从第几个小孩开始报数
     * @param count 表示数几下出队
     * @param nums 表示最初有几个小孩在圈中
     */
    public void move(int startNo,int count,int nums){
        // 先对数据进行检验
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        add(nums);
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数有误");
            return;
        }
        // 创建一个辅助指针,指向尾部,出圈(删除时使用，单向链表)
        Boy helper = first;
        while(helper.next != first){
            helper = helper.next;
        }
        // 让 first 移动到第 startNo 个小孩，同时 helper 保持在 first 后面
        for(int i=1;i<=startNo-1;i++){
            first = first.next;
            helper = helper.next;
        }
        // 当小孩报数时，让 first 和 helper 指针同时的移动 count-1 次，然后出圈
        while(true){
            // 圈中只有一个人
            if(helper == first){
                break;
            }
            // 移动 count-1 出圈
            for(int i=1;i<=count-1;i++){
                first = first.next;
                helper = helper.next;
            }
            // 出圈。此时 first 为要出圈的小孩
            System.out.printf("小孩 %d 出圈\n",first.no);
            first = first.next;
            helper.next = first;
        }
        // 最后的小孩的编号
        System.out.printf("最后留在圈中的小孩编号%d\n", first.no);
    }


}

class Boy{

    public int no;
    public Boy next;

    public Boy(int no){
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no +
                '}';
    }
}
