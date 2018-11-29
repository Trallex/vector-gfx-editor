package main.java.gui_example;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.MouseListener;


public class MouseTestPanel extends JPanel implements MouseListener, MouseMotionListener {
    private static final int DEFAULT_WIDTH = 200;
    private static final int DEFAULT_HEIGHT = 200;
    private boolean isPressed;

    private int x, y, x2, y2;

    ArrayList<Point> startingPoints = new ArrayList<Point>();
    ArrayList<Point> endingPoints = new ArrayList<Point>();

    public MouseTestPanel() {
        addMouseListener(this);
        setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        System.out.println("mouseDragged");
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        System.out.println("mouseMoved");
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        startingPoints.add(new Point(x, y));

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        x2 = e.getX();
        y2 = e.getY();
        endingPoints.add(new Point(x2, y2));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.BLACK);
        drawRectangles(g2d);
    }

    private void drawRectangles(Graphics2D g2d) {
        int x, y, x2, y2;
        Point beg, end;
        for(int i=0; i<endingPoints.size(); i++)
        {
            beg = startingPoints.get(i);
            x = beg.x;
            y = beg.y;
            end = endingPoints.get(i);
            x2 = end.x;
            y2 = end.y;
            g2d.draw(new Rectangle2D.Double(Math.min(x, x2), Math.min(y, y2), Math.abs(x-x2),  Math.abs(y-y2)));
        }
    }
}
