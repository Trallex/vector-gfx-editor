package vector_editor.model;

import java.awt.*;

public class CurrentShape {

    private static ShapeEnum shapeType = ShapeEnum.RECTANGLE;
    private static Color shapeColor = Color.BLACK;

    public static ShapeEnum getShapeType()
    {
        return shapeType;
    }

    public static void setShapeType(ShapeEnum shapeType)
    {
        CurrentShape.shapeType = shapeType;
    }

    public static Color getShapeColor()
    {
        return shapeColor;
    }

    public static void setShapeColor(Color shapeColor)
    {
        CurrentShape.shapeColor = shapeColor;
    }


}
