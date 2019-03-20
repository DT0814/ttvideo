package com.xawl.ttvideo.test;


import java.util.*;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
/*    public static int getLength(int []a) {
        int max=0;
        int sum=0;
        HashMap<Integer,Integer> hashMap=new HashMap<>();
        hashMap.put(0,-1);
        for (int i=0;i<a.length;i++){
            sum=sum+a[i];
            if (hashMap.get(sum)==null){
                hashMap.put(sum,i);
            }else{
                int tmp=i-hashMap.get(sum);
                if (max<tmp){
                    max=tmp;
                }
            }
        }
        return max;

    }*/

   /* public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        int arr[]=new int[n];
        for (int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        System.out.println(getLength(arr));
        sc.close();
    }*/


    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        getIndex(arr, k);
        sc.close();
    }

    private static void getIndex(int[] arr, int k) {
        int a = Integer.MAX_VALUE;
        String s = "";
        for (int i = 0; i < arr.length; i++) {
            for (int j = arr.length - 1; j > i; j--) {
                if (arr[i] + arr[j] >= k) continue;
                if (arr[i] + arr[j] < j) {

                }
                for (int x = i + 1; x < j; x++) {
                    if (arr[x] + arr[i] + arr[j] == k) {
                        if (a > x + i + j) {
                            a = x + i + j;
                            s = i + " " + x + " " + j;
                        }


                    }
                }
            }
        }
        System.out.println(s);
    }

}
