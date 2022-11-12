package com.aleksei.caesarcipher.command;

import com.aleksei.caesarcipher.CaesarCipher;
import com.aleksei.caesarcipher.ConsoleHelper;
import com.aleksei.caesarcipher.exception.InterruptOperationException;

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

        String textFromFile = ConsoleHelper.readFile(pathEncryptedFile);
        String decryptString = caesarCipher.decryptText(textFromFile, key);
        ConsoleHelper.writeFile(decryptString + System.lineSeparator(), pathOfFile);
        ConsoleHelper.writeMessage("Decrypting is done!");
    }
}

