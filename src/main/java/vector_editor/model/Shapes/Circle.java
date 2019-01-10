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
        return Math.sqrt((Math.pow(calcWidth(), 2) + Math.pow(calcHeight(), 2))) / 2;
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        //change the starting point of drawing depends on user's behave
        double startX = getX() > getX2() ? getX() - calcWidth() : getX();
        double startY = getY() > getY2() ? getY() - calcWidth() : getY();
        this.setX(startX);
        this.setY(startY);

        double width = calcWidth();
        this.setX2(startX + width);
        this.setY2(startY + width);

        vg.setColor(backgroundColor);
        vg.fillOval(Math.min(x, x2), Math.min(y, y2), width, width);
        if (strokeThickness > 0) {
            vg.setColor(strokeColor);
            vg.setStroke(new BasicStroke(strokeThickness));
            vg.drawOval(Math.min(x, x2), Math.min(y, y2), width, width);
        }
    }

    @Override
    public void drawHighlight(Graphics g) {
        super.drawHighlight(g);
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