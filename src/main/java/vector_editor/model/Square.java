package vector_editor.model;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Square extends Rectangle{

    private int x, y;
    private Color color;

    public Square(int x, int y, int x2, int y2, Color c)
    {
        super(x, y, x2, y2, c);
        this.x = Math.min(x,x2);
        this.y = Math.min(y,y2);
        color = c;
    }


    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public int calcHeight()
    {
        return Math.abs(y-super.getY2());
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

    @Override
    public void draw(Graphics g) {
        if (g == null) return;
        VectorGraphics vg = VectorGraphics.create(g);
        vg.drawRect(x, y, calcHeight(), calcHeight());

    }
}
