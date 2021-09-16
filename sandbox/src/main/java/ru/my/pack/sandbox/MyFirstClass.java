package ru.my.pack.sandbox;

import java.util.Arrays;

public class MyFirstClass {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        shuffle(nums);
        System.out.println(Arrays.toString(nums));


//        System.out.println("Hello, world!");
//        Point a = new Point(3, 3);
//        Point b = new Point(4, 4);
//        System.out.println(distance(a, b));
//        System.out.println(a.distance(b));
//        Arrays.
//
//    }
//    public static double distance(Point p1, Point p2){
//        double dx =  p2.x - p1.x;
//        double dy =  p2.y - p1.y;
//        return Math.sqrt(dx * dx + dy * dy);
//

    }

    public static int[] shuffle(int[] arr) {
        int value = 0;
        for (int i = 0; i < arr.length; i++) {
            int index = (int) (Math.random() * 9);
            value = arr[i];
            arr[i] = arr[index];
            arr[index] = value;

        }
        return arr;
    }
}