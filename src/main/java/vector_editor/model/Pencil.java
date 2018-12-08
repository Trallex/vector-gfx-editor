package vector_editor.model;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;
import java.util.ArrayList;

public class Pencil extends ShapeObject {  //for now the separated class from Pen, had problems wth instances in the controlller


    protected ArrayList<Point> points;

    public Pencil(int x, int y, int x2, int y2, Color c)
    {
        super(x, y, x2, y2, c);
        points= new ArrayList<>();

    }


    public void addPoint(Point point){
        points.add(point);
        System.out.println(point);
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
            //System.out.println(i);
        }
        VectorGraphics vg = VectorGraphics.create(g);
        vg.drawPolyline(xPoints,yPoints,nPoints);

    }


}
