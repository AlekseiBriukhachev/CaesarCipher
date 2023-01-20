package com.aleksei.command.service;

import com.aleksei.command.enumeration.Operation;
import com.aleksei.command.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private String consoleString;
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void writeMessage(String message) {
        System.out.println(message);
    }

    public String readString() {
        try {
            String text = bufferedReader.readLine();
            if ("exit".equalsIgnoreCase(text)) {
                throw new InterruptOperationException();
            }
            return text;
        } catch (InterruptOperationException e) {
            CommandExecutor.execute(Operation.EXIT);
        } catch (IOException e) {
            writeMessage("Not correct entered data");
        }
        return null;
    }

    public Operation askOperation() {
        while (true) {
            writeMessage("Please choose an operation or type 'exit'");
            writeMessage("\t 1 - Encrypt of text to file with key");
            writeMessage("\t 2 - Decrypt of text to file with key");
            writeMessage("\t 3 - Decoding of text with using of Brute Force");
            writeMessage("\t 4 - Decoding of text with using of statistic analyze");
            writeMessage("\t 5 - Exit");
            Integer i = Integer.parseInt(readString().trim());
            try {
                return Operation.getAllowableOperationByOrdinal(i);
            } catch (IllegalArgumentException e) {
                writeMessage("Invalid entered data. Please try again");
            }
        }
    }

    public String getConsoleString() {
        return consoleString;
    }

    public void setConsoleString(String consoleString) {
        this.consoleString = consoleString;
    }

    public void printExitMessage() {
        writeMessage("Good buy. Thank you for choosing of our service");
    }
}
