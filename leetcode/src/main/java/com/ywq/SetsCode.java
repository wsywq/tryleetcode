package com.ywq;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class SetsCode {

    // 链表节点类
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // leetcode21
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode();
        ListNode cur = result;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        if (list1 != null) {
            cur.next = list1;
        }

        if (list2 != null) {
            cur.next = list2;
        }
        return result.next;
    }

    // leetcode86
    public static ListNode partition(ListNode head, int x) {
        ListNode list1 = new ListNode();
        ListNode list2 = new ListNode();
        ListNode p1 = list1;
        ListNode p2 = list2;
        ListNode cur = head;
        while (cur != null) {
            if (cur.val < x) {
                p1.next = cur;
                p1 = p1.next;
            } else {
                p2.next = cur;
                p2 = p2.next;
            }

            ListNode temp = cur.next;
            cur.next = null;
            cur = temp;
        }

        p1.next = list2;
        return list1.next;
    }

    // leetcode23
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.offer(list);
            }
        }

        ListNode p = new ListNode();
        ListNode cur = p;
        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();
            cur.next = minNode;
            cur = cur.next;

            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }
        return p.next;
    }

    // 从1亿个整数中取出最大的100个
    public static List<Integer> findTopKMax(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k); // 小顶堆

        for (int num : nums) {
            if (minHeap.size() < k) {
                minHeap.offer(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(num);
            }
        }
        // 结果从小到大排序
        List<Integer> result = new ArrayList<>(minHeap);
        result.sort(Comparator.reverseOrder()); // 最大的在前
        return result;
    }

    // leetcode 19
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode result = new ListNode(0, head);
        ListNode p1 = result;
        ListNode p2 = result;
        for (int i = 0; i <= n; i++) {
            if (p1 == null) {
                return head;
            }
            p1 = p1.next;
        }
        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        ListNode temp = p2.next;
        p2.next = temp.next;
        temp.next = null;
        return result.next;
    }

    // leetcode 876
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    //leetcode 141
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    //leetcode 142
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // leetcode 160
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA, p2 = headB;
        while(p1!=p2){
            if(p1==null){
                p1=headB;
            }else{
                p1=p1.next;
            }

            if(p2==null){
                p2=headA;
            }else{
                p2=p2.next;
            }
        }
        return p1;
    }


    // 创建有序链表的辅助方法
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }

        ListNode head = new ListNode(arr[0]);
        ListNode current = head;

        for (int i = 1; i < arr.length; i++) {
            current.next = new ListNode(arr[i]);
            current = current.next;
        }

        return head;
    }

    // 打印链表的辅助方法
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    // 测试方法
    public static void main(String[] args) {
        // 测试TopK算法
        testTopK();

        // 测试链表合并算法
        testMergeKLists();
    }

    // 测试TopK方法
    public static void testTopK() {
        System.out.println("=== 测试TopK算法 ===");
        int n = 100_000_000; // 1亿
        int k = 100;
        int[] nums = new int[n];
        // 随机生成1亿个整数
        for (int i = 0; i < n; i++) {
            nums[i] = ThreadLocalRandom.current().nextInt();
        }
        long start = System.currentTimeMillis();
        List<Integer> topK = findTopKMax(nums, k);
        long end = System.currentTimeMillis();
        System.out.println("最大的100个数：" + topK);
        System.out.println("耗时：" + (end - start) + " 毫秒");
    }

    // 测试链表合并方法
    public static void testMergeKLists() {
        System.out.println("\n=== 测试链表合并算法 ===");

        // 创建测试数据
        ListNode[] lists = new ListNode[3];
        lists[0] = createList(new int[]{1, 4, 5});
        lists[1] = createList(new int[]{1, 3, 4});
        lists[2] = createList(new int[]{2, 6});

        // 打印原始链表
        System.out.println("原始链表：");
        for (int i = 0; i < lists.length; i++) {
            System.out.print("链表" + (i + 1) + ": ");
            printList(lists[i]);
        }

        // 合并链表
        long start = System.currentTimeMillis();
        ListNode merged = mergeKLists(lists);
        long end = System.currentTimeMillis();

        // 打印合并结果
        System.out.println("合并后的链表：");
        printList(merged);
        System.out.println("合并耗时：" + (end - start) + " 毫秒");

        // 验证结果
        System.out.println("验证结果：");
        ListNode current = merged;
        List<Integer> result = new ArrayList<>();
        while (current != null) {
            result.add(current.val);
            current = current.next;
        }
        System.out.println("合并结果数组：" + result);

        // 检查是否有序
        boolean isSorted = true;
        for (int i = 1; i < result.size(); i++) {
            if (result.get(i) < result.get(i - 1)) {
                isSorted = false;
                break;
            }
        }
        System.out.println("结果是否有序：" + isSorted);
    }
}
