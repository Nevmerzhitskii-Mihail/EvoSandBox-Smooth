package evo.render;

import evo.logic.CircleObj;
import evo.logic.PhysicsSolver;

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
        setSize(width + 15, height + 50);
        setTitle("EvoSandBox-Smooth v0.0.1_test");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void redraw() {
        Graphics2D buf = canvas_buffer.createGraphics();

        buf.setColor(Color.lightGray);
        buf.fillRect(0, 0, width, height);
        for (CircleObj obj : PhysicsSolver.objects){
            buf.setColor(Color.magenta);
            int x0 = (int)(obj.position.x - obj.radius);
            int y0 = (int)(obj.position.y - obj.radius);
            int size = (int)(2 * obj.radius);
            buf.fillOval(x0, y0, size, size);
            buf.setColor(Color.black);
            buf.drawOval(x0, y0, size, size);
//            buf.setColor(Color.red);
//            buf.drawLine((int) cell.pos.getX(), (int) cell.pos.getY(), (int)(cell.pos.getX() + cell.vel.getX() * 100), (int)(cell.pos.getY() + cell.vel.getY() * 100));
        }

        canvas_img.createGraphics().drawImage(canvas_buffer, null, 0, 0);
        canvas.repaint();
    }
}
