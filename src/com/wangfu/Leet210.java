package com.wangfu;

import java.util.LinkedList;
import java.util.Queue;

/**
 * leetcode 第210题
 * @author liutangchen
 */
public class Leet210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0)
            return new int[0];
        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        // 将入度为0的先加入队列
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i);
            }
        }
        // 记录选过的课程数
        int count = 0;
        int[] res = new int[numCourses];
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            res[count++] = curr;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == curr) {
                    inDegrees[prerequisite[0]]--;
                    if (inDegrees[prerequisite[0]] == 0) {
                        queue.offer(prerequisite[0]);
                    }
                }
            }
        }
        if (count == numCourses)
            return res;
        return new int[0];
    }
}
