package linkedlist;


public class SingleLinkedListDemo {

    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();

        // add
//        s.add(new HeroNode(1,"11英雄"));
//        s.add(new HeroNode(4,"44英雄"));
//        s.add(new HeroNode(3,"33英雄"));
//        s.add(new HeroNode(7,"77英雄"));
//        s.add(new HeroNode(2,"22英雄"));

        // addByOrder
        s.addByOrder(new HeroNode(1,"11英雄"));
        s.addByOrder(new HeroNode(4,"44英雄"));
        s.addByOrder(new HeroNode(3,"33英雄"));
        s.addByOrder(new HeroNode(3,"33英雄"));
        s.addByOrder(new HeroNode(7,"77英雄"));
        s.addByOrder(new HeroNode(2,"22英雄"));

        s.list();

        System.out.println("\n----------\n");

        // [1,2,3,4,7]
        s.changeNode(new HeroNode2(4,"444英雄"));
        s.changeNode(new HeroNode2(10,"10英雄"));
        s.list();

        System.out.println("\n----------\n");

        // [1,2,4,7]
        s.delete(3);
        s.delete(10);
        s.list();
    }

}

class SingleLinkedList{

    public HeroNode head = new HeroNode(); // 头指针(不存放数据，仅指向表头)

    /* 添加节点到尾部 */
    public void add(HeroNode heroNode){
        HeroNode p = head;
        // 找到尾节点
        while(p.next != null){
            p = p.next;
        }
        p.next = heroNode;
    }

    /* 添加节点，按照英雄编号从小到大的方式(若该编号已有，则添加失败) */
    public void addByOrder(HeroNode heroNode){
        HeroNode p = head;
        while(true){
            // 如果为空，则说明已经在链表的尾部，直接添加
            if(p.next == null){
                p.next = heroNode;
                return;
            }
            // 在当前元素后面插入，比后面小
            if(heroNode.no < p.next.no){
                heroNode.next = p.next;
                p.next = heroNode;
                return;
            // 说明编号存在
            }else if((heroNode.no == p.next.no)){
                System.out.println("编号已经存在");
                return;
            }
            p = p.next;
        }
    }

    /* 修改节点(根据编号修改) */
    public void changeNode(HeroNode2 heroNode){
        HeroNode p = head.next;
        while(true){
            // 到达尾部
            if(p == null){
                System.out.println("未找到~");
                return;
            }
            if(p.no == heroNode.no){
                p.name = heroNode.name;
                return;
            }
            p = p.next;
        }
    }

    /* 删除节点 */
    public void delete(int no){
        HeroNode p = head;
        while(true){
            if(p.next == null){
                System.out.println("未找到~");
                return;
            }
            if(p.next.no == no){
                p.next = p.next.next;
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
