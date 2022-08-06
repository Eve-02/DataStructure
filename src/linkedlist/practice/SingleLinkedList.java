package linkedlist.practice;


public class SingleLinkedList {

    private HeroNode head = new HeroNode();

    /**
     * 单链表反转
     * 创建一个新的单链表
     * 使用头插法实现反转
     * 最后将 head = new.head;
     */
    public void reverse(){
        // 如果当前链表为空 或 只有一个节点，无需反转，直接返回
        if(head.next == null || head.next.next == null){
            return;
        }
        SingleLinkedList s0 = new SingleLinkedList();
        HeroNode p = head.next;
        while(true){
            if(p == null){
                break;
            }else{
                s0.addByHead(p);
                p = p.next;
            }
        }
        head = s0.head;
    }

    /* 头插法 */
    public void addByHead(HeroNode heroNode){
        HeroNode h = new HeroNode(heroNode.no,heroNode.name);
        h.next = head.next;
        head.next = h;
    }

    /* 查找单链表中的倒数第k个结点 */
    public HeroNode queryByK(int k){
        int len = size();
        if(k <= 0 || k > len){
            System.out.println("超出长度");
        }
        int count = len - k;
        int i = 0;
        HeroNode p = head.next;
        while(true){
            if(i == count){
                return p;
            }
            p = p.next;
            i++;
        }
    }

    /* 节点个数(不包含头节点) */
    public int size(){
        HeroNode p = head.next;
        int count = 0;
        while(p != null){
            count++;
            p = p.next;
        }
        return count;
    }

    /* 添加节点(尾部) */
    public void add(HeroNode heroNode){
        HeroNode p = head;
        // 找到尾节点
        while(p.next != null){
            p = p.next;
        }
        p.next = heroNode;
    }

    /* 遍历链表 */
    public void list(){
        if(head.next == null){
            System.out.println("链表为空!");
            return;
        }
        HeroNode p = head.next;
        while(p != null){
            System.out.println(p);
            p = p.next;
        }
    }

}
// 英雄节点
class HeroNode{

    public int no; // 编号
    public String name; // 名字
    public HeroNode next; // 指向下一个节点

    public HeroNode(){

    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
