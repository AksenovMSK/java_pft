package ru.stqa.pft.sandbox;

public class DistanceOfPoints {

    public static void main(String[] args) {
        Point p1 = new Point(2.0,3.0);
        Point p2 = new Point (5.0,4.0);
        System.out.printf("расстояние между двумя точками равно " + "%.2f", p1.distance(p2));
    }

}
