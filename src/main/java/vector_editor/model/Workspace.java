package vector_editor.model;

import vector_editor.model.Shapes.ShapeObject;

import java.util.ArrayList;

public class Workspace {
    private int height;
    private int width;
    private String name;
    private ArrayList<ShapeObject> shapes;      //initially only shapes, wothout layer


    public Workspace(int height, int width, String name){
        this.height=height;
        this.width=width;
        this.name=name;
        shapes=new ArrayList<>();
    }

    public void addShape( ShapeObject newShape){
        shapes.add(newShape);
       // System.out.println(this.toString());

    }
    public void removeShape(int index){
        shapes.remove(index);
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
