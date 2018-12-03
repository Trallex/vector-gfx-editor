package main.java.vector_editor.view;

import javax.swing.*;
import java.awt.*;
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

    private ArrayList<JButton> buttons;

    public ToolbarComponent(int width, int height) {


        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#2E2E2E"));
        setupButtons();

    }

    private void setupButtons() {
        buttons = new ArrayList<>();

        MoveToolBtn = new JButton();
        PenToolBtn = new JButton();
        PencilToolBtn = new JButton();
        SquareToolBtn = new JButton();
        CircleToolBtn = new JButton();
        ZoomToolBtn = new JButton();
        TextToolBtn = new JButton();
        BitmapToolBtn = new JButton();

        buttons.add(MoveToolBtn);
        buttons.add(PenToolBtn);
        buttons.add(PencilToolBtn);
        buttons.add(SquareToolBtn);
        buttons.add(CircleToolBtn);
        buttons.add(ZoomToolBtn);
        buttons.add(TextToolBtn);
        buttons.add(BitmapToolBtn);

        MoveToolBtn.setActionCommand("move");
        PenToolBtn.setActionCommand("pen");
        PencilToolBtn.setActionCommand("pencil");
        SquareToolBtn.setActionCommand("square");
        CircleToolBtn.setActionCommand("circle");
        ZoomToolBtn.setActionCommand("zoom");
        TextToolBtn.setActionCommand("text");
        BitmapToolBtn.setActionCommand("bitmap");


        buttons.forEach(jButton -> {
            Icon icon = createImageIcon(String.format("/resources/images/%s.gif", jButton.getActionCommand()));
            jButton.setIcon(icon);
            jButton.setPreferredSize(new Dimension(getWidth(), 60));
            this.add(jButton);
            jButton.setOpaque(false);
            jButton.setFocusPainted(false);
            jButton.setBorderPainted(false);
            jButton.setContentAreaFilled(false);

        });
    }

    private ImageIcon createImageIcon(String path) {
        URL imgUrl = getClass().getResource(path);
        try {
            return new ImageIcon(imgUrl);
        } catch (Exception ex) {
            System.err.println("Couldn't find file" + path);
        }
        return null;
    }
}
