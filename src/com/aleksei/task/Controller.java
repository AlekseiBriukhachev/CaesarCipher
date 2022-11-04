package com.aleksei.task;

import com.aleksei.task.view.*;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;

public class Controller {
    private View view;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public void init() {
        createNewDocument();
    }
    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        view.setController(controller);
        view.init();
        controller.init();
    }
//    public void resetDocument() {
//        UndoListener undoListener = view.getUndoListener();
//        if (currentFile != null) {
//            view.setTextPane("");
//        }
//        view.update();
//    }

//    public void setPlainText(String text) {
//        resetDocument();
//        StringReader stringReader = new StringReader(text);
//        try {
//            new HTMLEditorKit().read(stringReader, document, 0);
//        } catch (IOException | BadLocationException e) {
//            ExceptionHandler.log(e);
//        }
//    }

//    public String getPlainText() {
//        StringWriter stringWriter = new StringWriter();
//        try {
//            HTMLEditorKit htmlEditorKit = new HTMLEditorKit();
//            htmlEditorKit.write(stringWriter, document, 0, document.getLength());
//        } catch (IOException | BadLocationException e) {
//            ExceptionHandler.log(e);
//        }
//        return stringWriter.toString();
//    }

    public void createNewDocument() {
        view.selectEncryptTab();
//        resetDocument();
        view.setTitle("Caesar Cipher");
        view.resetUndo();
        currentFile = null;
        view.setTextPane("");
    }

    public void openDocument() {
        view.selectEncryptTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new TXTFileFilter());
        if (fileChooser.showOpenDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
//            resetDocument();
            view.setTitle(currentFile.getName());

            try (FileReader fileReader = new FileReader(currentFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                String line = "";
                String str = "";
                line = bufferedReader.readLine();
                while ((str = bufferedReader.readLine()) != null){
                    line = line + "\n" + str;
                }
                view.setTextPane(line);
            } catch (IOException e) {
                ExceptionHandler.log(e);
            }

            view.resetUndo();
        }
    }

    public void saveDocument() {
        view.selectEncryptTab();
        if (currentFile != null) {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
                try(FileWriter fileWriter = new FileWriter(currentFile, false)) {
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    bufferedWriter.write(view.getTextPane().getText());
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e) {
                    ExceptionHandler.log(e);
                }
            }
        } else {
            saveDocumentAs();
        }
    }

    public void saveDocumentAs() {
        view.selectEncryptTab();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new TXTFileFilter());
        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try(FileWriter fileWriter = new FileWriter(currentFile)) {
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(view.getTextPane().getText());
                bufferedWriter.flush();
                bufferedWriter.close();
            } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        }
    }
}
