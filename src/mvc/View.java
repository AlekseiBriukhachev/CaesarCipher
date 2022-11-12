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

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private Encrypter encrypter = new Encrypter();
    private Decrypt decrypt = new Decrypt();
    private BruteForce bruteForce = new BruteForce();
    private StatisticAnalyze statisticAnalyze = new StatisticAnalyze();
    private static String dialogMessage;
    private static String confirmationMessage;
    private JFrame frame = new JFrame("Caesar Cipher");
    private JPanel btnPanel = new JPanel();
    private JPanel textAndBtnPanel = new JPanel();
    private JTextField textField = new JTextField(40);
    public static JTextArea console = new JTextArea(10, 20);
    private JButton encryptBtn = new JButton("Encrypt");
    private JButton decryptBtn = new JButton("Decrypt");
    private JButton bruteForceBtn = new JButton("Brute Force");
    private JButton statisticBtn = new JButton("Statistic analyze");
    private JButton enterBtn = new JButton("Enter");

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

    public void setController(Controller controller) {
        this.controller = controller;
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

//        enterBtn.setActionCommand("enter");
//        enterBtn.addActionListener(this);

        btnPanel.add(encryptBtn);
        btnPanel.add(decryptBtn);
        btnPanel.add(bruteForceBtn);
        btnPanel.add(statisticBtn);

//        textAndBtnPanel.add(new JScrollPane(textField));
//        textAndBtnPanel.add(enterBtn);

        console.setEditable(false);

//        frame.getContentPane().add(textAndBtnPanel, BorderLayout.NORTH);
        frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(console), BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "encrypt":
                encrypter.encrypt();
                break;
            case "decrypt":
                decrypt.decrypt();
                break;
            case "bruteforce":
                bruteForce.bruteForce();
                break;
        }
    }

    public void exit() {
        System.exit(0);
    }
    public static String getDialogMessage(){
        return JOptionPane.showInputDialog(dialogMessage);
    }

    public static void setDialogMessage(String dialogMessage) {
        View.dialogMessage = dialogMessage;
    }
    public static int getConfirmationMessage(){
        return JOptionPane.showConfirmDialog(null, confirmationMessage);
    }
    public  static void setConfirmationMessage(String confirmationMessage){
        View.confirmationMessage = confirmationMessage;
    }
}

