package ru.my.pack.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testDistance(){
    Point point1  = new Point(3,3);
    Point point2  = new Point(4,4);
    Assert.assertEquals(point1.distance(point2), 1.4142135623730951);
  }
}
