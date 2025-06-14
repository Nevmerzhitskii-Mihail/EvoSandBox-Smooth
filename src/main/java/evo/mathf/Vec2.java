package evo.mathf;

import evo.Main;

public class Vec2 {
    public double x, y;

    public static Vec2 zero = new Vec2(0);

    public Vec2(double a){
        x = a; y = a;
    }

    public Vec2(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vec2 add(Vec2 other){
        return new Vec2(other.x + x, other.y + y);
    }

    public Vec2 sub(Vec2 other){
        return new Vec2(x - other.x, y - other.y);
    }

    public Vec2 mul(double coef){
        return new Vec2(x * coef, y * coef);
    }

    public double dot(Vec2 other){
        return x * other.x + y * other.y;
    }

    public double length(){
        return Math.sqrt(x * x + y * y);
    }

    public Vec2 normalized(){
        return this.mul(1 / length());
    }

    public double projLength(Vec2 normal){
        return dot(normal.normalized());
    }

    public Vec2 proj(Vec2 normal){
        normal = normal.normalized();
        return normal.mul(dot(normal));
    }

    public Vec2 rotated(double angle){
        return new Vec2(x * Math.cos(angle) - y * Math.sin(angle), x * Math.sin(angle) + y * Math.cos(angle));
    }
}
