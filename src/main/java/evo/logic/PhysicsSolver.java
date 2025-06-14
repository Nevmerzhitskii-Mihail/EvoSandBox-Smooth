package evo.logic;

import evo.mathf.Vec2;

import java.util.ArrayList;
import java.util.HashSet;

public class PhysicsSolver {
    public static final double ELASTICITY = 1;
    public static final double FRICTION = 0;
    public static final Vec2 gravity = new Vec2(0, 0.0098);
    public static final int CALC_STEPS = 2;

    public static ArrayList<CircleObj> objects = new ArrayList<>();
    public static HashSet<CircleObj> addedObjects = new HashSet<>();
    public static HashSet<CircleObj> removedObjects = new HashSet<>();

    public static void AddObject(CircleObj obj){
        addedObjects.add(obj);
    }

    public static void RemoveObject(CircleObj obj){
        removedObjects.remove(obj);
    }

    public static void UpdateObjectsList(){
        objects.addAll(addedObjects);
        objects.removeAll(removedObjects);
        addedObjects.clear();
        removedObjects.clear();
    }

    public static void ApplyGravity(){
        for (int cur = 0; cur < objects.size(); cur++) {
            CircleObj current_object = objects.get(cur);
            for (int collide = cur + 1; collide < objects.size(); collide++) {
                CircleObj collide_object = objects.get(collide);
                if (!IsCollided(current_object, collide_object)) {
                    Vec2 delta = collide_object.position.sub(current_object.position);
                    Vec2 impulse = delta.normalized().mul(0.05 * collide_object.mass * current_object.mass / (delta.length() * delta.length()));
                    ApplyImpulses(current_object, collide_object, impulse);
                }
            }
        }
    }

    public static void PhysicStep(){
        for (int cur = 0; cur < objects.size(); cur++){
            CircleObj current_object = objects.get(cur);
            for (int collide = cur + 1; collide < objects.size(); collide++){
                CircleObj collide_object = objects.get(collide);
                if (IsCollided(current_object, collide_object)){
                    CalculateCollision(current_object, collide_object);
                }
            }
            CalculateBorderCollision(current_object);
            current_object.Recalculate();
        }
        UpdateObjectsList();
    }

    public static boolean IsCollided(CircleObj current, CircleObj other){
        return current.position.sub(other.position).length() <= current.radius + other.radius;
    }

    public static void CalculateCollision(CircleObj current, CircleObj other){
        Vec2 relPos = other.position.sub(current.position);
        Vec2 relVel = other.velocity.sub(current.velocity);
        Vec2 normal = relPos.normalized();
        double velProj = relVel.projLength(normal);
        double penetration = current.radius + other.radius - relPos.length();
        ApplyPseudoImpulses(current, other, normal.mul(-Math.max(penetration - 0.2, 0) * 1 / (current.invMass + other.invMass)));
        Vec2 friction = relVel.proj(normal.rotated(90)).mul(FRICTION / (current.invMass + other.invMass));
        ApplyImpulses(current, other, friction);
        if (velProj < 0)
            ApplyImpulses(current, other, normal.mul((1 + ELASTICITY) * velProj / (current.invMass + other.invMass)));
    }

    public static void CalculateBorderCollision(CircleObj current){
        if (current.position.x <= current.radius){
            Vec2 normal = new Vec2(1, 0);
            double velProj = current.velocity.projLength(normal);
            Vec2 friction = current.velocity.proj(normal.rotated(90)).mul(-FRICTION * current.mass);
            current.ApplyImpulse(friction);
            if (velProj < 0)
                current.ApplyImpulse(normal.mul(-(1 + ELASTICITY) * velProj * current.mass));
            current.ApplyPseudoImpulse(normal.mul((current.radius - current.position.x) * 1 * current.mass));
        }
        if (World.INSTANCE.width - current.position.x <= current.radius){
            Vec2 normal = new Vec2(-1, 0);
            double velProj = current.velocity.projLength(normal);
            Vec2 friction = current.velocity.proj(normal.rotated(90)).mul(-FRICTION * current.mass);
            current.ApplyImpulse(friction);
            if (velProj < 0)
                current.ApplyImpulse(normal.mul(-(1 + ELASTICITY) * velProj * current.mass));
            current.ApplyPseudoImpulse(normal.mul((current.radius + current.position.x - World.INSTANCE.width) * 1 * current.mass));
        }
        if (current.position.y <= current.radius){
            Vec2 normal = new Vec2(0, 1);
            double velProj = current.velocity.projLength(normal);
            Vec2 friction = current.velocity.proj(normal.rotated(90)).mul(-FRICTION * current.mass);
            current.ApplyImpulse(friction);
            if (velProj < 0)
                current.ApplyImpulse(normal.mul(-(1 + ELASTICITY) * velProj * current.mass));
            current.ApplyPseudoImpulse(normal.mul((current.radius - current.position.y) * 1 * current.mass));
        }
        if (World.INSTANCE.height - current.position.y <= current.radius){
            Vec2 normal = new Vec2(0, -1);
            double velProj = current.velocity.projLength(normal);
            Vec2 friction = current.velocity.proj(normal.rotated(90)).mul(-FRICTION * current.mass);
            current.ApplyImpulse(friction);
            if (velProj < 0)
                current.ApplyImpulse(normal.mul(-(1 + ELASTICITY) * velProj * current.mass));
            current.ApplyPseudoImpulse(normal.mul((current.radius + current.position.y - World.INSTANCE.height) * 1 * current.mass));
        }
    }

    public static void ApplyImpulses(CircleObj current, CircleObj other, Vec2 impulse){
        current.ApplyImpulse(impulse);
        other.ApplyImpulse(impulse.mul(-1));
    }

    public static void ApplyPseudoImpulses(CircleObj current, CircleObj other, Vec2 impulse){
        current.ApplyPseudoImpulse(impulse);
        other.ApplyPseudoImpulse(impulse.mul(-1));
    }
}
