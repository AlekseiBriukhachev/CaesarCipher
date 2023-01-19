package com.aleksei.mvc;

import com.aleksei.mvc.model.ReaderWriter;

public class Controller {
    private View view = new View();
    public void exit(){
        view.exit();
    }

    public static void main(String[] args) {
        View view = new View();
        view.init();

        ReaderWriter.printMessage("Please choose an operation:\n" +
                "\t Encrypt - Encrypt of text to file with key\n" +
                "\t Decrypt - Decrypt of text to file with key\n" +
                "\t Brute Force - Decoding of text with using of Brute Force\n" +
                "\t Statistic analyze - Decoding of text with using of statistic analyze");
    }
}
