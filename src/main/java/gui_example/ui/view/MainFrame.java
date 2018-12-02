package main.java.gui_example.ui.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Base64;


public class MainFrame extends JFrame  {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;


    public BlankArea getDrawPanel() {return (BlankArea)drawPanel;}

    private JPanel drawPanel;

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setContentPane(drawPanel);

    }

    private void createUIComponents() {
        drawPanel= new BlankArea(Color.YELLOW);

    }


}
