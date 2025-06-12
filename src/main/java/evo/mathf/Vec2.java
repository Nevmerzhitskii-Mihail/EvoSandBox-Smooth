package evo.mathf;

import evo.Main;

public class Vec2 {
    double x, y;

    public static Vec2 zero = new Vec2(0);

    public Vec2(double a){
        x = a; y = a;
    }

    public Vec2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public double length(){
        return Math.sqrt(dot(this, this));
    }

    public static Vec2 add(Vec2 a, Vec2 b){
        return new Vec2(a.x + b.x, a.y + b.y);
    }

    public static Vec2 sub(Vec2 a, Vec2 b){
        return new Vec2(Mathf.getDeltaX(a.x, b.x), Mathf.getDeltaY(a.y, b.y));
    }

    public static Vec2 mul(Vec2 a, double c){
        return new Vec2(a.x * c, a.y * c);
    }

    public static Vec2 div(Vec2 a, double c){
        return new Vec2(a.x / c, a.y / c);
    }

    public static double dot(Vec2 a, Vec2 b){
        return a.x * b.x + a.y * b.y;
    }

    public static Vec2 normalized(Vec2 a){
        if (a.length() == 0) return zero;
        return div(a, a.length());
    }

    public static Vec2 reduce(Vec2 a){
        return new Vec2(Mathf.mod(a.x, Main.screen_width), Mathf.mod(a.y, Main.screen_height));
    }
}
