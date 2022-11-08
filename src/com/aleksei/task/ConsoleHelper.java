package com.aleksei.task;

import com.aleksei.task.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConsoleHelper {
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

    public static String readFile(String path){
        String file = "";
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))){
            while (reader.ready()){
                String string = reader.readLine();
                file += string;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
    public static void writeFile (String file, String path){
        try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))){
            writer.write(file);
//            writer.flush();
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
    public static void printExitMessage() {
        ConsoleHelper.writeMessage("Good buy. Thank you for choosing of our service");
    }
}
