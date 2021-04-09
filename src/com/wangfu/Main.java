package com.wangfu;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < t; i++) {
            int n = in.nextInt();
            for (int j = 0; j < n; j++) {
                int[] arr = new int[2];
                arr[0] = in.nextInt();
                arr[1] = in.nextInt();
                queue.offer(arr);
            }
            int[] res = new int[n];
            int index = 0;
            int currentTime = 1;
            while (!queue.isEmpty()) {
                int[] arr = queue.poll();
                if (currentTime < arr[0])
                    currentTime = arr[0];
                if (currentTime >= arr[0] && currentTime <= arr[1]) {
                    res[index++] = currentTime;
                    currentTime++;
                } else {
                    res[index++] = 0;
                }
            }
            for (int k = 0; k < n - 1; k++) {
                System.out.print(res[k] + " ");
            }
            System.out.println(res[res.length - 1]);
        }
    }
}