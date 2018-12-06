package vector_editor.model;

import java.awt.*;

public abstract class ShapeObject {

    private int x, y;
    private int x2, y2;
    private Color color;

    public ShapeObject(int x, int y, int x2, int y2, Color c)
    {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        color = c;
    }

    public abstract void draw(Graphics g); //render

    public int calcWidth()
    {
        return x2 - x;
    }

    public int calcHeight()
    {
        return y2 - y;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public int getX2()
    {
        return x2;
    }

    public void setX2(int x2)
    {
        this.x2 = x2;
    }

    public int getY2()
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



}
