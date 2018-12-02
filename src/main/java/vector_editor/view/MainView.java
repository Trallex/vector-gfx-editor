package main.java.vector_editor.view;


import org.freehep.graphicsbase.util.export.ExportDialog;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private JFrame frame;
    private JPanel workspaceComponent;
    private JPanel contentPanel;
    private MainContainer mainContainer;


    public MainView() {

        mainContainer = new MainContainer();
        contentPanel = mainContainer.getContentPanel();
        frame = new JFrame("Vector TikZ Editor");
        frame.setContentPane(contentPanel);

        contentPanel.setLayout(new FlowLayout(20));


        frame.setSize(1920, 1080);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setupMenuBar();

    }

    private void setupWorkspaceComponent() {
        final WorkspaceComponent workspaceComponent = new WorkspaceComponent(1920, 1080);
        contentPanel.add(workspaceComponent).setLocation(1, 0);
        refresh();
    }

    private void refresh() {
        frame.validate();
        frame.repaint();
    }

    private void setupMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        JMenuItem saveItem = new JMenuItem("Save");

        newItem.addActionListener(e -> {
            setupWorkspaceComponent();
        });
        //Set listeners for saveItem
        saveItem.addActionListener(
                e -> {
                    ExportDialog export = new ExportDialog();
                    export.showExportDialog(contentPanel, "Export view as...", workspaceComponent, "export");
                }
        );

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

        refresh();



    }
}
