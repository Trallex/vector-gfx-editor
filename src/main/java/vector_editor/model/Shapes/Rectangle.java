package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Rectangle extends ShapeObject {


    public Rectangle(double x, double y, double x2, double y2, Color backgroundColor, Color strokeColor, float strokeThickness)
    {
        super(x, y, x2, y2, backgroundColor, strokeColor, strokeThickness);
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.setColor(backgroundColor);
        vg.fillRect(Math.min(x, x2), Math.min(y, y2), calcWidth(), calcHeight());
        if (strokeThickness > 0) {
            vg.setColor(strokeColor);
            vg.setStroke(new BasicStroke(strokeThickness));
            vg.drawRect(Math.min(x, x2), Math.min(y, y2), calcWidth(), calcHeight());
        }
    }
    @Override
    public boolean ifPointBelongToField(Point p)
    {
        this.setSelected(p.getX() <= Math.max(x, x2) && p.getX() >= Math.min(x, x2)
                && p.getY() <= Math.max(y, y2) && p.getY() >= Math.min(y, y2));
        return isSelected();
    }

    @Override
    public String toString() {
        return "rect";
    }
}