package ru.my.pack.sandbox;

public class Point {
  double a;
  double b;

  public Point(double a, double b) {
    this.a = a;
    this.b = b;
  }
  public double distance(Point p2){
    return Math.sqrt(Math.pow(p2.a - this.a, 2) + Math.pow(p2.b - this.b, 2));

  }
}
