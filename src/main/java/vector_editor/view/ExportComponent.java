package vector_editor.view;

import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsbase.swing.Headless;
import org.freehep.graphicsio.pdf.PDFGraphics2D;
import org.freehep.graphicsio.ps.EPSGraphics2D;
import org.freehep.graphicsio.svg.SVGGraphics2D;
import vector_editor.TikZGraphics2D;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ExportComponent extends JDialog {
    private JFileChooser fileChooser;

    public JTextField getPathField() {
        return pathField;
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;
    private JTextField pathField;
    private JTextField nameField;
    private JLabel nameLabel;
    private JLabel formatLabel;
    private JLabel pathLabel;
    private JComboBox<String> formatsCombobox;
    private JButton saveButton;
    private JButton browsePathButton;
    private JButton cancelButton;
    private File pathToDirectory;

    ExportComponent(String fileName) {
        initComponentItems();
        this.setSize(600, 300);
        this.setResizable(false);
        this.fileName = fileName;
        this.setTitle("Save file as...");

        showComponentItems();

        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(false);

    }

    public void display() {
        this.nameField.setText(fileName);
        this.setVisible(true);
    }

    public void addCommandActionListener(ActionListener cmdActionListener) {
        browsePathButton.addActionListener(cmdActionListener);
        saveButton.addActionListener(cmdActionListener);
        cancelButton.addActionListener(cmdActionListener);
    }

    public void initFileChooser() {
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Choose directory to save file...");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            pathToDirectory = fileChooser.getCurrentDirectory();
            pathToDirectory = fileChooser.getSelectedFile();
            pathField.setText(pathToDirectory.toString());
        } else {
            System.out.println("No Selection ");
        }
    }

    private void initComponentItems() {


        nameLabel = new JLabel("Name: ");
        formatLabel = new JLabel("Format: ");
        pathLabel = new JLabel("Path: ");

        nameField = new JTextField();
        pathField = new JTextField();

        formatsCombobox = new JComboBox<>();


        saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        cancelButton = new JButton("Cancel");
        cancelButton.setActionCommand("cancel");
        browsePathButton = new JButton("Browse");
        browsePathButton.setActionCommand("browse");
        populateCombobox();
    }

    private void populateCombobox() {
        ArrayList<String> possibleFormats;
        possibleFormats = new ArrayList<>();
        possibleFormats.add(".jpg");
        possibleFormats.add(".png");
        possibleFormats.add(".gif");
        possibleFormats.add(".svg");
        possibleFormats.add(".eps");
        possibleFormats.add(".pdf");
        possibleFormats.add(".tex");
        for (String pf : possibleFormats
        ) {
            formatsCombobox.addItem(pf);
        }
    }

    private void showComponentItems() {

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(nameLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        this.add(nameField, c);
        c.fill = GridBagConstraints.CENTER;
        c.gridwidth = 1;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        this.add(pathLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.6;
        c.gridx = 1;
        c.gridwidth = 1;
        c.gridy = 1;
        this.add(pathField, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.2;
        c.gridx = 2;
        c.gridy = 1;
        this.add(browsePathButton, c);
        c.fill = GridBagConstraints.CENTER;
        c.weightx = 0.1;
        c.gridx = 0;
        c.gridy = 2;
        this.add(formatLabel, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.8;
        c.gridwidth = 2;
        c.gridx = 1;
        c.gridy = 2;
        this.add(formatsCombobox, c);
        c.ipady = 20;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0.5;
        c.gridwidth = 1;
        this.add(saveButton, c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 4;
        c.weightx = 0.5;
        c.gridwidth = 1;
        this.add(cancelButton, c);

        this.setVisible(true);
    }

    public void closeWindow() {
        this.setVisible(false);
        dispose();
    }

    public void saveFile(JComponent component) throws IOException {
        String format = Objects.requireNonNull(formatsCombobox.getSelectedItem()).toString();

        Headless headless = new Headless(component);
        headless.pack();
        headless.setVisible(true);

        File out = new File(String.format("%s/%s%s", pathField.getText(), nameField.getText(), format));
        switch (format) {
            case ".jpg":
                break;
            case ".png":
                break;
            case ".gif":
                break;
            case ".svg":
                VectorGraphics vgSVG = new SVGGraphics2D(out, component);
                vgSVG.startExport();
                component.print(vgSVG);
                vgSVG.endExport();
                break;
            case ".eps":
                VectorGraphics vgEPS = new EPSGraphics2D(out, component);
                vgEPS.startExport();
                component.print(vgEPS);
                vgEPS.endExport();
                break;
            case ".pdf":
                VectorGraphics vgPDF = new PDFGraphics2D(out, headless);
                vgPDF.startExport();
                headless.print(vgPDF);
                vgPDF.endExport();
                break;
            case ".tex":
                OutputStream os = new FileOutputStream(out);
                Thread thread = new Thread(() -> {
                    TikZGraphics2D t = new TikZGraphics2D(os);
                    t.paintComponent(component);
                });
                thread.start();
                break;
        }

    }
}
