package main.java.gui_example.ui.controller;

import main.java.gui_example.Model.Rectangle;
import main.java.gui_example.ui.view.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainFrameController implements PropertyChangeListener {

    private MainFrame view;
    private main.java.gui_example.Model.Rectangle model;

    public MainFrameController(MainFrame view, Rectangle model) {
        this.view=view;
        this.model=model;
        this.model.addListener(this);
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

        if("point".equalsIgnoreCase(propName)){

            System.out.println("\nChanged property of point in the model");
            view.getDrawPanel().addShape(model);
            view.getDrawPanel().repaint();
        }
    }


    private class mouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            //System.out.println("Mouse clicked");

        }

        @Override
        public void mousePressed(MouseEvent e) {
           model.setFirstPoint(e.getPoint());



        }

        @Override
        public void mouseReleased(MouseEvent e) {
            model.setSecondPoint(e.getPoint());



        }

        @Override
        public void mouseEntered(MouseEvent e) {



        }

        @Override
        public void mouseExited(MouseEvent e) {



        }


    }

    private class mouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseDragged(MouseEvent e) {



        }

        @Override
        public void mouseMoved(MouseEvent e) {



        }
    }
}
