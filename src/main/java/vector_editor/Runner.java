package vector_editor;

import vector_editor.controller.MainFrameController;
import vector_editor.model.Rectangle;
import vector_editor.view.MainView;

import java.awt.*;

public class Runner {
    public static void main(String[] args) {
        MainView view = new MainView();
        Rectangle model = new Rectangle(0,0,0,0,Color.white); //temporary

        MainFrameController controller = new MainFrameController(view, model);


    }
}
