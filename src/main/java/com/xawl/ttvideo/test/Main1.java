package com.xawl.ttvideo.test;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            long x = sc.nextLong();
            long y = sc.nextLong();
            long k = sc.nextLong();
            if (k < y || k < x) {
                System.out.println(-1);
                break;
            }
            long num = x - y;
            k -= num > 0 ? num : -num;
            System.out.println(k);
        }
        sc.close();
    }
}
