package com.example.javaapplication;

import static java.lang.Math.sin;

public class Engine {
    private double x,y,vx,vy;
    private final double g = -9.81;

    Engine() {
        x=0;
        y=0;
        vx=0;
        vy=0;
    }
    double getX() { return x;}
    double getY() { return y;}
    double getVx() { return vx;}
    double getVy() { return vy;}

    void update(double thetax, double thetay, double delta) {
        double ax = g*sin(thetax);
        double ay = g*sin(thetay);
        vx = vx + ax*delta;
        vy = vy + ay*delta;
        x = x + vx*delta;
        y = y + vy*delta;
    }

}
