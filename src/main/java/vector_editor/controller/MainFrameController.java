package vector_editor.controller;

import vector_editor.model.CurrentShape;
import vector_editor.model.Model;
import vector_editor.model.ShapeEnum;
import vector_editor.model.Shapes.Rectangle;
import vector_editor.model.Shapes.*;
import vector_editor.model.Workspace;
import vector_editor.view.ColorChooserButton;
import vector_editor.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrameController {
    private int selectedShape =-1; //index of shape in model
    private MainView view;
    private Model model; //temporary!!
    private InputMap inputMap; //key binding
    private ActionMap actionMap; //KB
    private ShapeObject drawShape;
    private boolean isNewShapePainted; //helpful flag while using the pen
    private boolean isShiftKeyPressed = false;

    private String currentAction; // REFACTOR needed, used it in Listeners to change BG Color

    public MainFrameController(MainView view, Model model) //temporary model..
    {
        this.view = view;
        this.model = model;
        this.view.addListenerToContainer(new ContainerListenerForMainFrame());
        this.view.getToolbarComponent().addToolbarComponentListener(new ToolbarComponentListener());

        this.view.getExportComponent().addListeners(new CommandActionListener());

        this.view.getToolbarComponent().getStrokeColorBtn().addColorChangedListener(new ColorChangedListener());
        this.view.getToolbarComponent().getBackgroundColorBtn().addColorChangedListener(new ColorChangedListener());
        this.view.getToolbarComponent().getStrokeThicknessComboBox().addItemListener(new StrokeChangeListener());

        //creating key binders
        inputMap = view.getJFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        actionMap = view.getJFrame().getRootPane().getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), "shiftPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0, true), "shiftReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK, false), "ctrlZPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false), "deleteSelectedShape");

        actionMap.put("shiftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isShiftKeyPressed = true;
            }
        });
        actionMap.put("shiftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isShiftKeyPressed = false;
            }
        });
        actionMap.put("ctrlZPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Ctrl + Z");
                model.getWorkspace().removeLastShape();
                view.getWorkspaceComponent().removeLastShape();
                view.getWorkspaceComponent().repaint();
            }
        });
        actionMap.put("deleteSelectedShape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(selectedShape != -1)
                {
                    model.getWorkspace().removeShape(selectedShape);
                    view.getWorkspaceComponent().removeShape(selectedShape);
                    view.getWorkspaceComponent().repaint();
                }
            }
        });

    }


    class StrokeChangeListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                Object item = e.getItem();
                float fv = Float.parseFloat(item.toString());
                CurrentShape.setStrokeThickness(fv);
            }
        }
    }

    class ContainerListenerForMainFrame extends ContainerAdapter {
        // initial solution when the new workspace is set
        @Override
        public void componentAdded(ContainerEvent e) {
            view.getWorkspaceComponent().addWorkspaceComponentMouseListener(new MouseListenerForWorkspace()); //need to set listeners
            view.getWorkspaceComponent().addWorkspaceComponentMouseMotionListener(new MouseMotionListenerForWorkspace()); //after create the new workspace
            int width = view.getWorkspaceComponent().getWidth();
            int height = view.getWorkspaceComponent().getHeight();
            String name = view.getWorkspaceComponent().getName();
            model.setWorkspace(new Workspace(width, height, name));
        }
    }

    class ToolbarComponentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "rectangle":
//                    CurrentShape.setShapeType(ShapeEnum.RECTANGLE);
//                    System.out.println("RECTANGLE");
//                    temporary to test the model, need to make a key binding
                    CurrentShape.setShapeType(ShapeEnum.SQUARE);
                    System.out.println("SQUARE ");
                    isNewShapePainted=true;
                    break;
                case "pencil":
                    CurrentShape.setShapeType(ShapeEnum.PENCIL);
                    System.out.println("PENCIL");
                    isNewShapePainted=true;
                    break;
                case "pen":
                    CurrentShape.setShapeType(ShapeEnum.PEN);  //
                    System.out.println("PEN");
                    isNewShapePainted=true;
                    break;
                case "oval":
