package com.aleksei.command.commands;

import com.aleksei.command.service.CaesarCipher;
import com.aleksei.command.service.ConsoleHelper;
import com.aleksei.command.exception.InterruptOperationException;
import com.aleksei.command.service.ValidateValues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class EncryptCommand implements Command {
    @Override
    public void execute() {
        CaesarCipher caesarCipher = new CaesarCipher();
        ValidateValues validateValues = new ValidateValues();

        ConsoleHelper.writeMessage("Please enter the path to file for encrypting:");
        String pathOfFile = ConsoleHelper.readString();

        validateValues.validatePath(pathOfFile);

        ConsoleHelper.writeMessage("Please enter the key:");
        int key = validateValues.validateKey(ConsoleHelper.readString());

        ConsoleHelper.writeMessage("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = ConsoleHelper.readString();

        validateValues.validatePath(pathOfEncryptedFile);

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathOfFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfEncryptedFile))){
            while (reader.ready()){
                String line = reader.readLine();
                String encryptString = caesarCipher.encryptText(line, key);
                writer.write(encryptString + System.lineSeparator());
            }
            ConsoleHelper.writeMessage("Encrypting is done!");
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Not correct entered data");
        }
    }


}
