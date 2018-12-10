package vector_editor.model;

import java.awt.*;

public class CurrentShape {  //the static info about current shape and color selected in a toolbar

    private static ShapeEnum shapeType = ShapeEnum.RECTANGLE;  //initial selected shape and color

    public static Color getBacgroundColor() {
        return bacgroundColor;
    }

    public static void setBacgroundColor(Color bacgroundColor) {
        CurrentShape.bacgroundColor = bacgroundColor;
    }

    public static Color getBorderColor() {
        return borderColor;
    }

    public static void setBorderColor(Color borderColor) {
        CurrentShape.borderColor = borderColor;
    }

    private static Color bacgroundColor = Color.WHITE;
    private static Color borderColor = Color.black;

    public static ShapeEnum getShapeType()
    {
        return shapeType;
    }

    public static void setShapeType(ShapeEnum shapeType)
    {
        CurrentShape.shapeType = shapeType;
    }


}
