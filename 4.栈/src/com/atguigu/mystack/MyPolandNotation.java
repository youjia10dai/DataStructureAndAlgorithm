package com.atguigu.mystack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @Author: chenlj
 * @CreateTime: 2020-01-03 14:36
 * @Description: 计算器(后缀表达式实现,逆波兰表达式)
 *  使用系统的Stack堆栈对象
 */
public class MyPolandNotation {

    private Stack<String> stack = new Stack<String>();
    private Stack<Integer> numStack = new Stack<Integer>();
    private String expression;

    private int index = 0;

    /*
     * a.数字直接输出
     * b.运算符
     * 情况一：遇到左括号直接入栈，遇到右括号将栈中左括号之后入栈的运算符全部弹栈输出，同时左括号出栈但是不输出。
     * 情况二：遇到乘号和除号直接入栈，直到遇到优先级比它更低的运算符，依次弹栈。
     * 情况三：遇到加号和减号，如果此时栈空，则直接入栈，否则，将栈中优先级高的运算符依次弹栈（注意：加号和减号属于同一个优先级，所以也依次弹栈）直到栈空或则遇到左括号为止，停止弹栈。（因为左括号要匹配右括号时才弹出）。
     * 情况四：获取完后，将栈中剩余的运算符号依次弹栈输出
     * 3, 5, 2, *, +, 2, +, 3, /, 6, 2, *, 2, /, +, 3, +, 1, +
     * 3, 5, 2, *, +, 2, +, 3, /, 6, 2, 2, /, *, +, 3, +, 1, +
     */

    public static void main(String[] args) {
        MyPolandNotation calculator = new MyPolandNotation();
        int result = calculator.compute("(30+4)*5-6");//15
        System.out.println("result = " + result);
    }

    public int compute(String expression) {
        // 1.格式化表达式
        init(expression);
        // 2.解析出表达式(中缀表达式)
        List<String> infixExpressionList = getExpressionList();
        // 3.将中缀表达式转换为后缀表达式
        List<String> suffixExpressionList = parseSuffixExpressionList(infixExpressionList);
        System.out.println("suffixExpressionList = " + suffixExpressionList);
        // 4.使用后缀表达式进行计算
        int result = compute(suffixExpressionList);
        return result;
    }

    private int compute(List<String> suffixExpressionList) {
        for (String express : suffixExpressionList) {
            Type type = Type.getType(express);
            switch (type) {
                case NUMBER:
                    numStack.push(Integer.parseInt(express));
                    break;
                case OPERATOR:
                    Integer second = numStack.pop();
                    Integer first = numStack.pop();
                    int result = Operators.compute(first, second, Operators.getOperator(express));
                    numStack.push(result);
                    break;
            }
        }
        return numStack.pop();
    }

    //
    private List<String> parseSuffixExpressionList(List<String> infixExpressionList) {
        List<String> result = new ArrayList<String>();
        for (String expression : infixExpressionList) {
            switch (Type.getType(expression)) {
                case OPERATOR:
                    List<String> pop = pop(expression);
                    if(pop.size() != 0)
                        result.addAll(pop);
                    break;
                case NUMBER:
                    // 如果是数字直接添加
                    result.add(expression);
                    break;
                case ERROR:
                    throw new RuntimeException("表达式有误!");
            }
        }
        addLast(result);
        return result;
    }

    private void addLast(List<String> result) {
        while(!stack.isEmpty()) {
            result.add(stack.pop());
        }
    }

    private List<String> pop(String expression) {
        List<String> result = new ArrayList<String>();
        List<String> subResult = new ArrayList<String>();
        switch (expression) {
            case ")":
                subResult = stackRigthPop();
                break;
            case "+":
            case "-":
                if(!stack.isEmpty()){
                    subResult = stackAddPop();
                }
                stack.push(expression);
                break;
            case "(":
            case "*":
            case "/":
                stack.push(expression);
                break;
        }
        result.addAll(subResult);
        return result;
    }

    private List<String> stackRigthPop() {
        List<String> result = new ArrayList<String>();
        String peek = stack.peek();
        while(!peek.equals("(")) {
            result.add(stack.pop());
            peek = stack.peek();
        }
        stack.pop();
        return result;
    }

    private List<String> stackAddPop() {
        List<String> result = new ArrayList<String>();
        String peek = stack.peek();
        while(!peek.equals("(")) {
            result.add(stack.pop());
            if(stack.isEmpty()) {
                break;
            }
            peek = stack.peek();
        }
        return result;
    }

    private List<String> getExpressionList() {
        List<String> infixExpressionList = new ArrayList<String>();
        while(true) {
            String nextExpression = getNextExpression();
            if(nextExpression == null) {
                break;
            }
            infixExpressionList.add(nextExpression);
        }
        System.out.println("infixExpressionList = " + infixExpressionList);
        return infixExpressionList;
    }

    private void init(String expression) {
        this.expression = expression.replaceAll("\\s*", "");
    }

    /**
     * 拆分
     * @return
     */
    public String getNextExpression() {
        int length = expression.length();
        if(index == length) {
            return null;
        }
        String result = "";
        String str = expression.substring(index, index + 1);
        Type type = Type.getType(str);
        if(type == Type.ERROR) {
            throw new RuntimeException("表达式有误");
        }
        while (type == Type.NUMBER) {
            result = result + str;
            index++;
            if(index == length) {
                return result;
            }
            str = expression.substring(index, index + 1);
            type = Type.getType(str);
        }
        if(type == Type.OPERATOR && isEmpty(result)){
            index++;
            return str;
        }
        return result;
    }

    public static boolean isEmpty(String str) {
        if(str == null || str.trim().length() == 0)
            return true;
        return false;
    }

}