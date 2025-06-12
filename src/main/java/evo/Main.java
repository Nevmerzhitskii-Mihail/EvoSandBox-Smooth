package evo;

import evo.logic.World;
import evo.render.Form;

public class Main {
    public static int screen_width = 1600;
    public static int screen_height = 900;
    public static void main(String[] args) {
        Form main_window = new Form(screen_width, screen_height);
        World world = World.Init(screen_width, screen_height);
        while (true){
            main_window.redraw();
            world.step();
        }
    }
}
