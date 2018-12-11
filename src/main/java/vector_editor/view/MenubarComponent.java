package vector_editor.view;

import javax.swing.*;

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


    private JMenu fileMenu;
    private JMenuItem newFileItem;
    private JMenuItem saveFileItem;

    public MenubarComponent() {
        fileMenu = new JMenu("File");

        newFileItem = new JMenuItem("New file...");
        saveFileItem = new JMenuItem("Save as...");

        fileMenu.add(newFileItem);
        fileMenu.add(saveFileItem);

        this.add(fileMenu);
    }

}
