package vector_editor.view;



import org.freehep.graphicsbase.util.export.ExportDialog;

import javax.swing.*;
import java.awt.*;


public class MainView {
    private JFrame frame;
    private Container container;
    private WorkspaceComponent workspaceComponent;
    private ToolbarComponent toolbarComponent;
    private JScrollPane scrollPane;

    private int workspaceWidth, workspaceHeight; // It goes to WorkspaceModel
    private String workspaceName; // It goes to WorkspaceModel


    public MainView() {

        frame = new JFrame("Vector TikZ Editor");
        container = frame.getContentPane();
        container.setBackground(Color.decode("#3E3E3E"));
        container.setLayout(new BorderLayout());


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

        toolbarComponent = new ToolbarComponent(150, frame.getMaximumSize().height);
        container.add(toolbarComponent, BorderLayout.LINE_START);

        refresh();
    }

    private void setupWorkspaceComponent(int workspaceWidth, int workspaceHeight, String workspaceName) {

        if (scrollPane != null) container.remove(scrollPane);

        workspaceComponent = new WorkspaceComponent(workspaceWidth, workspaceHeight, workspaceName);
        scrollPane = new JScrollPane(workspaceComponent, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        if (workspaceComponent.getPreferredSize().width < 1280 && workspaceComponent.getPreferredSize().height < 720) {
            scrollPane.setMaximumSize(new Dimension(workspaceComponent.getWidth(), workspaceComponent.getHeight()));
        } else {
            scrollPane.setMaximumSize(new Dimension(1280, 720));
        }
        scrollPane.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        scrollPane.setOpaque(false);

        container.add(scrollPane, BorderLayout.CENTER);

        refresh();
    }

    private void refresh() {
        frame.validate();
        frame.repaint();
    }

    private void displayNewFilePanel() {
        JTextField widthField = new JTextField("");
        JTextField nameField = new JTextField("");
        JTextField heightField = new JTextField("");
        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Name of your project: "));
        panel.add(nameField);
        panel.add(new JLabel("Width: [px]"));
        panel.add(widthField);
        panel.add(new JLabel("Height: [px]"));
        panel.add(heightField);
        int result = JOptionPane.showConfirmDialog(null, panel, "New File...",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            workspaceWidth = Integer.parseInt(widthField.getText().trim());
            workspaceHeight = Integer.parseInt(heightField.getText().trim());
            workspaceName = nameField.getText().trim();
        } else {
            System.out.println("Cancelled");
        }
    }
    private void setupMenuBar() {
        JMenuBar menuBar = new MenubarComponent();

        ((MenubarComponent) menuBar).getNewFileItem().addActionListener(e -> {
            displayNewFilePanel();
            setupWorkspaceComponent(workspaceWidth, workspaceHeight, workspaceName);
        });
        //Set listeners for saveItem
        ((MenubarComponent) menuBar).getSaveFileItem().addActionListener(
                e -> {
                    ExportDialog export = new ExportDialog();
                    export.showExportDialog(container, "Export view as...", workspaceComponent, "export");
                }
        );

        frame.setJMenuBar(menuBar);

        refresh();



    }
}
