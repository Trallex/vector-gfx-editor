package main.java.gui_example;

import main.java.gui_example.Model.Rectangle;
import main.java.gui_example.ui.controller.MainFrameController;
import main.java.gui_example.ui.view.MainFrame;

public class Runner {
    public static void main(String[] args) {
        MainFrameController mainFrameController = new MainFrameController( new MainFrame(), new Rectangle());
    }
}
