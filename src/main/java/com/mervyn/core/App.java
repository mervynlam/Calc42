package com.mervyn.core;

import com.mervyn.utils.CalculateUtils;
import com.mervyn.utils.OperatorUtils;
import com.mervyn.utils.PermutationUtils;

import java.util.*;


public class App {
    public static void main(String[] args) {
        //输入运算数
        Scanner sc = new Scanner(System.in);
        Integer[] numArr = new Integer[5];
        for (int i = 0; i < 5; ++i) {
            numArr[i] = sc.nextInt();
        }

        Date start = new Date();

        //运算符全排列
        String[] operatorArr = OperatorUtils.operatorArr;

        //结果set
        Set<String> resultSet = new HashSet<String>();

        //全排列个数
        int permNum = 0;

        for (String operator : operatorArr) {
            //运算数拼接运算符
            List<Integer> statement = new ArrayList<Integer>();
            Collections.addAll(statement, numArr);
            char[] chars = operator.toCharArray();
            for (int i = 0; i < chars.length; ++i) {
                statement.add(new Integer(chars[i]));
            }

            //全排列
            List<List<Integer>> permutationList = PermutationUtils.permutation(statement);
            permNum+= permutationList.size();

            //计算每一个排列
            for (List<Integer> calcStatement : permutationList) {
                String result = CalculateUtils.calc(calcStatement);
                //结果入set
                if (result.length() > 0) {
                    resultSet.add(result);
                }
            }
        }

        for (String result : resultSet) {
            System.out.println(result);
        }

        Date end = new Date();
        System.out.println("全排列："+permNum+"个");
        System.out.println(resultSet.size()+"个，耗时:" + (end.getTime() - start.getTime()) + "ms");
    }
}
