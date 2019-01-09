package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;
import java.awt.geom.Point2D;

public class Circle extends Oval {

    public Circle(double x, double y, double x2, double y2, Color backgroundColor, Color strokeColor, float strokeThickness) {
        super(x, y, x2, y2, backgroundColor, strokeColor, strokeThickness);
    }


    public double calcDiameter()
    {
        return Math.sqrt((Math.pow(calcHeight(), 2)+Math.pow(calcWidth(),2)));
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.setColor(backgroundColor);
        vg.fillOval(Math.min(x, x2), Math.min(y, y2), calcDiameter(), calcDiameter());
        if (strokeThickness > 0) {
            vg.setColor(strokeColor);
            vg.setStroke(new BasicStroke(strokeThickness));
            vg.drawOval(Math.min(x, x2), Math.min(y, y2), calcDiameter(), calcDiameter());
        }
    }

    @Override
    public boolean ifPointBelongToField(Point p) {
        Point center = new Point();
        center.setLocation((x+x2)/2,(y+y2)/2);
        double  radius = Point2D.distance(center.getX(),center.getY(), x, y);
        this.setSelected(radius >= Point2D.distance(center.getX(), center.getY(), p.getX(), p.getY()));
        return this.isSelected();
    }
}