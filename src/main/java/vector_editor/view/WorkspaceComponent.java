package main.java.vector_editor.view;

import org.freehep.graphics2d.VectorGraphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WorkspaceComponent extends JComponent {
    private Image image;
    private VectorGraphics vg;
    private int currentX, currentY, oldX, oldY;


    public WorkspaceComponent(int width, int height) {
        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
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
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);
            vg = (VectorGraphics) image.getGraphics();
            vg.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        vg.drawImage(image, 0, 0, null);
    }

    private void clear() {
        vg.setPaint(Color.white);
        vg.fillRect(0, 0, getSize().width, getSize().height);
        vg.setPaint(Color.black);
        repaint();
    }
}
