package vector_editor.model.Shapes;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class RectangleTest {

    private static Rectangle rectangleTest = new Rectangle(0.1, 0.2, 1.1, 2.2, Color.BLACK,Color.BLUE, 2);
    double delta = 0;

    @Test
    public void ifPointBelongToField() {
        Point out = new Point(10, 20);
        Point in = new Point(1, 1);
        Assert.assertFalse(rectangleTest.ifPointBelongToField(out));
        Assert.assertTrue(rectangleTest.ifPointBelongToField(in));
    }

    @Test
    public void getStrokeThickness(){
        Assert.assertEquals(2, rectangleTest.getStrokeThickness(),delta);
    }

    @Test
    public void calcWidth() {
        Assert.assertEquals(1, rectangleTest.calcWidth(), delta);
    }

    @Test
    public void calcHeight() {
        Assert.assertEquals(2, rectangleTest.calcHeight(), delta);
    }

    @Test
    public void getX(){

        Assert.assertEquals(0.1, rectangleTest.getX(), delta) ;
    }

    @Test
    public void getY() {
        Assert.assertEquals(0.2, rectangleTest.getY(), delta) ;
    }

    @Test
    public void getX2() {
        Assert.assertEquals(1.1, rectangleTest.getX2(), delta) ;
    }

    @Test
    public void getY2() {
        Assert.assertEquals(2.2, rectangleTest.getY2(), delta) ;
    }

    @Test
    public void getBackgroundColor() {
        Assert.assertEquals(Color.BLACK, rectangleTest.getBackgroundColor());
    }

    @Test
    public void getStrokeColor() {
        Assert.assertEquals(Color.BLUE, rectangleTest.getStrokeColor());
    }

}