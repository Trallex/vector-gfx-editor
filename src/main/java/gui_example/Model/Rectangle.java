package main.java.gui_example.Model;

import org.freehep.graphics2d.VectorGraphics;

import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class Rectangle extends Shape {
    private Point pointClicked;
    private SwingPropertyChangeSupport propChangeFirer;
    public Rectangle() {
        propChangeFirer = new SwingPropertyChangeSupport(this);
    }

    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
    }

    public void setFirstPoint(Point firstPoint){
        Point oldVal = this.secondPoint;
        this.secondPoint=firstPoint;
        System.out.format("\nProperty changed, first point: %s, %s",firstPoint.getX(), firstPoint.getY());

    }

    public void setSecondPoint(Point secondPoint){
        Point oldVal = this.firstPoint;
        this.firstPoint=secondPoint;
        System.out.format("\nProperty changed, second point: %s, %s",secondPoint.getX(), secondPoint.getY());
        propChangeFirer.firePropertyChange("point", oldVal, pointClicked);


    }
    @Override
    public void paint(Graphics g) {

        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        double x1 = firstPoint.getX(), y1= firstPoint.getY(), x2 = secondPoint.getX(), y2 =secondPoint.getY();
        vg.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1-x2),  Math.abs(y1-y2));

    }
}
