package com.aleksei.task.command;

import com.aleksei.task.CaesarCipher;
import com.aleksei.task.ConsoleHelper;
import com.aleksei.task.exception.InterruptOperationException;

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
        ConsoleHelper.writeMessage("Please enter the path to file for encrypting:");
        String pathOfFile = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Please enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ConsoleHelper.readString()));
        ConsoleHelper.writeMessage("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = ConsoleHelper.readString();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathOfFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfEncryptedFile))){
            if (Files.notExists(Path.of(pathOfEncryptedFile))){
                Files.createFile(Path.of(pathOfEncryptedFile));
            }
            while (reader.ready()){
                String line = reader.readLine();
                String encryptString = caesarCipher.encryptText(line, key);
                writer.write(encryptString + System.lineSeparator());
            }
        }catch (IOException e){
            ConsoleHelper.writeMessage("Not correct entered data");
        }
        ConsoleHelper.writeMessage("Encrypting is done!");
    }
}
