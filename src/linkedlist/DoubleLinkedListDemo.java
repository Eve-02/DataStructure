package linkedlist;



public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(new HeroNode2(1,"12"));
        doubleLinkedList.list();


    }

}
class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2();

    /* 添加数据 */
    public void add(HeroNode2 heroNode){
        HeroNode2 p = head;
        // 找到尾节点
        while(p.next != null){
            p = p.next;
        }
        p.next = heroNode;
        heroNode.pre = p;
    }

    /* 添加数据，按照编号的顺序(不允许重复，重复添加视为失败) */
    public void addByOrder(HeroNode2 heroNode2){
        if(head.next == null){
            head.next = heroNode2;
            return;
        }
        HeroNode2 p = head.next;
        while(true){
            if(p.next == null){
                p.next = heroNode2;
                return;
            }
            // 添加在 p 后面(2  4)
            if(heroNode2.no < p.next.no){
                heroNode2.next = p.next;
                p.next = heroNode2;
                p.next.pre = heroNode2;
                heroNode2.pre = p;
                return;
            }else if(heroNode2.no == p.next.no){
                System.out.println("已经存在~");
                return;
            }
            p = p.next;
        }
    }

    /* 删除节点 */
    public void delete(int no){
        // 判断当前链表是否为空
        if(head.next == null){
            System.out.println("链表为空~");
            return;
        }
        HeroNode2 p = head.next;
        while(true){
            if(p == null){
                System.out.println("未找到~");
                return;
            }
            if(p.no == no){
                p.pre.next = p.next;
                if(p.next != null){
                    p.next.pre = p.pre;
                }
                System.out.println("删除成功~");
                return;
            }
            p = p.next;
        }
    }

    /* 遍历链表 */
    public void list(){
        if(head.next == null){
            System.out.println("链表为空!");
            return;
        }
        HeroNode2 p = head.next;
        while(p != null){
            System.out.println(p);
            p = p.next;
        }
    }

}
class HeroNode2 {

    public int no; // 编号
    public String name; // 名字
    public HeroNode2 pre; // 指向前一个节点,默认为null
    public HeroNode2 next; // 指向下一个节点,默认为null

    public HeroNode2(){

    }

    public HeroNode2(int no, String name) {
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
