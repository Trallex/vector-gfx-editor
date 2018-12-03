package main.java.vector_editor.view;


import org.freehep.graphicsbase.util.export.ExportDialog;

import javax.swing.*;
import java.awt.*;


public class MainView {
    private JFrame frame;
    private Container container;
    private WorkspaceComponent workspaceComponent;
    private ToolbarComponent toolbarComponent;
    private JScrollPane scrollPane;


    public MainView() {

        frame = new JFrame("Vector TikZ Editor");
        frame.setResizable(false);
        container = frame.getContentPane();
        container.setBackground(Color.decode("#3E3E3E"));
        container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setMinimumSize(new Dimension(1280, 720));
        frame.setMaximumSize(new Dimension((int) dim.getWidth(), (int) dim.getHeight()));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        setupToolbarComponent();
        setupMenuBar();

    }

    private void setupToolbarComponent() {

        toolbarComponent = new ToolbarComponent(100, frame.getMaximumSize().height);
        container.add(toolbarComponent);

        refresh();
    }
    private void setupWorkspaceComponent() {

        workspaceComponent = new WorkspaceComponent(1280, 720);
        scrollPane = new JScrollPane(workspaceComponent, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        if (workspaceComponent.getPreferredSize().width < 1280 && workspaceComponent.getPreferredSize().height < 720) {
            scrollPane.setMaximumSize(new Dimension(workspaceComponent.getWidth(), workspaceComponent.getHeight()));
        } else {
            scrollPane.setMaximumSize(new Dimension(1280, 720));
        }
        scrollPane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        scrollPane.setOpaque(false);

        container.add(scrollPane);

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
                    export.showExportDialog(container, "Export view as...", workspaceComponent, "export");
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
