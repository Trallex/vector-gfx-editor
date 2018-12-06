package vector_editor.model;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Rectangle extends ShapeObject {

    private int x, y;
    private int  x2, y2;
    private Color color;


    public Rectangle(int x, int y, int x2, int y2, Color c)
    {
        super(x, y, x2, y2, c);
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        color = c;

    }


    public int calcWidth()
    {
        return Math.abs(x-x2);
    }

    public int calcHeight()
    {
        return Math.abs(y-y2);
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

    public void addPoint(Point point){

    }
    @Override
    public void draw(Graphics g) {

        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.drawRect(Math.min(x, x2), Math.min(y, y2), calcWidth(), calcHeight());
    }

    //to test

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", x2=" + x2 +
                ", y2=" + y2 +
                ", color=" + color +
                '}';
    }


}
