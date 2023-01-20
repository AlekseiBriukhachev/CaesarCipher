package com.aleksei.command.commands;

import com.aleksei.command.service.CaesarCipher;
import com.aleksei.command.service.ConsoleHelper;
import com.aleksei.command.exception.InterruptOperationException;
import com.aleksei.command.service.ValidateValues;
import com.sun.jdi.IntegerValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class DecryptCommand implements Command {

    @Override
    public void execute() {
        CaesarCipher caesarCipher = new CaesarCipher();
        ValidateValues validateValues = new ValidateValues();

        ConsoleHelper.writeMessage("Please enter the path to file for decrypting:");
        String  pathEncryptedFile = ConsoleHelper.readString();

        validateValues.validatePath(pathEncryptedFile);


        ConsoleHelper.writeMessage("Enter the key:");
        int key = validateValues.validateKey(ConsoleHelper.readString());

        ConsoleHelper.writeMessage("Please enter the path for saving decrypted file:");
        String pathOfFile = ConsoleHelper.readString();

        validateValues.validatePath(pathOfFile);


        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfFile))){

            while (reader.ready()){
                String line = reader.readLine();
                String decryptedString = caesarCipher.decryptText(line, key);
                writer.write(decryptedString + System.lineSeparator());
            }

            ConsoleHelper.writeMessage("Decrypting is done!");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Not correct entered data");
            ConsoleHelper.printExitMessage();
        }

    }
}

