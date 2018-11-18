package main.java.gui_example.Model;

import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class Item{

    private Point pointClicked;

    private Point firstPoint;
    private Point secondPoint;
    private SwingPropertyChangeSupport propChangeFirer;


    public Item() {
        propChangeFirer = new SwingPropertyChangeSupport(this);
    }


    public void addListener(PropertyChangeListener prop) {
        propChangeFirer.addPropertyChangeListener(prop);
    }
    public void setPointClicked(Point pointClicked){
        Point oldVal = this.pointClicked;
        this.pointClicked = pointClicked;
        //System.out.format("Property changed, new point: %s, %s",pointClicked.getX(), pointClicked.getY());

        //after executing this, the controller will be notified that the new point has been set. Its then the controller's
        //task to decide what to do when the item in the model has changed. Ideally, the controller will update the view about this
        propChangeFirer.firePropertyChange("pointClicked", oldVal, pointClicked);
    }

//used while drawing a line (first and second point)
    public void setFirstPoint(Point firstPoint){
        Point oldVal = this.secondPoint;
        this.secondPoint=firstPoint;
        System.out.format("\nProperty changed, first point: %s, %s",firstPoint.getX(), firstPoint.getY());

    }

    public void setSecondPoint(Point secondPoint){
        Point oldVal = this.firstPoint;
        this.firstPoint=secondPoint;
        System.out.format("\nProperty changed, second point: %s, %s",secondPoint.getX(), secondPoint.getY());


    }
    public Point getPointClicked() {
        return pointClicked;
    }



}
