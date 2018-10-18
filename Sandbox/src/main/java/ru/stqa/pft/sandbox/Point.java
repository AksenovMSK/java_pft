package ru.stqa.pft.sandbox;

public class Point {
    double x;
    double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Point p){
        double operand1 = Math.pow((p.x - this.x),2);
        double operand2 = Math.pow((p.y - this.y),2);
        return Math.sqrt(operand1 + operand2);
    }
}
