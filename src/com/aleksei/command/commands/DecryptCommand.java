package com.aleksei.command.commands;

import com.aleksei.command.service.CaesarCipher;
import com.aleksei.command.service.ConsoleHelper;
import com.aleksei.command.service.ValidateValues;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DecryptCommand implements Command {
    private final ConsoleHelper consoleHelper = new ConsoleHelper();

    @Override
    public void execute() {
        CaesarCipher caesarCipher = new CaesarCipher();
        ValidateValues validateValues = new ValidateValues();

        consoleHelper.writeMessage("Please enter the path to file for decrypting:");
        String  pathEncryptedFile = consoleHelper.readString();

        validateValues.validatePath(pathEncryptedFile);


        consoleHelper.writeMessage("Enter the key:");
        int key = validateValues.validateKey(consoleHelper.readString());

        consoleHelper.writeMessage("Please enter the path for saving decrypted file:");
        String pathOfFile = consoleHelper.readString();

        validateValues.validatePath(pathOfFile);


        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfFile))){

            while (reader.ready()){
                String line = reader.readLine();
                String decryptedString = caesarCipher.decryptText(line, key);
                writer.write(decryptedString + System.lineSeparator());
            }

            consoleHelper.writeMessage("Decrypting is done!");
        } catch (IOException e) {
            consoleHelper.writeMessage("Not correct entered data");
            consoleHelper.printExitMessage();
        }

    }
}

