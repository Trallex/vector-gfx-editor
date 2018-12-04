package main.java.gui_example.Model;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Square extends Rectangle {

    @Override
    public void paint(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        double x1 = firstPoint.getX(), y1= firstPoint.getY(), x2 = secondPoint.getX(), y2 =secondPoint.getY();
        width = Math.abs(x1-x2);
        height = Math.abs(y1-y2);
        vg.drawRect(Math.min(x1, x2), Math.min(y1, y2), height,  height);
    }

}
