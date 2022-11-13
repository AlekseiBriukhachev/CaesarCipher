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
        }

    }
}

