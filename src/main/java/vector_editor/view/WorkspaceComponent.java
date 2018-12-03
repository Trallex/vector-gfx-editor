package main.java.vector_editor.view;

import org.freehep.graphics2d.VectorGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkspaceComponent extends JComponent {
    private VectorGraphics vg;
    private int currentX, currentY, oldX, oldY;
    int width, height;

    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public WorkspaceComponent(int width, int height) {
        this.width = width;
        this.height = height;
        this.setDoubleBuffered(false);
        this.setOpaque(false);
        this.setPreferredSize(this.getPreferredSize());
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                oldX = e.getX();
                oldY = e.getY();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                if (vg != null) {
                    vg.drawLine(oldX, oldY, currentX, currentY);
                    repaint();
                    oldX = currentX;
                    oldY = currentY;
                }
            }
        });
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }

    @Override
    protected void paintComponent(Graphics g) {

        vg = VectorGraphics.create(g);

        Dimension dim = getSize();
        Insets insets = getInsets();

        vg.setColor(Color.white);
        vg.fillRect(insets.left, insets.top,
                dim.width - insets.left - insets.right,
                dim.height - insets.top - insets.bottom);
        vg.setColor(Color.black);
        vg.drawLine(10.0, 10.0, this.getWidth() - 10, this.getHeight() - 10);


    }

    private void clear() {
        vg.setPaint(Color.white);
        vg.fillRect(0, 0, getSize().width, getSize().height);
        vg.setPaint(Color.black);
        repaint();
    }
}
