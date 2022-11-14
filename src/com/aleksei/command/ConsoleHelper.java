package com.aleksei.command;

import com.aleksei.command.exception.InterruptOperationException;

import java.io.*;

public class ConsoleHelper {
    private static String consoleString;
        private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws InterruptOperationException {
        try {
                String text = bufferedReader.readLine();
                if ("exit".equalsIgnoreCase(text)) {
                    throw new InterruptOperationException();
                }
                return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Operation askOperation() throws InterruptOperationException {
        while (true) {
            ConsoleHelper.writeMessage("Please choose an operation or type 'exit'");
            ConsoleHelper.writeMessage("\t 1 - Encrypt of text to file with key");
            ConsoleHelper.writeMessage("\t 2 - Decrypt of text to file with key");
            ConsoleHelper.writeMessage("\t 3 - Decoding of text with using of Brute Force");
            ConsoleHelper.writeMessage("\t 4 - Decoding of text with using of statistic analyze");
            ConsoleHelper.writeMessage("\t 5 - Exit");
            Integer i = Integer.parseInt(ConsoleHelper.readString().trim());
            try {
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Invalid entered data. Please try again");
            }
        }
    }

    public static String getConsoleString() {
        return consoleString;
    }

    public static void setConsoleString(String consoleString) {
        ConsoleHelper.consoleString = consoleString;
    }

    public static void printExitMessage() {
        ConsoleHelper.writeMessage("Good buy. Thank you for choosing of our service");
    }
}
