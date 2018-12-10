package vector_editor.model.Shapes;

import java.awt.*;

public abstract class ShapeObject {

    protected double x, y;
    protected double x2, y2;
    protected Color backgroundColor;
    protected Color borderColor;

    public ShapeObject(double x, double y, double x2, double y2, Color backgroundColor, Color borderColor)
    {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }


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

    public void setX(int x)
    {
        this.x = x;
    }

    public double getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public double getX2()
    {
        return x2;
    }

    public void setX2(int x2)
    {
        this.x2 = x2;
    }

    public double getY2()
    {
        return y2;
    }

    public void setY2(int y2)
    {
        this.y2 = y2;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
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
                ", BorderColor=" + borderColor +
                '}';
    }
}
