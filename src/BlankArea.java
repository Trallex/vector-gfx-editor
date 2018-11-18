import org.freehep.graphics2d.VectorGraphics;

import javax.swing.*;
import java.awt.*;

public class BlankArea extends JPanel {

    private Dimension minSize = new Dimension(100, 50);

    public BlankArea(Color color) {
        setBackground(color);
        setOpaque(true);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getMinimumSize() {
        return minSize;
    }

    public Dimension getPreferredSize() {
        return minSize;
    }

    public void paintComponent(Graphics g) {

        if (g == null) return;

        VectorGraphics vg = VectorGraphics.create(g);

        Dimension dim = getSize();
        Insets insets = getInsets();

        vg.setColor(Color.white);
        vg.fillRect(insets.left, insets.top,
                dim.width - insets.left - insets.right,
                dim.height - insets.top - insets.bottom);

        vg.setColor(Color.black);

        vg.setLineWidth(3.0);
        vg.drawLine(10, 10, dim.width - 10, dim.height - 10);

        vg.setColor(Color.ORANGE);
        vg.fillOval(10, 10, 100, 100);
    }
}
