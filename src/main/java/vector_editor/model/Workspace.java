package vector_editor.model;

import vector_editor.model.Shapes.ShapeObject;

import java.awt.*;
import java.util.ArrayList;

public class Workspace {
    private int height;
    private int width;
    private String name;
    private ArrayList<ShapeObject> shapes;      //initially only shapes, without layer


    public Workspace(int width, int height, String name) {
        this.height=height;
        this.width=width;
        this.name=name;
        shapes=new ArrayList<>();
    }

    public void addShape( ShapeObject newShape){
        shapes.add(newShape);
    }
    public void removeShape(int index){
        shapes.remove(index);
    }
    public void removeLastShape(){
        if(!shapes.isEmpty())
        {
            shapes.remove(shapes.size()-1);
        }
    }

    public int findDrawnShapesId(Point p)
    {
        int index = 0;
        int lastItem = -1;
        for (ShapeObject obj: shapes) {
            if(obj.ifPointBelongToField(p))
                lastItem = index;
            index++;
        }
        return lastItem;
    }
    //to test
    @Override
    public String toString() {
        return "Workspace{" +
                "height=" + height +
                ", width=" + width +
                ", name='" + name + '\'' +
                ", shapes=" + shapes +
                '}';
    }
}
