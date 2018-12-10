package vector_editor.view;

import javax.swing.*;

public class MenubarComponent extends JMenuBar {

    public JMenu getFileMenu() {
        return fileMenu;
    }

    @Override
    public JMenu getHelpMenu() {
        return helpMenu;
    }

    public JMenuItem getNewFileItem() {
        return newFileItem;
    }

    public JMenuItem getSaveFileItem() {
        return saveFileItem;
    }

    public JMenuItem getCloseItem() {
        return closeItem;
    }

    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem newFileItem;
    private JMenuItem saveFileItem;
    private JMenuItem closeItem;
    private JMenuItem aboutItem;

    public MenubarComponent() {
        fileMenu = new JMenu("File");
        helpMenu = new JMenu("Help");

        newFileItem = new JMenuItem("New file...");
        saveFileItem = new JMenuItem("Save as...");
        closeItem = new JMenuItem("Close...");
        aboutItem = new JMenuItem("About us...");

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(closeItem);

        helpMenu.add(aboutItem);

        this.add(fileMenu);
        this.add(helpMenu);
    }

}
