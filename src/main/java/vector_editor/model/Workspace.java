package vector_editor.model;

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

}
