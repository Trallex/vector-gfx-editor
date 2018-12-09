package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Square extends Rectangle {



    public Square(double x, double y, double x2, double y2, Color c)
    {
        super(x, y, x2, y2, c);
    }
    public double calcSide(){
        return Math.sqrt((Math.pow(calcHeight(), 2)+Math.pow(calcWidth(),2)));
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.drawRect(Math.min(x,x2), Math.min(y,y2), calcSide(),calcSide());

    }
}
