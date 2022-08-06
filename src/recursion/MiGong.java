package recursion;


public class MiGong {

    public static void main(String[] args) {
        // 1.创建一个二维数组，模拟迷宫
        // 地图
        int[][] map = new int[8][7];

        // 2.使用1表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 其它的墙
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        // 3.输出地图
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        System.out.println("\n---------\n");

        // 4.测试
//        System.out.println(setWay(map, 1, 1));
//        for (int[] ints : map) {
//            for (int anInt : ints) {
//                System.out.print(anInt + " ");
//            }
//            System.out.println();
//        }

        // 5.最短路径
        setWay2(map,1,1,0);
        for (int[] ints : result) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }


    /**
     * 使用递归回溯给小球找路
     * 说明：
     * 1. map表示地图
     * 2. ij表示小球起始位置
     * 3. 若小于到达 6,5(右下角)，则视为通路找到
     * 4. 约定：当 map[i][j] 为 0 表示该点没有走过; 为 1 时表示墙; 为 2 时表示通路可以走; 3 表示该点已经走过，但是走不通
     * 5. 按照 下 -> 右 -> 上 -> 左 的策略，当该点走不通时，回溯其它路径
     *
     * 思路分析:
     * 小球视当前点临时记为 2，即从该点假定可以走通。
     * 小球每往下走一步，有 2 两种情况：
     *      1是未走过，继续往下(上下左右)走，直至走到终点，返回true；
     *      2是为墙，或者是走过，但是是走不通的点，返回 false到上一个点，并在上一个点走完事先约定的顺序(下右上左)都走不通时，则将上一个点记为 3
     * 走通是：终点为 2；此时最后到达 终点时，回溯返回 true，回溯时程序的第二条 if语句的子if语句结束，不会往下走。
     * 走不通是：下一个点为 123的情况，为 2是 标记当前为2，走下，再走上的情况。返回 false。上下左右都 false时，到达 if尾部才记该点为 3
     *
     * 最短路径:
     * 小球的路径，与策略相关，即 上下左右的顺序。
     * 修改策略，
     * 每种策略中，到达终点时，将此地图数组存入(List<int[][]>)。
     * 每种策略走完之后，再从中求出 2 数量最小的即为最短路径。
     *
     * @param map 地图
     * @param i 小球起始行
     * @param j 小球起始列
     * @return 如果找到通路，就返回 true，否则返回 false
     */
    public static boolean setWay(int[][] map,int i,int j){
        // 先确定递归结束条件。当终点为 2 时，表示该条通路可以走
        if(map[6][5] == 2){
            return true;
        }
        if(map[i][j] == 0){ // 如果当前点还没有走过
            // 按照 下 -> 右 -> 上 -> 左 进行
            map[i][j] =2; // 假定该点可以走通
            if(setWay(map,i+1,j)){ // 向下走
                return true;
            }else if(setWay(map,i,j+1)){ // 向右走
                return true;
            }else if(setWay(map,i-1,j)){ // 向上走
                return true;
            }else if(setWay(map,i,j-1)){ // 向左走
                return true;
            }else{
                // 从该点，向下一个点(下、右、上、左)都走不通时，那么该走不通
                // 标记为 3
                map[i][j] = 3;
                return false;
            }
        }else { // 如果 map[i][j] != 0，可能是 1，2，3；
                // 而 2 可能是到达终点结束，或者如走了下，再往上方向走，而走回上一个点。因此下一个点遇到2时只有回退到原点(上一个点)的情况，那么也视为不通
            return false;
        }
    }

    static int[][] result = null;
    static int k = 56;
    public static boolean setWay2(int[][] map,int i,int j,int count){
        // 先确定递归结束条件。当终点为 2 时，表示该条通路可以走
        if(map[6][5] == 2){
            int[][] arr = new int[8][7];
            for(int i1=0;i1<map.length;i1++){
                for(int j1=0;j1<map[i1].length;j1++){
                    arr[i1][j1] = map[i1][j1];
                }
            }
            if(count <= k){
                result = arr;
            }
        }
        if(map[i][j] == 0){ // 如果当前点还没有走过
            // 按照 下 -> 右 -> 上 -> 左 进行
            map[i][j] =2; // 假定该点可以走通
            if(setWay2(map,i+1,j,count+1)){ // 向下走
                return true;
            }else if(setWay2(map,i,j+1,count+1)){ // 向右走
                return true;
            }else if(setWay2(map,i-1,j,count+1)){ // 向上走
                return true;
            }else if(setWay2(map,i,j-1,count+1)){ // 向左走
                return true;
            }else{
                // 从该点，向下一个点(下、右、上、左)都走不通时，那么该走不通
                // 标记为 3
                map[i][j] = 3;
                return false;
            }
        }else { // 如果 map[i][j] != 0，可能是 1，2，3；
            // 而 2 可能是到达终点结束，或者如走了下，再往上方向走，而走回上一个点。因此下一个点遇到2时只有回退到原点(上一个点)的情况，那么也视为不通
            return false;
        }
    }


}
