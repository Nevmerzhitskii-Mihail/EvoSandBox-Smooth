package evo.logic;

import evo.mathf.Mathf;
import evo.mathf.Vec2;

import java.awt.*;

public class Cell {
    public Vec2 pos; // position
    public Vec2 vel; // velocity
    public Vec2 acc; // acceleration
    public int radius; // radius
    public Color color;

    public Cell(double x, double y, double vx, double vy, int radius){
        pos = new Vec2(x, y);
        vel = new Vec2(vx, vy);
        acc = Vec2.zero;
        this.radius = radius;
        color = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
    }

    public void step(int id){
        physicStep(id);
    }


    double getGravitation(double length, double mass){
        return 3 * mass / (length * length);
    }

    double getCollision(double length, double distance){
        return -0.8 * (distance - length) / distance;
    }

    Vec2 getImpact(Cell other){
        Vec2 delta = Vec2.sub(pos, other.pos);
        double length = delta.length();
        double distance = radius + other.radius;
        double coef = getCollision(length, distance) / (radius * radius * 0.01);
        if (length > distance) coef = getGravitation(length, other.radius * other.radius * 0.01);
        delta = Vec2.normalized(delta);
        return Vec2.mul(delta, coef);
    }

    void physicStep(int id){
        for (int i = id + 1; i < World.INSTANCE.cells.size(); i++){
            Cell cell = World.INSTANCE.cells.get(i);
            Vec2 force = getImpact(cell);
            acc = Vec2.add(acc, force);
            cell.acc = Vec2.add(cell.acc, Vec2.mul(force, -1));
        }
        acc = Vec2.add(acc, Vec2.mul(vel, -0.0002));
        vel = Vec2.add(vel, acc);
    }

    public void recalc(){
        pos = Vec2.reduce(Vec2.add(pos, vel));
        acc = Vec2.zero;
    }
}
