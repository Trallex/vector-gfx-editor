package vector_editor;

import vector_editor.controller.MainFrameController;
import vector_editor.model.Model;
import vector_editor.view.MainView;

public class Runner {
    public static void main(String[] args) {
        MainView view = new MainView();
        Model model = new Model();
        MainFrameController controller = new MainFrameController(view, model);


    }
}