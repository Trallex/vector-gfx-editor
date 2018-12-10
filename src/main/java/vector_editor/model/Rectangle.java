package vector_editor.model;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Rectangle extends ShapeObject {


    public Rectangle(int x, int y, int x2, int y2, Color c) {
        super(x, y, x2, y2, c);
    }


    @Override
    public void draw(Graphics g) {

        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.drawRect(Math.min(x, x2), Math.min(y, y2), calcWidth(), calcHeight());
    }


}
