package com.aleksei.task.command;

import com.aleksei.task.CaesarCipher;
import com.aleksei.task.ConsoleHelper;
import com.aleksei.task.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

            try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
                 BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {

                StringBuilder stringBuilder = new StringBuilder();
                List<String> listStrings = new ArrayList<>();

                while (reader.ready()) {
                    String string = reader.readLine();
                    stringBuilder.append(string);
                    listStrings.add(string);
                }

                int key = getDecryptingKey(listStrings.get(0));
                String fileText = "";
                for (String listString : listStrings) {
                    fileText += listString;
                }
                String decryptString = caesarCipher.decryptText(fileText, key);
                writer.write(decryptString + System.lineSeparator());
                ConsoleHelper.writeMessage("File is decrypted by brute forcing. Key is " + key);


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
