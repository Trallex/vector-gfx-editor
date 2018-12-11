package vector_editor.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class ToolbarComponent extends JPanel {

    private JButton PenToolBtn;
    private JButton PencilToolBtn;
    private JButton SquareToolBtn;
    private JButton CircleToolBtn;

    public ColorChooserButton getBackgroundColorBtn() {
        return BackgroundColorBtn;
    }

    public ColorChooserButton getStrokeColorBtn() {
        return StrokeColorBtn;
    }

    private ColorChooserButton BackgroundColorBtn;
    private ColorChooserButton StrokeColorBtn;


    private static final int MAX_THICKNESS = 15;
    private static final int MIN_THICKNESS = 0;

    public JComboBox<Integer> getStrokeThicknessComboBox() {
        return StrokeThicknessComboBox;
    }

    private JComboBox<Integer> StrokeThicknessComboBox;

    private ArrayList<JButton> buttons;

    public ToolbarComponent(int width, int height) {

        this.setLayout(new GridLayout(0, 1));
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.decode("#2E2E2E"));
        setupComponents();

    }

    private void setupComponents() {
        buttons = new ArrayList<>();

        PenToolBtn = new JButton();
        PencilToolBtn = new JButton();
        SquareToolBtn = new JButton();
        CircleToolBtn = new JButton();

        BackgroundColorBtn = new ColorChooserButton(Color.white);
        StrokeColorBtn = new ColorChooserButton(Color.black);

        Integer[] values = new Integer[MAX_THICKNESS + 1];
        for (int i = MIN_THICKNESS; i <= MAX_THICKNESS; i++) {
            values[i] = (Integer) i;
        }
        StrokeThicknessComboBox = new JComboBox<>(values);

        BackgroundColorBtn.setText("Background:");
        StrokeColorBtn.setText("Stroke:");

        buttons.add(PenToolBtn);
        buttons.add(PencilToolBtn);
        buttons.add(SquareToolBtn);
        buttons.add(CircleToolBtn);

        buttons.add(StrokeColorBtn);
        buttons.add(BackgroundColorBtn);


        PenToolBtn.setActionCommand("pen");
        PencilToolBtn.setActionCommand("pencil");
        SquareToolBtn.setActionCommand("rectangle");
        CircleToolBtn.setActionCommand("oval");
        StrokeColorBtn.setActionCommand("strokeColor");
        BackgroundColorBtn.setActionCommand("backgroundColor");
        StrokeThicknessComboBox.setActionCommand("strokeThickness");


        buttons.forEach(jButton -> {
            if (!(jButton instanceof ColorChooserButton)) {
                Icon icon = createImageIcon(String.format("/images/%s.gif", jButton.getActionCommand()));
                jButton.setIcon(icon);
            }

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


        this.add(StrokeThicknessComboBox);
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
