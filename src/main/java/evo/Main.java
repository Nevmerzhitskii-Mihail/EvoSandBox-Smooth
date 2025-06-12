package evo;

import evo.render.Form;

public class Main {
    public static void main(String[] args) {
        int screen_width = 1960;
        int screen_height = 1800;
        Form main_window = new Form(screen_width, screen_height);
        while (true){
            main_window.repaint();
        }
    }
}
