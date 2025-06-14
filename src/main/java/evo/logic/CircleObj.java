package evo.logic;

import com.sun.javafx.geom.Vec2d;
import evo.mathf.Vec2;

public class CircleObj {
    public Vec2 position = Vec2.zero;
    public Vec2 velocity = Vec2.zero;
    public Vec2 impulse = Vec2.zero;
    public Vec2 pseudo_impulse = Vec2.zero;
    public double radius;
    public double mass;
    public double invMass;

    public CircleObj(Vec2 position, Vec2 velocity, double radius, double mass){
        this.position = position;
        this.velocity = velocity;
        this.radius = radius;
        this.mass = mass;
        this.invMass = 1. / mass;
    }

    public void ApplyImpulse(Vec2 impulse){
        this.impulse = this.impulse.add(impulse);
    }

    public void ApplyPseudoImpulse(Vec2 impulse){
        this.pseudo_impulse = this.pseudo_impulse.add(impulse);
    }

    public void Recalculate(){
        velocity = velocity.add(impulse.mul(invMass / PhysicsSolver.CALC_STEPS));
        position = position.add(velocity.mul(1. / PhysicsSolver.CALC_STEPS));
        position = position.add(pseudo_impulse.mul(invMass / PhysicsSolver.CALC_STEPS));
        pseudo_impulse = Vec2.zero;
        impulse = Vec2.zero;
    }

    public Vec2 getImpulse(){
        return velocity.mul(mass);
    }

    public void setMass(double mass){
        this.mass = mass;
        this.invMass = 1 / mass;
    }

    public void setInfiniteMass(){
        this.mass = 0;
        this.invMass = 0;
    }
}
