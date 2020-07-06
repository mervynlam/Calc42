package com.mervyn.utils;

import java.util.List;
import java.util.Stack;

public class CalculateUtils {
    private static final float EPS = 1e-4f;

    /**
     * @Title: calc
     * @Description: TODO(后缀表达式计算结果)
     * @author lsy
     * @date  2020年07月06日 16:42:20
     * @param calcStatement
     * @return java.lang.String
     * @throws
    */
    public static String calc(List<Integer> calcStatement) {
        //运算栈
        Stack<Double> stack = new Stack<Double>();

        //结果栈
        Stack<String> resultStack = new Stack<String>();

        //循环表达式
        for (Integer num : calcStatement) {
            if (isOperator(num)) {
                //如果是运算符
                //判断栈内是否有2个以上运算数
                if (stack.size() < 2) {
                    //没用，表达式有问题，下一个
                    return "";
                }

                //出栈2个运算数
                Double a = stack.pop();
                Double b = stack.pop();
                try {
                    //计算结果，入栈
                    Double c = calc(a, b, num);
                    stack.push(c);
                } catch (ArithmeticException e) {
                    return "";
                }

                //中缀表达式生成
                //出栈两个表达式
                String pre = resultStack.pop();
                String next = resultStack.pop();

                //当前结果
                String str;
                if ((char)(int)num == '*' || (char)(int)num == '/') {
                    //新符号是*或/
                    //前后表达式加括号
                    pre = pre.length() > 2? "("+pre+")" :pre;
                    next = next.length() > 2? "("+next+")" :next;
                    str = pre +(char)(int)num + next;
                } else if ((char)(int)num == '-') {
                    //新符号是-
                    //后面表达式加括号
                    next = next.length() > 2? "("+next+")" :next;
                    str = pre +(char)(int)num + next;
                } else {
                    //无需加括号
                    str = pre + (char)(int)num + next;
                }
                //结果入栈
                resultStack.push(str);
            } else {
                //不是运算符，入栈
                stack.push(num*1.0);

                //中缀表达式生成，入栈
                resultStack.push(String.valueOf(num));
            }
        }

        //循环后如果结果为42，返回算式
        Double result = stack.pop();
        //误差EPS
        if (Math.abs(result - 42) < EPS) {
            return resultStack.pop();
        } else {
            return "";
        }
    }

    /**
     * @Title: isOperator
     * @Description: TODO(判断是否为运算符)
     * @author lsy
     * @date  2020年07月06日 15:22:14
     * @param op
     * @return boolean
     * @throws
    */
    private static boolean isOperator(int op) {
        if (op == 42 || op == 43 || op == 45 || op == 47) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @Title: calc
     * @Description: TODO(计算)
     * @author lsy
     * @date  2020年07月06日 15:22:06
     * @param a
     * @param b
     * @param op
     * @return int
     * @throws
    */
    private static Double calc(Double a, Double b, int op) {
        switch (op) {
            case 42:
                return a*b;
            case 43:
                return a+b;
            case 45:
                return a-b;
            case 47:
                return a/b;
            default:
                return 0.0;
        }
    }
}
