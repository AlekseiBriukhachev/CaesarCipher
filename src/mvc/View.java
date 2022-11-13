package mvc;


import mvc.model.BruteForce;
import mvc.model.Decrypt;
import mvc.model.Encrypter;
import mvc.model.StatisticAnalyze;

import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class View extends JFrame implements ActionListener {
    private final Encrypter encrypter = new Encrypter();
    private final Decrypt decrypt = new Decrypt();
    private final BruteForce bruteForce = new BruteForce();
    private final StatisticAnalyze statisticAnalyze = new StatisticAnalyze();
    private static String dialogMessage;
    private static String confirmationMessage;
    private static String doneConfirmation;
    private final JFrame frame = new JFrame("Caesar Cipher");
    private final JPanel btnPanel = new JPanel();
    protected static JTextArea console = new JTextArea(10, 20);
    private final JButton encryptBtn = new JButton("Encrypt");
    private final JButton decryptBtn = new JButton("Decrypt");
    private final JButton bruteForceBtn = new JButton("Brute Force");
    private final JButton statisticBtn = new JButton("Statistic analyze");

    public View() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        } catch (Exception e) {
        }
    }

    public void init() {
        initGui();
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    public void initGui() {
        encryptBtn.setActionCommand("encrypt");
        encryptBtn.addActionListener(this);

        decryptBtn.setActionCommand("decrypt");
        decryptBtn.addActionListener(this);

        bruteForceBtn.setActionCommand("bruteforce");
        bruteForceBtn.addActionListener(this);

        statisticBtn.setActionCommand("statisticAnalyze");
        statisticBtn.addActionListener(this);

        btnPanel.add(encryptBtn);
        btnPanel.add(decryptBtn);
        btnPanel.add(bruteForceBtn);
        btnPanel.add(statisticBtn);

        console.setEditable(false);

        frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(console), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "encrypt" -> encrypter.encrypt();
            case "decrypt" -> decrypt.decrypt();
            case "bruteforce" -> bruteForce.bruteForce();
            case "statisticAnalyze" -> {
                try {
                    statisticAnalyze.analyze();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    public void exit() {
        System.exit(0);
    }

    public static String getDialogMessage() {
        return JOptionPane.showInputDialog(dialogMessage);
    }

    public static void setDialogMessage(String dialogMessage) {
        View.dialogMessage = dialogMessage;
    }

    public static int getConfirmationMessage() {
        return JOptionPane.showConfirmDialog(null, confirmationMessage);
    }

    public static void setConfirmationMessage(String confirmationMessage) {
        View.confirmationMessage = confirmationMessage;
    }

    public static void getDoneConfirmation() {
        JOptionPane.showMessageDialog(null, doneConfirmation);
    }

    public static void setDoneConfirmation(String doneConfirmation) {
        View.doneConfirmation = doneConfirmation;
    }
}

