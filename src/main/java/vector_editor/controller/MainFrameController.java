package vector_editor.controller;

import vector_editor.model.CurrentShape;
import vector_editor.model.Model;
import vector_editor.model.Shapes.Rectangle;
import vector_editor.model.Shapes.*;
import vector_editor.model.ToolEnum;
import vector_editor.model.Workspace;
import vector_editor.view.ColorChooserButton;
import vector_editor.view.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class MainFrameController {
    private int selectedShape =-1; //index of shape in model
    private Point draggingPoint= null;
    private MainView view;
    private Model model; //temporary!!
    private InputMap inputMap; //key binding
    private ActionMap actionMap; //KB
    private ShapeObject drawShape;
    private boolean isNewShapePainted; //helpful flag while using the pen
    private boolean isShiftKeyPressed = false;

    private String currentAction; // REFACTOR needed, used it in Listeners to change BG Color

    private Workspace newWorkspaceState;

    public MainFrameController(MainView view, Model model) //temporary model..
    {
        this.view = view;
        this.model = model;

        initListenersToToolbar();
        initListenersToContainer();
        initListenersToMenuBar();

        //creating key binders
        initInputMap();
        initActionMap();

    }

    private void initListenersToToolbar() {
        this.view.getToolbarComponent().addToolbarComponentListener(new ToolbarComponentListener());
        this.view.getToolbarComponent().getStrokeColorBtn().addColorChangedListener(new ColorChangedListener());
        this.view.getToolbarComponent().getBackgroundColorBtn().addColorChangedListener(new ColorChangedListener());
        this.view.getToolbarComponent().getStrokeThicknessComboBox().addItemListener(new StrokeChangeListener());
    }

    private void initListenersToContainer() {
        this.view.addListenerToContainer(new ContainerListenerForMainFrame());

    }

    private void initListenersToMenuBar() {
        this.view.getExportComponent().addCommandActionListener(new ExportCommandActionListener());
        this.view.getMenuBar().addCommandActionListener(new MenuCommandActionListener());
    }

    private void initInputMap() {
        inputMap = view.getJFrame().getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, InputEvent.SHIFT_DOWN_MASK, false), "shiftPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_SHIFT, 0, true), "shiftReleased");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK, false), "ctrlZPressed");
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0, false), "deleteSelectedShape");
    }

    private void initActionMap() {
        actionMap = view.getJFrame().getRootPane().getActionMap();

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
                setWorkspaceToPreviousState();
            }
        });
        actionMap.put("deleteSelectedShape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedShape();
            }

        });

    }


    private void setWorkspaceToPreviousState() {
        isNewShapePainted = false;
        drawShape = null;
        model.setWorkspaceToPreviousState();
        view.getWorkspaceComponent().setShapes(model.getWorkspace().getShapes());
        view.getWorkspaceComponent().repaint();
    }

    private void deleteSelectedShape() {
        if (CurrentShape.getShapeType() == ToolEnum.SELECT && selectedShape != -1) {
            {
                model.saveCurrentWorkspaceToHistory();
                newWorkspaceState = new Workspace(model.getWorkspace());
                newWorkspaceState.removeShape(selectedShape);
                model.setWorkspace(newWorkspaceState);
                view.getWorkspaceComponent().setShapes(model.getWorkspace().getShapes());
                view.getWorkspaceComponent().repaint();
            }
        }
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
            setNewWorkspace();
        }
    }

    private void setNewWorkspace() {
        initListenersToWorkspace();
        model.cleanWorkspaceHistory();
        int width = view.getWorkspaceComponent().getWidth();
        int height = view.getWorkspaceComponent().getHeight();
        String name = view.getWorkspaceComponent().getName();
        model.setWorkspace(new Workspace(width, height, name));
    }

    private void initListenersToWorkspace() {
        view.getWorkspaceComponent().addWorkspaceComponentMouseListener(new MouseListenerForWorkspace()); //need to set listeners
        view.getWorkspaceComponent().addWorkspaceComponentMouseMotionListener(new MouseMotionListenerForWorkspace()); //after create the new workspace
    }


    class ToolbarComponentListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "rectangle":
                    CurrentShape.setShapeType(ToolEnum.SQUARE);
                    isNewShapePainted=true;
                    break;
                case "pencil":
                    CurrentShape.setShapeType(ToolEnum.PENCIL);
                    isNewShapePainted=true;
                    break;
                case "pen":
                    CurrentShape.setShapeType(ToolEnum.PEN);  //
                    isNewShapePainted=true;
                    break;
                case "oval":
                    CurrentShape.setShapeType(ToolEnum.CIRCLE);
                    isNewShapePainted=true;
                    break;
                case "move":
                    CurrentShape.setShapeType(ToolEnum.SELECT);
                    isNewShapePainted=false;
                    break;
                case "strokeColor":  //REFACTOR needed
                    CurrentShape.setShapeType(ToolEnum.STROKE_COLOR);
                    break;
                case "backgroundColor":
                    CurrentShape.setShapeType(ToolEnum.BACKGROUND_COLOR);
                    break;
            }
        }
    }

    private ShapeObject getTmpShape(double x, double y, double x2, double y2) {
        switch (CurrentShape.getShapeType()) {
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

    // This part need REFACTOR but this is one of ways how to deal with problem
    class ColorChangedListener implements ColorChooserButton.ColorChangedListener {

        @Override
        public void colorChanged(Color newColor) {
            switch (CurrentShape.getShapeType()) {
                case STROKE_COLOR:
                    CurrentShape.setStrokeColor(newColor);
                    break;
                case BACKGROUND_COLOR:
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
                if (CurrentShape.getShapeType() == ToolEnum.PENCIL) {
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
            } else if (selectedShape != -1 && CurrentShape.getShapeType() == ToolEnum.SELECT) {
                double xDifference = event.getX() - draggingPoint.getX();
                double yDiference = event.getY() - draggingPoint.getY();

                draggingPoint.setLocation(event.getPoint());

                view.getWorkspaceComponent().updateShapePlace(selectedShape, xDifference, yDiference);
                view.getWorkspaceComponent().repaint();
            }

        }


    }

    class MouseListenerForWorkspace extends MouseAdapter
    {

        @Override
        public void mousePressed(MouseEvent e)  //when new shape is created, to set the starting point
        {
            listenToDrawingShapeWhenPressed(e);
        }

        private void listenToDrawingShapeWhenPressed(MouseEvent e) {
            //when new shape is created, to set the starting point

            draggingPoint = e.getPoint();
            if (isNewShapePainted) //flag which check if there is a new shape (helpful with pen)
            {
                drawShape = getTmpShape(e.getX(), e.getY(), e.getX(), e.getY());
                view.getWorkspaceComponent().setTmpShape(drawShape);
                view.getWorkspaceComponent().repaint();
                isNewShapePainted = false;
            } else if (CurrentShape.getShapeType() == ToolEnum.SELECT) {
                selectedShape = model.getWorkspace().findDrawnShapesId(e.getPoint());
                //System.out.println(model.getWorkspace().findDrawnShapesId(e.getPoint()));
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (CurrentShape.getShapeType() == ToolEnum.SELECT) {
                listenToShapeSelectWhenClicked(e);
            }
        }

        private void listenToShapeSelectWhenClicked(MouseEvent e) {
            int x, y;
            x = e.getX();
            y = e.getY();

            System.out.println("selected");
            boolean selected = false;
            Point pt = new Point(x, y);
            for (ShapeObject currentShape : model.getWorkspace().getShapes()) {
                if (currentShape.ifPointBelongToField(pt) && !selected) {
                    currentShape.setSelected(true);
                    //System.out.println(currentShape);
                    selected = true;
                } else currentShape.setSelected(false);
            }
            view.refresh();
        }

        public void mouseReleased(MouseEvent e)  //after user's action, it sets the finishing point and add the shape to the model
        {
            listenToDrawingShapeWhenReleased(e);
        }

        private void addNewShapeToWorkspace() {
            model.saveCurrentWorkspaceToHistory();
            newWorkspaceState = new Workspace(model.getWorkspace());
            newWorkspaceState.addShape(drawShape);
            model.setWorkspace(newWorkspaceState);

            view.getWorkspaceComponent().setTmpShape(null);
            view.getWorkspaceComponent().setShapes(model.getWorkspace().getShapes());
            drawShape = null;
            isNewShapePainted = true;
        }

        private void listenToDrawingShapeWhenReleased(MouseEvent e) {
            if (!(drawShape == null))
            {

                if (CurrentShape.getShapeType() != ToolEnum.PEN) {

                    drawShape.setX2(e.getX());
                    drawShape.setY2(e.getY());
                    addNewShapeToWorkspace();
 

                } else  //in the case of pen update the current pen shape in the view and the model
                {

                    model.saveCurrentWorkspaceToHistory(); //save the state of the previous one
                    view.getWorkspaceComponent().setTmpShape(null);
                    newWorkspaceState = new Workspace(model.getWorkspace());
                    ArrayList<ShapeObject> shapes = view.getWorkspaceComponent().getShapes();
                    //checking if its a new instance of pen
                    if (((Polyline) drawShape).getPoints().size() == 0) {
                        ((Polyline) drawShape).addPoint(new Point(e.getX(), e.getY()));
                        newWorkspaceState.addShape(new Polyline((Polyline) drawShape));
                        model.setWorkspace(newWorkspaceState);

                    } else //if the pen is actually used need to remove the previous shape and add new one with new points
                    {
                        ShapeObject tempPen = new Polyline((Polyline) shapes.get(shapes.size() - 1)); //get the last Pen object and changed them
                        ((Polyline) tempPen).addPoint(new Point(e.getX(), e.getY()));
                        newWorkspaceState.removeLastShape();
                        drawShape = tempPen;
                        newWorkspaceState.addShape(new Polyline((Polyline) drawShape));
                        model.setWorkspace(newWorkspaceState);

                    }
                    view.getWorkspaceComponent().setShapes(model.getWorkspace().getShapes());
                    isNewShapePainted = false;
                }
                view.getWorkspaceComponent().repaint();
            } else if (CurrentShape.getShapeType() == ToolEnum.SELECT && selectedShape != -1) {
                double xDifference = e.getX() - draggingPoint.getX();
                double yDiference = e.getY() - draggingPoint.getY();

                model.saveCurrentWorkspaceToHistory();
                newWorkspaceState = new Workspace(model.getWorkspace());
                newWorkspaceState.getShapes().get(selectedShape).updateShapePlace(xDifference, yDiference);
                model.setWorkspace(newWorkspaceState);
                view.getWorkspaceComponent().setTmpShape(null);
                view.getWorkspaceComponent().setShapes(model.getWorkspace().getShapes()); //because of that problem with ctr+z
                //after moving, view changes the model..
            }
            draggingPoint = null;
        }

    }

    private class MenuCommandActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "new_file":
                    view.displayNewFilePanel();
                    view.setupWorkspaceComponent();
                    break;
                case "save_file":
                    view.getExportComponent().display();
                    break;
                case "close":
                    break;
                case "about":
                    break;
            }
        }
    }

    private class ExportCommandActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "browse":
                    view.getExportComponent().initFileChooser();
                    break;
                case "save":
                    try {
                        view.getExportComponent().saveFile(view.getWorkspaceComponent());
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "cancel":
                    view.getExportComponent().closeWindow();
                    break;
            }
        }
    }


}
