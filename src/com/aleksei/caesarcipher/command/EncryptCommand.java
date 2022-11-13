package com.aleksei.caesarcipher.command;

import com.aleksei.caesarcipher.CaesarCipher;
import com.aleksei.caesarcipher.ConsoleHelper;
import com.aleksei.caesarcipher.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class EncryptCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {
        CaesarCipher caesarCipher = new CaesarCipher();
        boolean isPath = false;

        ConsoleHelper.writeMessage("Please enter the path to file for encrypting:");
        String pathOfFile = ConsoleHelper.readString();
        do {
            if (Path.of(pathOfFile).isAbsolute()){
                isPath = true;
            }else {
                ConsoleHelper.writeMessage("Not correct entered data. Please try again");
            }
        } while (!isPath);
        isPath = false;

        ConsoleHelper.writeMessage("Please enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ConsoleHelper.readString()));

        ConsoleHelper.writeMessage("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = ConsoleHelper.readString();
        do {
            if (Path.of(pathOfEncryptedFile).isAbsolute()){
                isPath = true;
            }else {
                ConsoleHelper.writeMessage("Not correct entered data. Please try again");
                pathOfEncryptedFile = ConsoleHelper.readString();
            }
        } while (!isPath);

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
