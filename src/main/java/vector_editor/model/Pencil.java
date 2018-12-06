package vector_editor.model;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;
import java.util.ArrayList;

public class Pencil extends ShapeObject {

    private int x, y;
    private int  x2, y2;
    private Color color;
    private ArrayList<Point> points;


    public Pencil(int x, int y, int x2, int y2, Color c)
    {
        super(x, y, x2, y2, c);
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        color = c;
        points= new ArrayList<>();

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
        points.add(point);
    }
    @Override
    public void draw(Graphics g) {

        if (g == null) return;
        int nPoints=points.size();
        double[] xPoints = new double[nPoints];
        double[] yPoints = new double[nPoints];
        for(int i=0; i<nPoints; i++){
            xPoints[i]=points.get(i).getX();
            yPoints[i]=points.get(i).getY();
        }
        VectorGraphics vg = VectorGraphics.create(g);
        vg.drawPolyline(xPoints,yPoints,nPoints);
    }



    //to test


    @Override
    public String toString() {
        return "Pencil{" +
                "points=" + points +
                '}';
    }
}
