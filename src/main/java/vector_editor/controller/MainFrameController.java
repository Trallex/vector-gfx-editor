package vector_editor.controller;

import vector_editor.model.*;
import vector_editor.model.Rectangle;
import vector_editor.view.MainView;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrameController {

    //final static Logger logger = Logger.getLogger(ToolsMenu.class);

    private MainView view;
    private Rectangle model; //temporary!!

    private ShapeObject drawShape;

    public MainFrameController(MainView view, Rectangle model) //temporary model..
    {
        this.view = view;
        this.model = model;

        this.view.getToolbarComponent().addToolbarComponentListener(new ToolbarComponentListener());
        this.view.getWorkspaceComponent().addWorkspaceComponentMouseListener(new MouseListenerForWorkspace());
        this.view.getWorkspaceComponent().addWorkspaceComponentMouseMotionListener(new MouseMotionListenerForWorkspace());

    }



    class ToolbarComponentListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "pencil":
                    CurrentShape.setShapeType(ShapeEnum.PENCIL);
                    System.out.println("PENCIL");
                    break;
                case "square":   //the best change the name to rectangle in the view
                    CurrentShape.setShapeType(ShapeEnum.RECTANGLE);
                    System.out.println("SQUARE");
                    break;
                case "move":
                    System.out.println("MOVE");
                    break;
                case "pen":
                    System.out.println("PEN");
                    break;
                case "circle":
                    System.out.println("CIRCLE");
                    break;
                case "zoom":
                    System.out.println("ZOOM");
                    break;
                case "text":
                    System.out.println("TEXT");
                    break;
                case "bitmap":
                    System.out.println("BITMAP");
                    break;

            }


        }
    }

    class MouseMotionListenerForWorkspace implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent event) //need to implement the method for the pencil
        {
            if (!(drawShape == null))
            {
                if (drawShape instanceof Pencil)
                {
                    System.out.println("here");
                    System.out.println(event.getY());
                    ((Pencil) drawShape).addPoint(new Point(event.getX(), event.getY()));
                }

                else
                {
                    drawShape.setX2(event.getX());
                    drawShape.setY2(event.getY());
                }
                view.getWorkspaceComponent().setTmpShape(drawShape);
                view.getWorkspaceComponent().repaint();
            }



        }

        @Override
        public void mouseMoved(MouseEvent arg0)
        {
        }

    }

    class MouseListenerForWorkspace implements MouseListener
    {
        public void mouseClicked(MouseEvent e){}

        public void mouseEntered(MouseEvent e){}

        public void mouseExited(MouseEvent e){}


        public void mousePressed(MouseEvent e)
        {
            drawShape = getTmpShape(e.getX(), e.getY(), e.getX(), e.getY());
            view.getWorkspaceComponent().setTmpShape(drawShape);
            view.getWorkspaceComponent().repaint();
        }

        public void mouseReleased(MouseEvent e)
        {
            if (!(drawShape == null))
            {
                drawShape.setX2(e.getX());
                drawShape.setY2(e.getY());
                System.out.println(drawShape.toString());
                ArrayList<ShapeObject> shapes = view.getWorkspaceComponent().getShapes();
                shapes.add(drawShape);
                view.getWorkspaceComponent().setTmpShape(null);
                view.getWorkspaceComponent().setShapes(shapes);
                drawShape = null;
                view.getWorkspaceComponent().repaint();

            }
        }

        private ShapeObject getTmpShape(int x, int y, int x2, int y2)
        {
            switch (CurrentShape.getShapeType())
            {
                case RECTANGLE:
                    return new Rectangle(x, y, x2, y2, CurrentShape.getShapeColor());
                case PENCIL:
                    System.out.println("pencil");
                   return new Pencil(x, y, x2, y2, CurrentShape.getShapeColor());
                default:
                    break;
            }
            return drawShape;
        }
    }


}
