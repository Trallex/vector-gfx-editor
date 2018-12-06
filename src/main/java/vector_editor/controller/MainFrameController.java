package vector_editor.controller;

import vector_editor.model.Rectangle;
import vector_editor.model.ShapeObject;
import vector_editor.view.MainView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    }



    class ToolbarComponentListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "move":
                    System.out.println("MOVE");
                    break;
                case "pen":
                    System.out.println("PEN");
                    break;
                case "pencil":
                    System.out.println("PENCIL");
                    break;
                case "square":
                    System.out.println("SQUARE");
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

//        @Override
//        public void actionPerformed(ActionEvent event)
//        {
//            StaticStuff.setShapeType(ShapeEnum.RECTANGLE);
//        }

}
