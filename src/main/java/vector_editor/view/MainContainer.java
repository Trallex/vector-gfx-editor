package main.java.vector_editor.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainContainer {

    JPanel getContentPanel() {
        return ContentPanel;
    }

    private JPanel ContentPanel;
    private JPanel ToolbarPanel;


    private JButton MoveToolBtn;
    private JButton PenToolBtn;
    private JButton PencilToolBtn;
    private JButton SquareToolBtn;
    private JButton CircleToolBtn;
    private JButton ZoomToolBtn;
    private JButton TextToolBtn;
    private JButton ImageToolBtn;

    private ArrayList<JButton> buttonList;


    public MainContainer() {
        createUIComponents();

        MoveToolBtn.addActionListener(System.out::println);
    }

    private void createUIComponents() {
        for (Component component : ToolbarPanel.getComponents()) {
            if (component instanceof JButton) {
                ((JButton) component).setOpaque(false);
                ((JButton) component).setContentAreaFilled(false);
                ((JButton) component).setBorderPainted(false);
                ((JButton) component).setFocusPainted(false);
            } else System.out.println("Not type of JButton");
        }
    }
}
