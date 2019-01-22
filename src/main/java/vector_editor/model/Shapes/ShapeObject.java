package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public abstract class ShapeObject {

    protected double x, y;
    protected double x2, y2;
    protected Color backgroundColor;
    protected Color strokeColor;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    protected boolean isSelected;

    public float getStrokeThickness() {
        return strokeThickness;
    }

    public void setStrokeThickness(float strokeThickness) {
        this.strokeThickness = strokeThickness;
    }

    protected float strokeThickness;

    public ShapeObject(double x, double y, double x2, double y2, Color backgroundColor, Color strokeColor, float strokeThickness)
    {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.backgroundColor = backgroundColor;
        this.strokeColor = strokeColor;
        this.strokeThickness = strokeThickness;
        this.isSelected = false;
    }

    public abstract boolean ifPointBelongToField(Point p);
    public abstract void draw(Graphics g);

    public abstract ShapeObject clone();

    public void drawHighlight(Graphics g) {
        if (g == null && !this.isSelected()) return;
        assert g != null;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.setColor(Color.BLUE);
        int offset = 2;
        vg.setStroke(new BasicStroke(1));
        vg.drawRect(Math.min(x, x2) - getStrokeThickness() - offset, Math.min(y, y2) - getStrokeThickness() - offset, calcWidth()
                + 2 * getStrokeThickness() + 2 * offset, calcHeight()
                + 2 * getStrokeThickness() + 2 * offset);

    }

    public double calcWidth()
    {
        return Math.abs(x2 - x);
    }

    public double calcHeight()
    {
        return Math.abs(y2 - y);
    }

    public double getX()
    {
        return x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    public double getX2()
    {
        return x2;
    }

    public void setX2(double x2)
    {
        this.x2 = x2;
    }

    public double getY2()
    {
        return y2;
    }

    public void setY2(double y2)
    {
        this.y2 = y2;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void updateShapePlace(double xDifference, double yDifference){
        this.x += xDifference;
        this.x2 += xDifference;
        this.y += yDifference;
        this.y2 += yDifference;
    }

    public ShapeObject(ShapeObject s) {
        this.x = s.getX();
        this.y = s.getY();
        this.x2 = s.getX2();
        this.y2 = s.getY2();
        this.backgroundColor = s.backgroundColor;
        this.strokeColor = s.strokeColor;
        this.strokeThickness = s.strokeThickness;
        this.isSelected = false;
    }
    @Override
    public String toString() {
        return "ShapeObject{" +
                "x=" + x +
                ", y=" + y +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", BGcolor=" + backgroundColor +
                ", StrokeColor=" + strokeColor +
                '}';
    }


}
