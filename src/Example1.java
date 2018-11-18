import org.freehep.graphicsbase.util.export.ExportDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Example1 extends JPanel implements MouseListener {

    private BlankArea blankArea;

    public Example1() {
        // Tworzenie pustej przestrzeni Graficznej
        super(new GridLayout(0, 1));
        blankArea = new BlankArea(Color.YELLOW);
        add(blankArea);
        // Nasłuchiwanie obsługi myszki
        blankArea.addMouseListener(this);
        addMouseListener(this);
        setPreferredSize(new Dimension(450, 450));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    private static void createAndShowGUI() {
        // Tworzenie okna SWING i ustawianie operacji zamknięcia
        JFrame frame = new JFrame("Example1");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tworzenie panelu z zawartością
        final Example1 panel = new Example1();
        panel.setOpaque(true);
        frame.getContentPane().add(panel);

        // Tworzenie menu odpowiedzialnego za zapis plików
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu file = new JMenu("Plik");
        menuBar.add(file);

        JMenuItem exportItem = new JMenuItem("Zapisz");
        exportItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExportDialog export = new ExportDialog();
                export.showExportDialog(panel, "Zapisz jako...", panel, "export");
            }
        });
        file.add(exportItem);

        JMenuItem quitItem = new JMenuItem("Wyjdź");
        quitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        file.add(quitItem);

        // Wyświetlenie okna
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }


    public void mousePressed(MouseEvent e) {
        System.out.println(String.format("Mouse pressed (# of clicks: %d)", e.getClickCount()));
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println(String.format("Mouse released (# of clicks: %d)", e.getClickCount()));
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("Mouse entered");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("Mouse exited");
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println(String.format("Mouse clicked (# of clicks: %d)", e.getClickCount()));
    }
    public void mouseMoved(MouseEvent e) {
        System.out.println(saySomething("Mouse moved", e));
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println(saySomething("Mouse dragged", e));
    }

    String saySomething(String eventDescription, MouseEvent e) {
        return (eventDescription  + " (" + e.getX() + "," + e.getY() + ")" + " detected on " + e.getComponent().getClass().getName());
    }
}

