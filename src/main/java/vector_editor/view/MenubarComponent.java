package vector_editor.view;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MenubarComponent extends JMenuBar {

    public JMenu getFileMenu() {
        return fileMenu;
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
    private JMenuItem newFileItem;
    private JMenuItem saveFileItem;
    private JMenuItem closeItem;

    public MenubarComponent() {
        fileMenu = new JMenu("File");

        newFileItem = new JMenuItem("New file...");
        saveFileItem = new JMenuItem("Save as...");
        saveFileItem.setEnabled(false);
        closeItem = new JMenuItem("Close...");

        newFileItem.setActionCommand("new_file");
        saveFileItem.setActionCommand("save_file");
        closeItem.setActionCommand("close");

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);
        fileMenu.add(closeItem);


        this.add(fileMenu);
    }

    public void addCommandActionListener(ActionListener listener) {
        newFileItem.addActionListener(listener);
        saveFileItem.addActionListener(listener);
        closeItem.addActionListener(listener);
    }

}
