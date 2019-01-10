package vector_editor.view;

import org.freehep.graphics2d.VectorGraphics;
import vector_editor.model.Shapes.ShapeObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class WorkspaceComponent extends JComponent {
    private VectorGraphics vg;
    private int currentX, currentY, oldX, oldY;
    int width, height;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    String name;

    //new code
    private ArrayList<ShapeObject> shapes = new ArrayList<>();
    private ShapeObject demoShape;
    //
    @Override
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public WorkspaceComponent(int width, int height, String name) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.setDoubleBuffered(false);
        this.setOpaque(false);
        this.setPreferredSize(this.getPreferredSize());

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), getHeight());
    }



    private void clear() {
        vg.setPaint(Color.white);
        vg.fillRect(0, 0, getSize().width, getSize().height);
        vg.setPaint(Color.black);
        repaint();
    }

    //add mouse listeners to the workspace in the controller
    public void addWorkspaceComponentMouseListener(MouseListener listenForMouse)
    {
        this.addMouseListener(listenForMouse);
    }

    public void addWorkspaceComponentMouseMotionListener(MouseMotionListener listenForMouse)
    {
        this.addMouseMotionListener(listenForMouse);
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        for (ShapeObject s : shapes)
        {
            if (s != null) {
                if (s.isSelected()) s.drawHighlight(g);
                s.draw(g);
            }
        }

        if (demoShape != null) demoShape.draw(g);   //paint component during drawing
    }

    public ArrayList<ShapeObject> getShapes()
    {
        return shapes;
    }

    public void setShapes(ArrayList<ShapeObject>  shapes)
    {
        this.shapes = shapes;
    }

    public ShapeObject getTmpShape()
    {
        return demoShape;
    }

    public void setTmpShape(ShapeObject tmpShape) {  // temporary shape set during drawing
        this.demoShape = tmpShape;
    }



    public void removeLastShape()
    {
        if(!shapes.isEmpty())
        {
            shapes.remove(shapes.size()-1);
        }
    }
    public void removeShape(int index)
    {
        if(!shapes.isEmpty())
        {
            shapes.remove(index);
        }
    }
    public void updateShapePlace(int index, double xDifferecne, double yDifference) {

        shapes.get(index).updateShapePlace(xDifferecne, yDifference);

    }
}
