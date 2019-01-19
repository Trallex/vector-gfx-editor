package vector_editor.model.Shapes;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class SquareTest {

    Square squareTest =  new Square(0.1, 1.1, 2.1, 2.2, Color.BLACK, Color.BLUE, 2);
    double delta;
    @Test
    public void getX(){

        Assert.assertEquals(0.1, squareTest.getX(), delta) ;
    }
    @Test
    public void getY(){

        Assert.assertEquals(1.1, squareTest.getY(), delta) ;
    }
    @Test
    public void getX2(){

        Assert.assertEquals(2.1, squareTest.getX2(), delta) ;
    }
    @Test
    public void getY2(){

        Assert.assertEquals(2.2, squareTest.getY2(), delta) ;
    }
    @Test
    public void calcHeight() {
        Assert.assertEquals(2, squareTest.calcHeight(), delta);
    }
    @Test
    public void calcWidth() {
        Assert.assertEquals(2, squareTest.calcWidth(), delta);
    }
}