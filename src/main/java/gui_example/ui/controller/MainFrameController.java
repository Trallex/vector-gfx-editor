package main.java.gui_example.ui.controller;

import main.java.gui_example.ui.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController {

    private MainFrame mainFrame;
    private JButton helloButton;
    private JTextArea helloTA;

    public MainFrameController() {
        initComponent();
        initListeners();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);

    }

    private void initListeners() {
        helloButton.addActionListener(new helloBtnListener());
    }

    private void initComponent() {
        mainFrame = new MainFrame();

        helloButton = mainFrame.getHelloButton();
        helloTA = mainFrame.getHelloTA();
    }


    private class helloBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            helloTA.append("Hello world");
        }
    }
}
