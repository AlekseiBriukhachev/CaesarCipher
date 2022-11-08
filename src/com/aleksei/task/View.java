package com.aleksei.task;

import com.aleksei.task.listeners.FrameListener;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private boolean mutex;
    private Controller controller;
    private JFrame frame = new JFrame("Caesar Cipher");
    private JPanel btnPanel = new JPanel();
    public static JTextField textField = new JTextField(40);
    public static JTextArea console = new JTextArea(10, 20);
    private JButton encryptBtn = new JButton("Encrypt");
    private JButton decryptBtn = new JButton("Decrypt");
    private JButton bruteForceBtn = new JButton("Brute Force");
    private JButton statisticBtn = new JButton("Statistic analyze");

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
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void initGui() {
        btnPanel.add(encryptBtn);
        btnPanel.add(decryptBtn);
        btnPanel.add(bruteForceBtn);
        btnPanel.add(statisticBtn);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                console.append(textField.getText() + "\n");
//                ConsoleHelper.setConsoleString(textField.getText() + "\n");
                mutex = true;
                textField.setText("");
            }
        });

        console.setEditable(false);

        frame.getContentPane().add(new JScrollPane(textField), BorderLayout.NORTH);
        frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(console), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public boolean isMutex() {
        return mutex;
    }

    public void setMutex(boolean mutex) {
        this.mutex = mutex;
    }
    public void exit(){
        System.exit(0);
    }
}

