package vector_editor.model;

import java.awt.*;

public class CurrentShape {  //the static info about current shape and color selected in a toolbar
    private static ToolEnum shapeType = ToolEnum.RECTANGLE;  //initial selected shape and color
    public static Color getBackgroundColor() {
        return backgroundColor;
    }
    public static void setBackgroundColor(Color backgroundColor) {
        CurrentShape.backgroundColor = backgroundColor;
    }
    public static Color getStrokeColor() {
        return strokeColor;
    }
    public static void setStrokeColor(Color strokeColor) {
        CurrentShape.strokeColor = strokeColor;
    }
    private static Color backgroundColor = Color.WHITE;
    private static Color strokeColor = Color.black;
    public static float getStrokeThickness() {
        return strokeThickness;
    }
    public static void setStrokeThickness(float strokeThickness) {
        CurrentShape.strokeThickness = strokeThickness;
    }
    private static float strokeThickness = 0.f;
    public static ToolEnum getShapeType()
    {
        return shapeType;
    }
    public static void setShapeType(ToolEnum shapeType)
    {
        CurrentShape.shapeType = shapeType;
    }
}
