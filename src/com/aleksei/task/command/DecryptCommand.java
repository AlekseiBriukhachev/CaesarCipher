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

public class DecryptCommand implements Command {

    @Override
    public void execute() throws InterruptOperationException {
        CaesarCipher caesarCipher = new CaesarCipher();
        ConsoleHelper.writeMessage("Please enter the path to file for decrypting:");
        String  pathEncryptedFile = ConsoleHelper.readString();
        ConsoleHelper.writeMessage("Enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ConsoleHelper.readString()));
        ConsoleHelper.writeMessage("Please enter the path for saving decrypted file:");
        String pathOfFile = ConsoleHelper.readString();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfFile))) {
            if (Files.notExists(Path.of(pathOfFile))){
                Files.createFile(Path.of(pathOfFile));
            }
            while (reader.ready()) {
                String string = reader.readLine();
                String decryptString = caesarCipher.decryptText(string, key);
                writer.write(decryptString + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConsoleHelper.writeMessage("Decrypting is done!");
    }
}

