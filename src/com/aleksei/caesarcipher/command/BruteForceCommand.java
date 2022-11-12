package com.aleksei.caesarcipher.command;

import com.aleksei.caesarcipher.CaesarCipher;
import com.aleksei.caesarcipher.ConsoleHelper;
import com.aleksei.caesarcipher.exception.InterruptOperationException;

public class BruteForceCommand implements Command {
    private final CaesarCipher caesarCipher = new CaesarCipher();

    @Override
    public void execute() throws InterruptOperationException {
        ConsoleHelper.writeMessage("WARNING! Brut forcing is NOT LEGAL!\nDo you want to continue? Y/N");
        if (ConsoleHelper.readString().equalsIgnoreCase("y")) {

            ConsoleHelper.writeMessage("Please enter the path to file for decrypting:");
            String pathEncryptedFile = ConsoleHelper.readString();

            ConsoleHelper.writeMessage("Please enter the path for saving decrypted file:");
            String pathNotEncryptedFile = ConsoleHelper.readString();

            String fileText = ConsoleHelper.readFile(pathEncryptedFile);
            int key = getDecryptingKey(fileText);
            String decryptString = caesarCipher.decryptText(fileText, key);
            ConsoleHelper.writeFile(decryptString + System.lineSeparator(), pathNotEncryptedFile);
            ConsoleHelper.writeMessage("File is decrypted by brute forcing. Key is " + key);
        }
    }


    public int getDecryptingKey(String message) throws InterruptOperationException {
        int key = 0;
        String decryptString = "";

        for (int i = 0; i < caesarCipher.getAlphabet().length(); i++) {
            for (int j = 0; j < message.length(); j++) {
                int letterPosition = caesarCipher.getAlphabet().indexOf(message.charAt(j));
                char decryptChar;
                if (letterPosition >= 0) {
                    if (message.toCharArray()[j] != ' ') {
                        int decryptPos = (letterPosition - i) % caesarCipher.getAlphabet().length();
                        if (decryptPos < 0) {
                            decryptPos = caesarCipher.getAlphabet().length() + decryptPos;
                        }
                        decryptChar = caesarCipher.getAlphabet().charAt(decryptPos);
                    } else {
                        decryptChar = ' ';
                    }
                } else {
                    decryptChar = message.charAt(j);
                }
                decryptString += decryptChar;
            }
            if (isValidText(decryptString)) {
                key = i;
                break;
            } else {
                decryptString = "";
            }
        }
        return key;
    }


    private boolean isValidText(String text) throws InterruptOperationException {
        ConsoleHelper.writeMessage(text + "\n" + "Can you read the text? Y/N");

        String answer = ConsoleHelper.readString();

        if (answer.equalsIgnoreCase("Y")) {
            return true;
        }
        return false;
    }
}

