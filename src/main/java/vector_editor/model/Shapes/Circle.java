package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Circle extends Oval {

    public Circle(double x, double y, double x2, double y2, Color backgroundColor, Color borderColor) {
        super(x, y, x2, y2, backgroundColor, borderColor);
    }


    public double calcDiameter()
    {
        return Math.sqrt((Math.pow(calcHeight(), 2)+Math.pow(calcWidth(),2)));
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.setPaint(backgroundColor);
        vg.fillOval(Math.min(x, x2), Math.min(y, y2), calcDiameter(), calcDiameter());

    }
}
