package com.aleksei.mvc;

public class Controller {
    private final View view = new View();
    public void exit(){
        view.exit();
    }

    public static void main(String[] args) {
        final ReaderWriter readerWriter = new ReaderWriter();
        View view = new View();
        view.init();

        readerWriter.printMessage("""
                \t Please choose an operation:
                \t Encrypt - Encrypt of text to file with key
                \t Decrypt - Decrypt of text to file with key
                \t Brute Force - Decoding of text with using of Brute Force
                \t Statistic analyze - Decoding of text with using of statistic analyze""");
    }
}
