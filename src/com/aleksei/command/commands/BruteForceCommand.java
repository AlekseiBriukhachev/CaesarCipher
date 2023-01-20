package com.aleksei.command.commands;

import com.aleksei.command.CaesarCipher;
import com.aleksei.command.ConsoleHelper;
import com.aleksei.command.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

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
                int key = 0;
                while (reader.ready()) {
                    String string = reader.readLine();
                    stringBuilder.append(string);
                    listStrings.add(string);
                }

                key = getDecryptingKey(listStrings.get(0));
                for (String line : listStrings) {
                    String decryptString = caesarCipher.decryptText(line, key);
                    writer.write(decryptString + System.lineSeparator());
                }
                ConsoleHelper.writeMessage("File is decrypted by brute forcing. Key is " + key);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Not correct entered data");
            }
        }
    }


    public int getDecryptingKey(String message) {
        return Math.toIntExact(IntStream.range(0, caesarCipher.getAlphabet().length())
                .mapToObj(key -> caesarCipher.decryptText(message, key))
                .takeWhile(s -> !isValidText(s))
                .count());
    }


    private boolean isValidText(String text) {
        ConsoleHelper.writeMessage(text + "\n" + "Can you read the text? Y/N");

        String answer = null;
        try {
            answer = ConsoleHelper.readString();
        } catch (InterruptOperationException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(answer).equalsIgnoreCase("Y");
    }
}

