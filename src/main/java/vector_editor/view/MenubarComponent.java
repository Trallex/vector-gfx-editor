package vector_editor.view;

import javax.swing.*;
import java.awt.event.ActionListener;

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
        saveFileItem.setEnabled(false);
        closeItem = new JMenuItem("Close...");
        aboutItem = new JMenuItem("About us...");

        newFileItem.setActionCommand("new_file");
        saveFileItem.setActionCommand("save_file");
        closeItem.setActionCommand("close");
        aboutItem.setActionCommand("about");

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(closeItem);

        helpMenu.add(aboutItem);

        this.add(fileMenu);
        this.add(helpMenu);
    }

    public void addCommandActionListener(ActionListener listener) {
        newFileItem.addActionListener(listener);
        saveFileItem.addActionListener(listener);
        closeItem.addActionListener(listener);
        aboutItem.addActionListener(listener);
    }

}