//                    CurrentShape.setShapeType(ShapeEnum.OVAL);
//                    System.out.println("OVAL");
//                    temporary to test the model, need to make a key binding
                    CurrentShape.setShapeType(ShapeEnum.CIRCLE);
                    System.out.println("CIRCLE");
                    isNewShapePainted=true;
                    break;
                case "move":
                    System.out.println("MOVE");
                    isNewShapePainted=false;
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
                case "strokeColor":  //REFACTOR needed
                    currentAction = "strokeColor";
                    break;
                case "backgroundColor":
                    currentAction = "backgroundColor";
                    break;
            }
        }
    }


    // This part need REFACTOR but this is one of ways how to deal with problem
    class ColorChangedListener implements ColorChooserButton.ColorChangedListener {

        @Override
        public void colorChanged(Color newColor) {
            switch (currentAction) {
                case "strokeColor":
                    CurrentShape.setStrokeColor(newColor);
                    break;
                case "backgroundColor":
                    CurrentShape.setBackgroundColor(newColor);
                    break;
            }
        }
    }

    class MouseMotionListenerForWorkspace extends MouseMotionAdapter
    {
        @Override
        public void mouseDragged(MouseEvent event)  //to set points in the pencil or to paint the shapes in real time before add them to the model
        {
            listenToDrawingWhenDragged(event);
        }

        private void listenToDrawingWhenDragged(MouseEvent event) {
            if (!(drawShape == null)) {
                if (CurrentShape.getShapeType() == ShapeEnum.PENCIL) {
                    ((Polyline) drawShape).addPoint(new Point(event.getX(), event.getY()));
                } else {
                    if (isShiftKeyPressed) {
                        if (drawShape instanceof Rectangle) {
                            Square square = new Square(drawShape.getX(), drawShape.getY(), drawShape.getX2(), drawShape.getY2(), drawShape.getBackgroundColor(), drawShape.getStrokeColor(), drawShape.getStrokeThickness());
                            drawShape = square;
                        } else if (drawShape instanceof Oval) {
                            Circle circle = new Circle(drawShape.getX(), drawShape.getY(), drawShape.getX2(), drawShape.getY2(), drawShape.getBackgroundColor(), drawShape.getStrokeColor(), drawShape.getStrokeThickness());
                            drawShape = circle;
                        }
                    } else {
                        if (drawShape instanceof Square) {
                            Rectangle rectangle = new Rectangle(drawShape.getX(), drawShape.getY(), drawShape.getX(), drawShape.getY2(), drawShape.getBackgroundColor(), drawShape.getStrokeColor(), drawShape.getStrokeThickness());
                            drawShape = rectangle;
                        } else if (drawShape instanceof Circle) {
                            Oval oval = new Oval(drawShape.getX(), drawShape.getY(), drawShape.getX2(), drawShape.getY2(), drawShape.getBackgroundColor(), drawShape.getStrokeColor(), drawShape.getStrokeThickness());
                            drawShape = oval;
                        }
                    }
                    drawShape.setX2(event.getX());
                    drawShape.setY2(event.getY());
                }
                view.getWorkspaceComponent().setTmpShape(drawShape);
                view.getWorkspaceComponent().repaint(); // draw the shape during user's action
            }

        }


    }

    class MouseListenerForWorkspace extends MouseAdapter
    {
        private void listenToDrawingShapeWhenPressed(MouseEvent e) {
            if (isNewShapePainted) //flag which check if there is a new shape (helpful with pen)
            {
                drawShape = getTmpShape(e.getX(), e.getY(), e.getX(), e.getY());
                view.getWorkspaceComponent().setTmpShape(drawShape);
                view.getWorkspaceComponent().repaint();
                isNewShapePainted = false; //not necesary
            } else {
                selectedShape = model.getWorkspace().findDrawnShapesId(e.getPoint());
                System.out.println(model.getWorkspace().findDrawnShapesId(e.getPoint()));
            }
        }

        private void listenToDrawingShapeWhenReleased(MouseEvent e) {
            if (!(drawShape == null)) {
                ArrayList<ShapeObject> shapes = view.getWorkspaceComponent().getShapes();

                if (CurrentShape.getShapeType() != ShapeEnum.PEN) {

                    drawShape.setX2(e.getX());
                    drawShape.setY2(e.getY());


                    shapes.add(drawShape);
                    model.getWorkspace().addShape(drawShape);  //need to set the instance of the shape before
                    view.getWorkspaceComponent().setTmpShape(null);
                    view.getWorkspaceComponent().setShapes(shapes);
                    drawShape = null;
                    isNewShapePainted = true;

                } else  //in the case of pen update the current pen shape in the view and the model
                {
                    //checking if its a new instance of pen
                    if (((Polyline) drawShape).getPoints().size() == 0) {
                        ((Polyline) drawShape).addPoint(new Point(e.getX(), e.getY()));
                    } else //if the pen is actually used need to remove the previous shape and add new one with new points
                    {
                        ShapeObject tempPen = shapes.get(shapes.size() - 1); //get the last Pen object and changed them
                        ((Polyline) tempPen).addPoint(new Point(e.getX(), e.getY()));
                        shapes.remove((shapes.size() - 1));
                        model.getWorkspace().removeShape(shapes.size());
                        drawShape = tempPen;
                    }
                    shapes.add(drawShape);
                    view.getWorkspaceComponent().setShapes(shapes);
                    model.getWorkspace().addShape(drawShape);
                    isNewShapePainted = false;
                }
                view.getWorkspaceComponent().repaint();
            }
        }

        private void listenToShapeSelectWhenClicked(MouseEvent e) {
            int x, y;
            x = e.getX();
            y = e.getY();

            boolean selected = false;
            Point pt = new Point(x, y);
            for (ShapeObject currentShape : model.getWorkspace().getShapes()) {
                if (currentShape.ifPointBelongToField(pt) && !selected) {
                    currentShape.setSelected(true);
                    System.out.println(currentShape);
                    selected = true;
                } else currentShape.setSelected(false);
            }
            view.refresh();
        }

        @Override
        public void mousePressed(MouseEvent e)  //when new shape is created, to set the starting point
        {
            listenToDrawingShapeWhenPressed(e);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            listenToShapeSelectWhenClicked(e);
        }

        public void mouseReleased(MouseEvent e)  //after user's action, it sets the finishing point and add the shape to the model
        {
            listenToDrawingShapeWhenReleased(e);
        }

        private ShapeObject getTmpShape(double x, double y, double x2, double y2)
        {
            switch (CurrentShape.getShapeType())
            {
                case RECTANGLE:
                    return new Rectangle(x, y, x2, y2, CurrentShape.getBackgroundColor(), CurrentShape.getStrokeColor(), CurrentShape.getStrokeThickness());
                case SQUARE:
                    return new Square(x, y, x2, y2, CurrentShape.getBackgroundColor(), CurrentShape.getStrokeColor(), CurrentShape.getStrokeThickness());
                case OVAL:
                    return new Oval(x, y, x2, y2, CurrentShape.getBackgroundColor(), CurrentShape.getStrokeColor(), CurrentShape.getStrokeThickness());
                case CIRCLE:
                    return new Circle(x, y, x2, y2, CurrentShape.getBackgroundColor(), CurrentShape.getStrokeColor(), CurrentShape.getStrokeThickness());
                case PENCIL:
                    return new Polyline(x, y, x2, y2, CurrentShape.getBackgroundColor(), CurrentShape.getStrokeColor(), CurrentShape.getStrokeThickness());
                case PEN:
                    return new Polyline(x, y, x2, y2, CurrentShape.getBackgroundColor(), CurrentShape.getStrokeColor(), CurrentShape.getStrokeThickness());
                default:
                    break;
            }
            return drawShape;
        }
    }

    private class CommandActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "browse":
                    view.getExportComponent().initFileChooser();
                    break;
                case "save":
                    view.getExportComponent().saveFile(view.getExportComponent().getNameField().toString(), view.getWorkspaceComponent());
                    break;
                case "cancel":
                    view.getExportComponent().closeWindow();
                    break;
            }
        }
    }
}
