package com.mervyn.utils;


import java.util.ArrayList;
import java.util.List;

public class PermutationUtils {

    private static List<List<Integer>> permutationList = new ArrayList<List<Integer>>();

    public static List<List<Integer>> permutation(List<Integer> statement) {
        permutationList.clear();
        permutation(statement, 0, statement.size());
        return permutationList;
    }

    /**
     * @Title: permutation
     * @Description: TODO(全排列并加入到list)
     * @author lsy
     * @date  2020年07月06日 16:37:31
     * @param statement
     * @param pos
     * @param n
     * @return void
     * @throws
    */
    public static void permutation(List<Integer> statement, int pos, int n) {
        addToList(statement);
        if (pos == n) {
            return;
        }
        for (int i = pos; i < n; ++i) {
            if (isSwap(statement, pos, i)) {
                swap(statement, pos, i);
                permutation(statement, pos+1, n);
                swap(statement, pos, i);
            }
        }
    }

    /**
     * @Title: isSwap
     * @Description: TODO(如果[a,b) 中存在 num[i] == num[b]不交换，避免重复)
     * @author lsy
     * @date  2020年07月07日 15:14:15
     * @param statement
     * @param a
     * @param b
     * @return boolean
     * @throws
     */
    private static boolean isSwap(List<Integer> statement, int a, int b) {
        for (int i = a; i < b; ++i) {
            if (statement.get(i) == statement.get(b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @Title: addToList
     * @Description: TODO(加入到list)
     * @author lsy
     * @date  2020年07月06日 16:37:51
     * @param statement
     * @return void
     * @throws
    */
    private static void addToList(List<Integer> statement) {
        List<Integer> tmp = new ArrayList<Integer>();
        for (Integer a : statement) {
            tmp.add(a);
        }
        permutationList.add(tmp);
    }

    /**
     * @Title: swap
     * @Description: TODO(交换)
     * @author lsy
     * @date  2020年07月06日 16:38:15
     * @param statement
     * @param a
     * @param b
     * @return void
     * @throws
    */
    public static void swap(List<Integer> statement, int a, int b) {
        Integer tmp = statement.get(a);
        statement.set(a, statement.get(b));
        statement.set(b, tmp);
    }

}
