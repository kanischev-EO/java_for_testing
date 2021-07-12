package ru.my.pack.sandbox;

public class MyFirstClass {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        Point a = new Point(-1, 3);
        Point b = new Point(6, 2);
        System.out.println(distance(a, b));
        System.out.println(a.distance(b));

    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.a - p1.a, 2) + Math.pow(p2.b - p1.b, 2));

    }
}