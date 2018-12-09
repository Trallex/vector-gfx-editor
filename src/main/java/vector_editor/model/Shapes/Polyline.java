package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;

public class Polyline extends ShapeObject {

    public Polyline(double x, double y, double x2, double y2, Color c)
    {
        super(x, y, x2, y2, c);

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
