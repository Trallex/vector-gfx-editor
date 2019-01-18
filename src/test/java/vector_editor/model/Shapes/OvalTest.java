package vector_editor.model.Shapes;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class OvalTest {
    Oval ovalTest =  new Oval(0.1, 0.2, 1.1, 2.2, Color.BLACK,Color.BLUE, 2);
    double delta;
    @Test
    public void ifPointBelongToField() {
        Point out = new Point(10, 20);
        Assert.assertFalse(ovalTest.ifPointBelongToField(out));
    }

    @Test
    public void getStrokeThickness() {
        Assert.assertEquals(2, ovalTest.getStrokeThickness(),delta);
    }

    @Test
    public void calcWidth() {
        Assert.assertEquals(1, ovalTest.calcWidth(), delta);
    }

    @Test
    public void calcHeight() {
        Assert.assertEquals(2, ovalTest.calcHeight(), delta);
    }

    @Test
    public void getX(){

        Assert.assertEquals(0.1, ovalTest.getX(), delta) ;
    }

    @Test
    public void getY() {
        Assert.assertEquals(0.2, ovalTest.getY(), delta) ;
    }

    @Test
    public void getX2() {
        Assert.assertEquals(1.1, ovalTest.getX2(), delta) ;
    }

    @Test
    public void getY2() {
        Assert.assertEquals(2.2, ovalTest.getY2(), delta) ;
    }

    @Test
    public void getBackgroundColor() {
        Assert.assertEquals(Color.BLACK, ovalTest.getBackgroundColor());
    }

    @Test
    public void getStrokeColor() {
        Assert.assertEquals(Color.BLUE, ovalTest.getStrokeColor());
    }
}