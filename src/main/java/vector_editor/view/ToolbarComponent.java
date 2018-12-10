package vector_editor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class ToolbarComponent extends JPanel {

    private JButton MoveToolBtn;
    private JButton PenToolBtn;
    private JButton PencilToolBtn;
    private JButton SquareToolBtn;
    private JButton CircleToolBtn;
    private JButton ZoomToolBtn;
    private JButton TextToolBtn;
    private JButton BitmapToolBtn;
    private ColorChooserButton ColorBackgroundBtn;
    private ColorChooserButton ColorBorderBtn;

    private ArrayList<JButton> buttons;

    public ToolbarComponent(int width, int height) {

        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#2E2E2E"));
        setupComponents();


    }

    private void setupComponents() {
        buttons = new ArrayList<>();

        MoveToolBtn = new JButton();
        PenToolBtn = new JButton();
        PencilToolBtn = new JButton();
        SquareToolBtn = new JButton();
        CircleToolBtn = new JButton();
        ZoomToolBtn = new JButton();
        TextToolBtn = new JButton();
        BitmapToolBtn = new JButton();
        ColorBackgroundBtn = new ColorChooserButton(Color.white);
        ColorBorderBtn = new ColorChooserButton(Color.black);
        ColorBackgroundBtn.setText("Background:");
        ColorBorderBtn.setText("Border:");

        buttons.add(MoveToolBtn);
        buttons.add(PenToolBtn);
        buttons.add(PencilToolBtn);
        buttons.add(SquareToolBtn);
        buttons.add(CircleToolBtn);
        buttons.add(ZoomToolBtn);
        buttons.add(TextToolBtn);
        buttons.add(BitmapToolBtn);
        buttons.add(ColorBorderBtn);
        buttons.add(ColorBackgroundBtn);


        MoveToolBtn.setActionCommand("move");
        PenToolBtn.setActionCommand("pen");
        PencilToolBtn.setActionCommand("pencil");
        SquareToolBtn.setActionCommand("rectangle");
        CircleToolBtn.setActionCommand("oval");
        ZoomToolBtn.setActionCommand("zoom");
        TextToolBtn.setActionCommand("text");
        BitmapToolBtn.setActionCommand("bitmap");
        ColorBorderBtn.setActionCommand("borderColor");
        ColorBackgroundBtn.setActionCommand("backgroundColor");



        buttons.forEach(jButton -> {
            if (!(jButton instanceof ColorChooserButton)) {
                Icon icon = createImageIcon(String.format("/images/%s.gif", jButton.getActionCommand()));
                jButton.setIcon(icon);
            }
            jButton.setPreferredSize(new Dimension(getWidth(), 60));
            this.add(jButton);
            jButton.setOpaque(false);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setContentAreaFilled(false);
            jButton.setForeground(Color.white);
            jButton.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
            jButton.setHorizontalAlignment(SwingConstants.CENTER);
            jButton.setHorizontalTextPosition(SwingConstants.LEFT);


        });
    }

    private ImageIcon createImageIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        try {
            return new ImageIcon(imgUrl);
        } catch (Exception ex) {
            System.err.println("Couldn't find file " + path);
        }
        return null;
    }
//add a listener in the controller to the toolbar buttons
    public void addToolbarComponentListener(ActionListener toolbarComponentListener) {
        buttons.forEach(jButton -> jButton.addActionListener(toolbarComponentListener));
    }
}
