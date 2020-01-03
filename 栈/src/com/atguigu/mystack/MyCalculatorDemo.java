package com.atguigu.mystack;

import com.atguigu.mystack.practice.LinkedStack;

import java.util.regex.Pattern;

/**
 * @Author: chenlj
 * @CreateTime: 2019-12-31 14:37
 * @Description: 计算器(中位表达计算)
 */
public class MyCalculatorDemo {

    public static void main(String[] args) {
        MyCalculator calculator = new MyCalculator();
        int result = calculator.compute("711 * 2 * 2 * 11 - 5 + 1 - 5 + 3 - 4");
        System.out.println("result = " +result);
//        calculator.getAllExpressions();
    }

}

class MyCalculator {

    // 数字栈
    private LinkedStack<Integer> numStack = new LinkedStack(99);

    // 符号栈
    private LinkedStack<String> operatorStack = new LinkedStack(99);

    // 计算的表达式
    private String expression;

    // 表达式的下标
    private int index;

    public MyCalculator() {
    }

    /*
     * 不支持  小括号   中缀表达式计算
     * 使用栈完成表达式的计算 思路
     * 1. 通过一个 index  值（索引），来遍历我们的表达式
     * 2. 如果我们发现是一个数字, 就直接入数栈
     * 3. 如果发现扫描到是一个符号,  就分如下情况
     *    3.1 如果发现当前的符号栈为 空，就直接入栈
     *    3.2 如果符号栈有操作符，就进行比较,如果当前的操作符的优先级小于或者等于栈中的操作符， 就需要从数栈中pop出两个数,在从符号栈中pop出一个符号，进行运算，将得到结果，入数栈，然后将当前的操作符入符号栈， 如果当前的操作符的优先级大于栈中的操作符， 就直接入符号栈.
     * 4. 当表达式扫描完毕，就顺序的从 数栈和符号栈中pop出相应的数和符号，并运行.
     * 5. 最后在数栈只有一个数字，就是表达式的结果
     */

    public int compute(String expression) {
        init(expression);
        expression = getNextExpression();
        while(expression != null) {
            Type type = Type.getType(expression);
            switch (type) {
                case NUMBER:
                    numStack.push(Integer.parseInt(expression));
                    break;
                case OPERATOR:
                    operator(expression);
            }
            expression = getNextExpression();
        }
        int result = lastCompute();
        return result;
    }

    private int lastCompute() {
        String operator = operatorStack.pop();
        Integer firstNum = numStack.pop();
        Integer secondNum = numStack.pop();
        int result = Operators.compute(secondNum, firstNum, Operators.getOperator(operator));
        return result;
    }

    private void init(String expression) {
        this.expression = expression.replaceAll("\\s*", "");
    }

    /**
     * 进行运算
     * @param expression
     */
    private void operator(String expression) {
        if(operatorStack.isEmpty()) {
            // 如果为空就直接放入
            operatorStack.push(expression);
        }else {
            String operator = operatorStack.peek();
            if(comparePriority(expression, operator) != 1) {
                // 如果当前操作符优先级不高于栈中取出的,进行计算
                computeNumber(expression);
            }
        }
    }

    private void computeNumber(String expression) {
        //
        Integer firstNum = numStack.pop();
        Integer secondNum = numStack.pop();
        String operatorStr = operatorStack.pop();
        Operators operator = Operators.getOperator(operatorStr);
        int result = Operators.compute(secondNum, firstNum, operator);
//        //
        numStack.push(result);
        operatorStack.push(expression);
    }

    /**
     * 如果返回的不是1,就要计算操作
     * @param firstOperator
     * @param secondOperator
     * @return
     */
    public int comparePriority(String firstOperator, String secondOperator) {
        return getPriority(firstOperator) - getPriority(secondOperator);
    }

    /**
     *
     * @param operator
     * @return 1为  乘除  0 为 加减
     */
    public int getPriority(String operator) {
        if("/".equals(operator) || "*".equals(operator)) {
            return 1;
        }
        return 0;
    }

    /**
     * 验证表达式的有效性
     */
    public void checkVail() {

    }

    /**
     * 测试拆分表达式
     */
    public void getAllExpressions() {
        String expression = getNextExpression();
        while(expression != null) {
            System.out.println(expression);
            expression = getNextExpression();
        }
    }

    /**
     * 拆分
     * @return
     */
    public String getNextExpression() {
        if(index == expression.length()) {
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
            if(index == expression.length()) {
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

enum Type {

    NUMBER,OPERATOR,ERROR;

    public static Type getType(String expression) {
        if(expression.contains("/") || expression.contains("*") || expression.contains("+") || expression.contains("-") || expression.contains("(") || expression.contains(")")) {
            return OPERATOR;
        }
        if(isNumber(expression)) {
            return NUMBER;
        }
        return ERROR;
    }

    public static boolean isNumber(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}

enum Operators {
    ADD("+"),SUBTRACT("-"),MULTIPLICATION("*"),DIVIDED("/"),ERROR("error");
    Operators(String operator) {
        this.operator = operator;
    }
    String operator;

    public static Operators getOperator(String operator) {
        Operators[] values = Operators.values();
        for (Operators value : values) {
            if(operator.equals(value.operator)) {
                return value;
            }
        }
        return Operators.ERROR;
    }

    public static int compute(Integer first, Integer second, Operators operators) {
        switch (operators) {
            case ADD:
                return first + second;
            case SUBTRACT:
                return first - second;
            case DIVIDED:
                return first / second;
            case MULTIPLICATION:
                return  first * second;
        }
        throw new RuntimeException("表达式有误");
    }

}