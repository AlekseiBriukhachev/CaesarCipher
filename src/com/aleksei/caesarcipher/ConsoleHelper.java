package com.aleksei.caesarcipher;

import com.aleksei.caesarcipher.exception.InterruptOperationException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConsoleHelper {
    private static View view = new View();
    private static String consoleString;
        private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//    private static BufferedReader bufferedReader = new BufferedReader(new StringReader(textField.getText() + "\n"));

    public static void writeMessage(String message) {
        System.out.println(message);
//        View.console.append(message + "\n");
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

    public static String readFile(String path) {
        String file = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                file += string;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void writeFile(String file, String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            writer.write(file);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Not correct entered data");
        }
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
