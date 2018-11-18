package main.java.gui_example.ui.view;

import javax.swing.*;

public class MainFrame extends JFrame {

    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    public JButton getHelloButton() {
        return helloButton;
    }

    public JTextArea getHelloTA() {
        return helloTA;
    }

    private JButton helloButton;
    private JTextArea helloTA;
    private JPanel mainPanel;

    public MainFrame() {
        setSize(WIDTH, HEIGHT);
        setContentPane(mainPanel);
        setLocationRelativeTo(null);
    }
}
