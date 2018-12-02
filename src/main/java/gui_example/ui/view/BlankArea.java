package main.java.gui_example.ui.view;

import main.java.gui_example.Model.Shape;
import org.freehep.graphics2d.VectorGraphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlankArea extends JPanel {

    private Dimension minSize = new Dimension(100, 50);
    public ArrayList<Shape> shapeList = new ArrayList<>();

    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }


    public void addShape(Shape newShape){
        shapeList.add(newShape);
    }
    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }

    public void paintComponent(Graphics g) {

        if (g == null) return;

        VectorGraphics vg = VectorGraphics.create(g);

        for (Shape shape: shapeList) {
            shape.paint(vg);

        }

    }


}