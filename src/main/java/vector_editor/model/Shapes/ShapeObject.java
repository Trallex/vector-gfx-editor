package vector_editor.model.Shapes;

import java.awt.*;

public abstract class ShapeObject {

    protected double x, y;
    protected double x2, y2;
    protected Color backgroundColor;
    protected Color strokeColor;

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
    }

    public  abstract boolean ifPointBelongToField(Point p);
    public abstract void draw(Graphics g);

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
//to test
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
