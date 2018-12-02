package main.java.vector_editor.view;


import javax.swing.*;

public class MainView {
    private JFrame frame;


    public MainView() {
        frame = new JFrame("Vector TikZ Editor");
        frame.setContentPane(new MainContainer().getContentPanel());
        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setupMenuBar();
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem closeItem = new JMenuItem("Close");

        //Add items to fileMenu

        fileMenu.add(newItem);
        fileMenu.add(saveItem);
        fileMenu.add(closeItem);

        JMenu helpMenu = new JMenu("Help");

        JMenuItem aboutItem = new JMenu("About");
        //Add items to helpMenu
        helpMenu.add(aboutItem);
        //Add menus to menuBar
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        frame.setJMenuBar(menuBar);

        frame.validate();
        frame.repaint();

    }
}
