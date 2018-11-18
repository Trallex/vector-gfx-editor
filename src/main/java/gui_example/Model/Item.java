package main.java.gui_example.Model;

import javax.swing.event.SwingPropertyChangeSupport;
import java.awt.*;
import java.beans.PropertyChangeListener;

public class Item{

    private Point pointClicked;
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
        System.out.format("Property changed, new point: %s, %s",pointClicked.getX(), pointClicked.getY());

        //after executing this, the controller will be notified that the new point has been set. Its then the controller's
        //task to decide what to do when the item in the model has changed. Ideally, the controller will update the view about this
        propChangeFirer.firePropertyChange("pointClicked", oldVal, pointClicked);
    }
    public Point getPointClicked() {
        return pointClicked;
    }



}
