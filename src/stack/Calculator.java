package stack;

public class Calculator {

    public static void main(String[] args) {
        // 运算表达式
//        String expression = "7+20*6-4";
//        String expression = "7+2+3*4";
        String expression = "5-2*3+1";
        // 创建 数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        // 定义需要的相关变量
        int index = 0;
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int result = 0;
        char ch = ' '; // 将每次扫描得到的char保存到ch中
        String keepNum = ""; //用于拼接多位数

        // 开始while循环
        while(index<expression.length()){
            // 取出字符
            ch = expression.charAt(index);
            // 判断ch的类型
            if(operStack.isOper(ch)){ // 运算符(入栈、运算)
                // 栈不为空
                while(true){
                    if(!operStack.isEmpty()){
                        // 与栈顶的符号比较优先级
                        if(operStack.priority(ch) <= operStack.priority(operStack.getTop())){
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            oper = operStack.pop();
                            result = numStack.cal(num1,num2,oper);
                            // 将运算的结果加入数栈，继续与栈顶判断符号优先级
                            numStack.push(result);
                        }else{
                            // 优先级大于栈顶符号，入栈
                            operStack.push(ch);
                            break;
                        }
                    }else{
                        // 栈为空
                        operStack.push(ch);
                        break;
                    }
                }
            }else{ // 数字，入栈
                keepNum += ch;
                // 如果index是最后一位，直接入栈
                if(index == expression.length() - 1){
                    numStack.push(Integer.parseInt(keepNum));
                }
                // 判断下一位是否是一位多位数
                else if(operStack.isOper(expression.charAt(index+1))){
                    // 后一位不是数字，入栈
                    numStack.push(Integer.parseInt(keepNum));
                    keepNum = ""; // 清空
                }
            }
            index++;
        }

        // 扫描栈依次取出进行运算
        while(true){
            // 符号栈为空结束
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1,num2,oper);
            numStack.push(result);
        }
        System.out.println(result);

    }

}

class ArrayStack2 {

    private int maxSize; // 栈的大小
    private int[] stack; // 数组，存放数据
    private int top = -1; // 初始化为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    // 栈满
    public boolean isFull() {
        return top == maxSize - 1;
    }

    // 栈空
    public boolean isEmpty() {
        return top == -1;
    }

    // 入栈-push
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        stack[++top] = value;
    }

    // 出栈-pop，将栈顶的数据返回
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    // 查看栈顶元素
    public int getTop(){
        return stack[top];
    }

    // 遍历栈(从栈顶往下)
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    // 返回栈的优先级，数值越大，优先级越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }
        if(oper == '+' || oper == '-'){
            return 0;
        }
        return -1; // 当前计算式中只有 +-*/
    }

    // 判断是否是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    // 计算
    public int cal(int num1, int num2, int oper){
        int result = 0;
        switch (oper){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }


}
