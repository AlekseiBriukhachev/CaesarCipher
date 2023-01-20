package com.aleksei.command.commands;

import com.aleksei.command.service.CaesarCipher;
import com.aleksei.command.service.ConsoleHelper;
import com.aleksei.command.service.ValidateValues;

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
    private final ConsoleHelper consoleHelper = new ConsoleHelper();


    @Override
    public void execute() {
        ValidateValues validateValues = new ValidateValues();

        consoleHelper.writeMessage("WARNING! Brut forcing is NOT LEGAL!\nDo you want to continue? Y/N");
        if (Objects.requireNonNull(consoleHelper.readString()).equalsIgnoreCase("y")) {

            consoleHelper.writeMessage("Please enter the path to file for decrypting:");
            String pathEncryptedFile = consoleHelper.readString();

            validateValues.validatePath(pathEncryptedFile);


            consoleHelper.writeMessage("Please enter the path for saving decrypted file:");
            String pathNotEncryptedFile = consoleHelper.readString();

            validateValues.validatePath(pathNotEncryptedFile);


            try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
                 BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {
                StringBuilder stringBuilder = new StringBuilder();
                List<String> listStrings = new ArrayList<>();
                int key;
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
                consoleHelper.writeMessage("File is decrypted by brute forcing. Key is " + key);
            } catch (IOException e) {
                consoleHelper.writeMessage("Not correct entered data");
                consoleHelper.printExitMessage();
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
        consoleHelper.writeMessage(text + "\n" + "Can you read the text? Y/N");

        String answer =  consoleHelper.readString();

        return Objects.requireNonNull(answer).equalsIgnoreCase("Y");
    }
}

