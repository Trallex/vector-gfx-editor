package vector_editor.model.Shapes;

import java.awt.*;

public abstract class ShapeObject {

    protected double x, y;
    protected double x2, y2;
    protected Color color;

    public ShapeObject(double x, double y, double x2, double y2, Color c)
    {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        color = c;
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

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }


//to test
    @Override
    public String toString() {
        return "ShapeObject{" +
                "x=" + x +
                ", y=" + y +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", color=" + color +
                '}';
    }
}
