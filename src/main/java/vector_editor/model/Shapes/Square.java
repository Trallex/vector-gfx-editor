package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Square extends Rectangle {

    public Square(double x, double y, double x2, double y2, Color backgroundColor, Color strokeColor, float strokeThickness)
    {
        super(x, y, x2, y2, backgroundColor, strokeColor, strokeThickness);
    }

    @Override
    public double calcHeight() {
        return  Math.max(super.calcHeight(), super.calcWidth());
    }
    @Override
    public double calcWidth() {
        return  Math.max(super.calcHeight(), super.calcWidth());
    }

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        //change the starting point of drawing depends on user's behave
        double startX = getX() > getX2() ? getX() - calcWidth() : getX();
        double startY = getY() > getY2() ? getY() - calcWidth() : getY();

        double width = calcWidth();
        double height = width;

        VectorGraphics vg = VectorGraphics.create(g);
        vg.setColor(backgroundColor);
        vg.fillRect(startX, startY, width, height);
        vg.setColor(strokeColor);
        vg.setStroke(new BasicStroke(strokeThickness));
        vg.drawRect(startX,startY,width,height);
    }

}