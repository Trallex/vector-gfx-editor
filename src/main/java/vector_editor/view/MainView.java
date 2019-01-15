package vector_editor.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerListener;

public class MainView {
    private JFrame frame;
    private Container container;
    private WorkspaceComponent workspaceComponent;
    private ToolbarComponent toolbarComponent;
    private ExportComponent exportComponent;
    public ExportComponent getExportComponent() {
        return exportComponent;
    }

    private JScrollPane scrollPane;

    public MenubarComponent getMenuBar() {
        return menuBar;
    }

    private MenubarComponent menuBar;
    private int workspaceWidth, workspaceHeight; // It goes to WorkspaceModel
    private String workspaceName; // It goes to WorkspaceModel

    //to get the view components in the controller
    public WorkspaceComponent getWorkspaceComponent() {
        return workspaceComponent;
    }
    public ToolbarComponent getToolbarComponent() {
        return toolbarComponent;
    }

    public JFrame getJFrame() {
        return frame;
    }

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
        setupExportComponent();
    }
    private void setupToolbarComponent() {

        toolbarComponent = new ToolbarComponent(150, frame.getMaximumSize().height);
        container.add(toolbarComponent, BorderLayout.LINE_START);
        refresh();
    }
    public void setupWorkspaceComponent() {

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

    public void refresh() {
        frame.validate();
        frame.repaint();
    }

    public void displayNewFilePanel() {
        JTextField widthField = new JTextField("1280");
        JTextField nameField = new JTextField("test");
        JTextField heightField = new JTextField("720");
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

            //HERE PASS THE NEW WORKSPACE TO THE CONTROLLER!
            workspaceWidth = Integer.parseInt(widthField.getText().trim());
            workspaceHeight = Integer.parseInt(heightField.getText().trim());
            workspaceName = nameField.getText().trim();
            setupWorkspaceComponent();

        } else {
            System.out.println("Cancelled");
        }
    }
    private void setupMenuBar() {

        menuBar = new MenubarComponent();
        frame.setJMenuBar(menuBar);
        refresh();

    }

    private void setupExportComponent() {
        exportComponent = new ExportComponent();
    }

    public void addListenerToContainer(ContainerListener listener) { //listener to observe if new items were added to the container
        container.addContainerListener(listener);
    }


}

