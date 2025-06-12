package evo.render;

import javax.swing.*;

public class Form extends JFrame {
    public int width, height;

    public Form(int width, int height){
        this.width = width;
        this.height = height;
        pack();
        setSize(width, height);
        setTitle("EvoSandBox-Smooth v0.0.1_test");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
