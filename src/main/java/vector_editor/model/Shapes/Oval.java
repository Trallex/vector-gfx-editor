package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Oval extends ShapeObject {

    public Oval(double x, double y, double x2, double y2, Color backgroundColor, Color strokeColor, float strokeThickness) {
        super(x, y, x2, y2, backgroundColor, strokeColor, strokeThickness);
    }

    @Override
    public boolean ifPointBelongToField(Point p) {

        Point center = new Point();
        center.setLocation((x+x2)/2, (y+y2)/2 );
        double radiusX = Math.abs(x-x2 );
        double radiusY = Math.abs(y-y2 );
        this.setSelected(Math.pow(((p.getX() - center.getX()) / radiusX), 2) + Math.pow(((p.getY() - center.getY()) / radiusY), 2) <= 1);
        return this.isSelected();
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.setColor(backgroundColor);
        vg.fillOval(Math.min(x, x2), Math.min(y, y2), calcWidth(), calcHeight());
        if (strokeThickness > 0) {
            vg.setColor(strokeColor);
            vg.setStroke(new BasicStroke(strokeThickness));
            vg.drawOval(Math.min(x, x2), Math.min(y, y2), calcWidth(), calcHeight());
        }

    }
}