package sparsearray;

import java.io.*;

public class Sparsearray {

    /* 得到原始数组 */
    public static int[][] v1(){
        // 创建一个原始的二维数组 11*11
        // 0：表示没有棋子，1：表示黑子，2：表示白子
        int[][] arr1 = new int[11][11];
        arr1[1][2] = 1;
        arr1[2][3] = 2;

        // 输出原始数组
        System.out.println("原始的二维数组:");
        for (int[] ints : arr1) {
            for (int value : ints) {
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }
        return arr1;
    }

    /* 原始的二维数组 --> 稀疏数组 */
    public static int[][] v2(int[][] arr1){
        int sum = 0;
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr1[i].length;j++){
                if(arr1[i][j] != 0){
                    sum++;
                }
            }
        }
        int[][] arr2 = new int[sum+1][3];
        arr2[0][0] = arr1.length;
        arr2[0][1] = arr1[0].length;
        arr2[0][2] = sum;
        int count = 0;
        for(int i=0;i<arr1.length;i++){
            for(int j=0;j<arr1[i].length;j++){
                if(arr1[i][j] != 0){
                    arr2[++count][0] = i;
                    arr2[count][1] = j;
                    arr2[count][2] = arr1[i][j];
                }
            }
        }

        System.out.println("-------------");

        // 输出稀疏数组
        System.out.println("稀疏数组:");
        for (int[] ints : arr2) {
            for (int value : ints) {
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }
        return arr2;
    }

    /* 稀疏数组 --> 原始的二维数组 */
    public static void v3(int[][] arr2){

        System.out.println("-------------");

        int row = arr2[0][0];
        int col = arr2[0][1];
        int[][] arr1 = new int[row][col];

        for(int i=1;i<arr2.length;i++){
            int row1 = arr2[i][0];
            int col1 = arr2[i][1];
            int value = arr2[i][2];
            arr1[row1][col1] = value;
        }

        // 输出原始的二维数组
        System.out.println("原始的二维数组:");
        for (int[] ints : arr1) {
            for (int value : ints) {
                System.out.printf("%d\t",value);
            }
            System.out.println();
        }
    }

    /* 输出到文件中 */
    public static void v4(int[][] arr2) throws IOException {
        FileWriter fileWriter = new FileWriter("01.text");
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<arr2.length;i++){
            for(int j=0;j<arr2[i].length;j++){
                stringBuilder.append(arr2[i][j] + "\t");
            }
            fileWriter.write(stringBuilder.toString() + "\n");
            stringBuilder.delete(0,stringBuilder.length());
        }
        fileWriter.close();

        System.out.println("-------------");
        System.out.println("成功输出到文件中~");
        System.out.println("-------------");
    }

    /* 获取文件中的稀疏数组 */
    public static int[][] v5() throws IOException {
        System.out.println("将文件中的稀疏数组还原~");

        FileReader fileReader = new FileReader("01.text");
        char[] arr = new char[1024];
        int len = fileReader.read(arr);

        String str = new String(arr,0,len);
        String[] strings = str.split("\n");
        String[] strings1 = strings[0].split("\t");
        int[][] arr2 = new int[strings.length][strings1.length];

        for(int i=0;i<strings.length;i++){
            String[] split = strings[i].split("\t");
            for(int j=0;j<split.length;j++){
                arr2[i][j] = Integer.parseInt(split[j]);
            }
        }

        for (int[] ints : arr2) {
            for (int data : ints) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        return arr2;

    }

    public static void main(String[] args) throws IOException {

        try {
            v4(v2(v1()));
            v3(v5());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
