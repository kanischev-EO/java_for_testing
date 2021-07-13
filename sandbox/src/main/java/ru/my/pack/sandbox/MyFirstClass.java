package ru.my.pack.sandbox;

public class MyFirstClass {
    public static void main(String[] args) {
        System.out.println("Hello, world!");
        Point a = new Point(3, 3);
        Point b = new Point(4, 4);
        System.out.println(distance(a, b));
        System.out.println(a.distance(b));

    }
    public static double distance(Point p1, Point p2){
        double dx =  p2.x - p1.x;
        double dy =  p2.y - p1.y;
        return Math.sqrt(dx * dx + dy * dy);


    }
}