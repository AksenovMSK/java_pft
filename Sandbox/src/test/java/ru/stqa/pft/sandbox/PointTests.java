package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests {

    @Test
    public void testDistance(){
        Point p1 = new Point(2.0,3.0);
        Point p2 = new Point (5.0,4.0);
        Assert.assertEquals(p1.distance(p2) ,3.1622776601683795);
    }
}
