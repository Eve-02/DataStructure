package linkedlist.practice;


public class main {

    public static void main(String[] args) {
        SingleLinkedList s = new SingleLinkedList();

        s.add(new HeroNode(1,"11英雄"));
        s.add(new HeroNode(4,"44英雄"));
        s.add(new HeroNode(3,"33英雄"));
        s.add(new HeroNode(7,"77英雄"));
        s.add(new HeroNode(2,"22英雄"));

//        System.out.println(s.size());
//        s.list();
//
//        System.out.println("\n----------\n");
//
//        System.out.println(s.queryByK(2));
//
//        System.out.println("\n----------\n");

        System.out.println("链表反转:");
        s.list();

        System.out.println("\n----------\n");
        s.reverse();

        s.list();
    }

}
