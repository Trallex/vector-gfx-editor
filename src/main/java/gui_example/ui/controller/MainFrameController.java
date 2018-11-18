package main.java.gui_example.ui.controller;

import main.java.gui_example.Model.Item;
import main.java.gui_example.ui.view.MainFrame;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainFrameController implements PropertyChangeListener{


    private MainFrame view;
    private Item model;


    public MainFrameController(MainFrame view, Item model) {
        this.view=view;
        this.model=model;

        this.model.addListener(this);
        view.setVisible(true);
        initListeners();
    }


    private void initListeners() {

        view.getDrawPanel().addMouseMotionListener(new mouseMotionListener());
        view.getDrawPanel().addMouseListener(new mouseListener());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propName = evt.getPropertyName();
        Object newVal = evt.getNewValue();

        if("pointClicked".equalsIgnoreCase(propName)){

            System.out.println("\nChanged property of point in the model");
           // view.getTxtAddress().setText((String)newVal);
        }
    }


    private class mouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("Mouse clicked");

        }

        @Override
        public void mousePressed(MouseEvent e) {
            model.setFirstPoint(e.getPoint());
            //System.out.println("Mouse pressed");


        }

        @Override
        public void mouseReleased(MouseEvent e) {
            model.setSecondPoint(e.getPoint());
            //System.out.println("Mouse released");


        }

        @Override
        public void mouseEntered(MouseEvent e) {
            //System.out.println("Mouse entred");


        }

        @Override
        public void mouseExited(MouseEvent e) {
            //System.out.println("Mouse exited");


        }


    }

    private class mouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {
            //System.out.println("Mouse dragged");


        }

        @Override
        public void mouseMoved(MouseEvent e) {
            //System.out.println("Mouse moved");


        }
    }
}




