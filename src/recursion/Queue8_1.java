package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Queue8_1 {

    public static void main(String[] args) {
        int[][] arr = new int[8][8];
        Arrays.fill(t,-1);
        v(arr,0);
        System.out.println(list.size());
        for (int[][] ints : list) {
            for (int[] anInt : ints) {
                for (int i : anInt) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
            System.out.println("\n----------\n");
        }
    }

    static List<int[][]> list = new ArrayList<>(); // 解法
    static int[] t = new int[8]; // 8 皇后列所在的位置
    public static void v(int[][] arr,int count){
        if(count == 8){
            int[][] arr1 = new int[8][8];
            for(int i=0;i<arr.length;i++){
                for(int j=0;j<arr[i].length;j++){
                    arr1[i][j] = arr[i][j];
                }
            }
            list.add(arr1);
            return;
        }
        for(int i=0;i<8;i++){
            t[count] = i;
            if(!v1(count)){
                arr[count][i] = 1;
                v(arr,count+1);
            }
            // 当有一种结果符合时，进行回溯，进行arr值的重置
            // (t[count]不需要重置，因为count+1后面的值对count没有影响)
            arr[count][i] = 0;
        }

    }

    /* 判断是否冲突 */
    public static boolean v1(int count){
        for(int i=0;i<count;i++){
            if(t[i] == t[count] || Math.abs(count-i) == Math.abs(t[count]-t[i])){
                return true;
            }
        }
        return false;
    }


}
