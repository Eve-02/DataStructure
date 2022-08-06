package recursion;

public class test {

    public static int v(int n){
        if(n > 1){
            return n*v(n+1);
        }
        return 1;
    }

    public static void main(String[] args) {
        v(3);
    }

}
