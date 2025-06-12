package evo.render;

import evo.logic.Cell;
import evo.logic.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Form extends JFrame {
    public int width, height;
    public JPanel canvas = new JPanel(){
        @Override
        public void paint(Graphics g) {
            ((Graphics2D) g).drawImage(canvas_img, null, 0, 0);
        }
    };
    public BufferedImage canvas_img, canvas_buffer;

    public Form(int width, int height){
        this.width = width;
        this.height = height;
        canvas_img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        canvas_buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Container container = getContentPane();
        container.add(canvas, BorderLayout.CENTER);

        pack();
        setSize(width + 10, height + 20);
        setTitle("EvoSandBox-Smooth v0.0.1_test");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void redraw() {
        Graphics2D buf = canvas_buffer.createGraphics();

        buf.setColor(Color.lightGray);
        buf.fillRect(0, 0, width, height);
        for (Cell cell : World.INSTANCE.cells){
            buf.setColor(cell.color);
            buf.fillOval((int)cell.pos.getX() - cell.radius, (int)cell.pos.getY() - cell.radius, 2 * cell.radius, 2 * cell.radius);
//            buf.setColor(Color.red);
//            buf.drawLine((int) cell.pos.getX(), (int) cell.pos.getY(), (int)(cell.pos.getX() + cell.vel.getX() * 100), (int)(cell.pos.getY() + cell.vel.getY() * 100));
        }

        canvas_img.createGraphics().drawImage(canvas_buffer, null, 0, 0);
        canvas.repaint();
    }
}
