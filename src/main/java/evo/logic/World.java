package evo.logic;

import evo.mathf.Mathf;
import evo.mathf.Vec2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class World {
    // SINGLETON PATTERN REALISATION
    public static World INSTANCE;

    public static World Init(int width, int height){
        if (INSTANCE == null){
            INSTANCE = new World(width, height);
        }
        return INSTANCE;
    }

    // World LOGIC
    public int width, height;

    private World(int width, int height){
        this.width = width;
        this.height = height;
        for (int i = 0; i < 100; i++) {
            CircleObj obj = new CircleObj(
                    new Vec2(Math.random() * width, Math.random() * height),
                    Vec2.zero,
                    Math.random() * 6 + 6,
                    10
            );
            obj.setMass(obj.radius * obj.radius * obj.radius / 144);
            obj.ApplyImpulse(new Vec2(1, 0).rotated(Math.random() * Math.PI * 2).mul(obj.mass * 4));
            PhysicsSolver.AddObject(obj);
        }
        PhysicsSolver.UpdateObjectsList();
    }

    public void step(){
        for (int i = 0; i < PhysicsSolver.CALC_STEPS; i++) {
            PhysicsSolver.ApplyGravity();
            PhysicsSolver.PhysicStep();
        }
    }
}
