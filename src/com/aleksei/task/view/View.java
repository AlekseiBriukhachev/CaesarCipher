package com.aleksei.task.view;

import com.aleksei.task.Controller;
import com.aleksei.task.ExceptionHandler;
import com.aleksei.task.listeners.*;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane textPane = new JTextPane();
    private JTextPane encryptedTextPane = new JTextPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }
    }

    public void init() {
        initGui();
        addWindowListener(new FrameListener(this));
        setVisible(true);
    }

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "New":
                controller.createNewDocument();
                break;
            case "Open":
                controller.openDocument();
                break;
            case "Save":
                controller.saveDocument();
                break;
            case "Save as...":
                controller.saveDocumentAs();
                break;
            case "Exit":
                controller.exit();
                break;
            case "About":
                showAbout();
        }

    }

    public void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        MenuHelper.initFileMenu(this, menuBar);
        MenuHelper.initEditMenu(this, menuBar);
        MenuHelper.initStyleMenu(this, menuBar);
        MenuHelper.initAlignMenu(this, menuBar);
        MenuHelper.initColorMenu(this, menuBar);
        MenuHelper.initFontMenu(this, menuBar);
        MenuHelper.initHelpMenu(this, menuBar);

        getContentPane().add(menuBar, BorderLayout.NORTH);
    }

    public void initEncoder() {
        encryptedTextPane.setContentType("text/encrypt");
        JScrollPane textScrollPane = new JScrollPane(textPane);
        tabbedPane.addTab("Text", textScrollPane);
        JScrollPane encryptScrollPane = new JScrollPane(encryptedTextPane);
        tabbedPane.addTab("Encrypt", encryptScrollPane);
        tabbedPane.setPreferredSize(new Dimension(500, 500));
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui() {
        initMenuBar();
        initEncoder();
        pack();
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (CannotUndoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (CannotRedoException e) {
            ExceptionHandler.log(e);
        }
    }

    public void selectedTabChanged() {
//        switch (tabbedPane.getSelectedIndex()) {
//            case 0:
//                controller.setPlainText(encryptedTextPane.getText());
//                break;
//            case 1:
//                textPane.setText(controller.getPlainText());
//                break;
//        }
        resetUndo();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean isEncryptTabSelected() {
//        return tabbedPane.getSelectedIndex() == 0;
        return false;
    }

    public void selectEncryptTab() {
//        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void update() {
//        encryptedTextPane.setDocument(controller.getDocument());
    }

    public void showAbout() {
        JOptionPane.showMessageDialog(this, "Best Caesar Cipher encrypt", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    public JTextPane getTextPane() {
        return textPane;
    }

    public void setTextPane(String text) {
        textPane.setText(text);
    }

    public void exit() {
        controller.exit();
    }

}

