package vector_editor.view;

import org.freehep.graphics2d.VectorGraphics;
import org.freehep.graphicsio.ps.EPSGraphics2D;
import org.freehep.graphicsio.svg.SVGGraphics2D;
import vector_editor.TikZGraphics2D;
import vector_editor.helpers.ScreenImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Objects;

public class ExportComponent extends JDialog {

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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new java.io.File("."));
        fileChooser.setDialogTitle("Choose directory to save file...");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setCurrentDirectory(FileSystemView.getFileSystemView().getHomeDirectory());
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

        File out = new File(String.format("%s/%s%s", pathField.getText(), nameField.getText(), format));
        BufferedImage bImg = ScreenImage.createImage(component);


        switch (format) {
            case ".jpg":
                Graphics2D gJPG = bImg.createGraphics();
                component.printAll(gJPG);
                try {
                    if (ImageIO.write(bImg, "jpg", out)) {
                        showSuccess();
                        gJPG.dispose();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case ".png":
                Graphics2D gPNG = bImg.createGraphics();
                component.print(gPNG);
                try {
                    if (ImageIO.write(bImg, "png", out)) {
                        showSuccess();
                        gPNG.dispose();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case ".gif":
                Graphics2D gGIF = bImg.createGraphics();
                component.print(gGIF);
                try {
                    if (ImageIO.write(bImg, "gif", out)) {
                        showSuccess();
                        gGIF.dispose();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;
            case ".svg":
                VectorGraphics vgSVG = new SVGGraphics2D(out, component);
                vgSVG.startExport();
                component.print(vgSVG);
                vgSVG.endExport();
                showSuccess();
                break;
            case ".eps":
                VectorGraphics vgEPS = new EPSGraphics2D(out, component);
                vgEPS.startExport();
                component.print(vgEPS);
                vgEPS.endExport();
                showSuccess();
                break;
            case ".tex":
                OutputStream os = new FileOutputStream(out);
                Thread thread = new Thread(() -> {
                    TikZGraphics2D t = new TikZGraphics2D(os);
                    t.paintComponent(component);
                });
                thread.start();
                showSuccess();
                break;
        }

    }

    public void showError(String error) {
        JPanel errorPanel = new JPanel(new GridLayout(0, 1));
        JLabel errorText = new JLabel(error);
        errorPanel.add(errorText);

        JOptionPane.showConfirmDialog(null, errorPanel, "Error occured...", JOptionPane.DEFAULT_OPTION);

    }

    private void showSuccess() {

    }
}

