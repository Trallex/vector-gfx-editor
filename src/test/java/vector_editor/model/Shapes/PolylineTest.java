package vector_editor.model.Shapes;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PolylineTest {
    Polyline polylineTest = new Polyline(0.1, 0.2, 1.1, 2.2, Color.BLACK, Color.BLUE, 2);
    Point temp = new Point();
    double delta;
    ArrayList<Point> list = new ArrayList<Point>();

    @Test
    public void ifPointBelongToField() {
        polylineTest.addPoint(new Point(0, 0));
        Assert.assertFalse(polylineTest.ifPointBelongToField(new Point(7, 8)));
    }

    @Test
    public void getPoints() {
        temp.setLocation(0, 0);
        list.add(temp);
        temp.setLocation(0.1, 0.2);
        list.add(temp);
        list.add(new Point(9, 9));
        int index=0;
        polylineTest.addPoint(list.get(1));
        ArrayList<Point> test = polylineTest.getPoints();
       for(Point p: test){
           Assert.assertEquals(list.get(index), p);
       }
    }

    @Test
    public void getStrokeThickness(){
        Assert.assertEquals(2, polylineTest.getStrokeThickness(),delta);
    }

    @Test
    public void getBackgroundColor() {
        Assert.assertEquals(Color.BLACK, polylineTest.getBackgroundColor());
    }

    @Test
    public void getStrokeColor() {
        Assert.assertEquals(Color.BLUE, polylineTest.getStrokeColor());
    }
}