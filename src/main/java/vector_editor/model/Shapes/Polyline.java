package vector_editor.model.Shapes;

import org.freehep.graphics2d.VectorGraphics;

import java.awt.*;
import java.util.ArrayList;

public class Polyline extends ShapeObject {

    private ArrayList<Point> points;

    public Polyline(double x, double y, double x2, double y2, Color backgroundColor, Color strokeColor, float strokeThickness)
    {
        super(x, y, x2, y2, backgroundColor, strokeColor, strokeThickness);
        points=new ArrayList<>();
    }

    public Polyline(Polyline polyline) {
        super(polyline);
        points = new ArrayList<>();
        for (Point point : polyline.getPoints()) {
            this.points.add(point);
        }
    }
    @Override
    public boolean ifPointBelongToField(Point p) {
        return false;
    }

    public void addPoint(Point point){
        points.add(point);
    }

    public ArrayList<Point> getPoints() {
        return points;
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
        vg.setColor(backgroundColor);
        vg.fillPolygon(xPoints, yPoints, nPoints);
        if (strokeThickness > 0) {
            vg.setColor(strokeColor);
            vg.setStroke(new BasicStroke(strokeThickness));
            vg.drawPolyline(xPoints, yPoints, nPoints);
        }
    }





    @Override
    public String toString() {
        String string = "";
        for (Point point : points
                ) {
            string += point.getX() + " " + point.getY() + " ";

        }
        return string;
    }

    @Override
    public ShapeObject clone() {
        return new Polyline(this);

    }
}
