package recursion;


public class Queue8_2 {

    static int max = 8;

    public static void main(String[] args) {
        v(0);
        System.out.println(k);
    }

    static int k = 0; // 解法数量
    static int[] t = new int[max]; // 8 皇后列所在的位置
    public static void v(int count){
        if(count == max){
            k++;
            return;
        }
        for(int i=0;i<max;i++){
            t[count] = i;
            if(!v1(count)){
                v(count+1);
            }
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
