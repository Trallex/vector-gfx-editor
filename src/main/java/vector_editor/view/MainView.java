package main.java.vector_editor.view;

import javax.swing.*;

public class MainView {
    JFrame frame;

    public MainView() {
        frame = new JFrame("Vector TikZ Editor");
        frame.setContentPane(new MainContainer().getContentPanel());
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
