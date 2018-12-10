package vector_editor.model;

import java.awt.*;

public class CurrentShape {  //the static info about current shape and color selected in a toolbar

    private static ShapeEnum shapeType = ShapeEnum.RECTANGLE;  //initial selected shape and color
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
