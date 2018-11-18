package main.java.gui_example.ui.view;

import javax.swing.*;


public class MainFrame extends JFrame  {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;


    public JPanel getDrawPanel() {return drawPanel;}


    private JPanel drawPanel;

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(drawPanel);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {

        drawPanel= new JPanel();
        // TODO: place custom component creation code here
    }
}
