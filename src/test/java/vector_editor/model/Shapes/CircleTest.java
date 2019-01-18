package vector_editor.model.Shapes;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CircleTest {
    Circle circleTest =  new Circle(0.1, 0.2, 1.1, 1.2, Color.BLACK,Color.BLUE, 2);
    double delta;

    @Test
    public void calcDiameter() {
        Assert.assertEquals(0.7071067811865476, circleTest.calcDiameter(), delta);
    }

    @Test
    public void ifPointBelongToField() {
        Point out = new Point(0,0);
        Assert.assertFalse(circleTest.ifPointBelongToField(out));

    }
}