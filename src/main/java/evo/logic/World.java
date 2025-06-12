package evo.logic;

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
    public ArrayList<Cell> cells = new ArrayList<>();

    private World(int width, int height){
        this.width = width;
        this.height = height;
        for(int i = 0; i < 100; i++) {
            cells.add(new Cell(Math.random() * width, Math.random() * height, Math.random() * 2 - 1, Math.random() * 2 - 1, (int) (Math.random() * 5 + 5)));
        }
    }

    public void step(){
        for (int id = 0; id < cells.size(); id++) cells.get(id).step(id);
        for (Cell cell : cells) cell.recalc();
    }
}
