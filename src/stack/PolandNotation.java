package stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

    public static void main(String[] args) {

        // 中缀
        String str = "1+((2+3)*4)-5";
        // list
        List<String> list = getListString(str);
        // 后缀
        List<String> list1 = parseSuffixExpression(list);
        System.out.println(list1);
        // 计算
        System.out.println(cal(list1));

    }

    public static List<String> parseSuffixExpression(List<String> list){
        //符号栈
        Stack<String> s1 = new Stack<>();
        // 存储中间结果
        //Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        //遍历表达式
        for (String item : list) {
            //若是一个数，加入s2
            if (item.matches("\\d+")){
                s2.add(item);
            }else if (item.equals("(")){
                s1.push(item);
            }else if (item.equals(")")){
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop(); // 把左括号（  出栈 清除括号。
            }else {
                //判断优先级 ，当item的优先级小于等于s1栈顶运算符，
                // 将s1栈顶的运算符弹出并压入到s2中，再次转到4.1步骤与s1中新的栈顶运算符相比较;
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                s1.push(item);
            }

        }

        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;

    }

    /* 计算逆波兰表达式 */
    public static int cal(List<String> list){
        // 创建栈，只需要一个
        Stack<String> stack = new Stack<>();
        // 遍历
        for (String s : list) {
            if(s.matches("\\d+")){
                // 数字，入栈
                stack.push(s);
            }else{
                // 取值，计算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if(s.equals("+")){
                    result = num1 + num2;
                }else if(s.equals("-")){
                    result = num1 - num2;
                }else if(s.equals("*")){
                    result = num1 * num2;
                }else if(s.equals("/")){
                    result = num1 / num2;
                }else{
                    throw new RuntimeException("不认识符号");
                }
                stack.push("" + result);
            }
        }
        // 最后在栈中的是运算结果
        return Integer.parseInt(stack.pop());
    }

    public static List<String> getListString(String s){
        List<String> list = new ArrayList<>();
        String str = "";
        for(int i=0;i<s.length();i++){
            char ch = s.charAt(i);
            if(ch < 48 || ch> 57){ // 非数字
                list.add(s.charAt(i) + "");
            }else{ // 数字，考虑是否是多位数
                str = str + ch;
                // 如果下一位不是数字或者已到达结尾，则将拼接的数加入list
                if(i+1 >= s.length() || s.charAt(i+1) < 48 || s.charAt(i+1) > 57){
                    list.add(str);
                    str = "";
                }
            }
        }
        return list;
    }

}

class Operation{

    //加法
    private static  int ADD = 1;
    //减法
    private static  int SUB = 1;
    //乘法
    private static  int MUL = 2;
    //除法
    private static  int DIV = 2;


    /**
     * 返回优先级
     * @return
     */
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("该运算符不存在");
                break;
        }
        return result;
    }


}

