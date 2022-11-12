package com.aleksei.caesarcipher.command;

import com.aleksei.caesarcipher.CaesarCipher;
import com.aleksei.caesarcipher.ConsoleHelper;
import com.aleksei.caesarcipher.exception.InterruptOperationException;

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

        String textFromFile = ConsoleHelper.readFile(pathOfFile);
        String encryptString = caesarCipher.encryptText(textFromFile, key);
        ConsoleHelper.writeFile(encryptString + System.lineSeparator(), pathOfEncryptedFile);

        ConsoleHelper.writeMessage("Encrypting is done!");
    }
}
