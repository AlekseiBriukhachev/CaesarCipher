package com.aleksei.mvc.model;


import com.aleksei.mvc.CaesarCipher;
import com.aleksei.mvc.Controller;
import com.aleksei.mvc.ReaderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class BruteForce {
    private final CaesarCipher caesarCipher = new CaesarCipher();

    public void bruteForce() {
        Controller controller = new Controller();

        ReaderWriter.setConfirmText("WARNING! Brut forcing is NOT LEGAL!\nDo you want to continue?");
        switch (ReaderWriter.readConfirmationMessage()) {
            case 0 -> {
                ReaderWriter.setDialogText("Please enter the path to file for decrypting:");
                String pathEncryptedFile = ReaderWriter.readDialogMessage();


                ReaderWriter.setDialogText("Please enter the path for saving decrypted file:");
                String pathNotEncryptedFile = ReaderWriter.readDialogMessage();


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
                    for (String line : listStrings){
                        String decryptString = caesarCipher.decryptText(line, key);
                        writer.write(decryptString + System.lineSeparator());
                    }
                    ReaderWriter.setDoneMessage("File is decrypted by brute forcing. Key is " + key);
                }catch (IOException e) {
                    ReaderWriter.setDoneMessage("Not correct entered data");
                }
            }
            case 2 -> controller.exit();
        }
    }


    public int getDecryptingKey(String message) {
        return Math.toIntExact(IntStream.range(0, caesarCipher.getAlphabet().length())
                .mapToObj(key -> caesarCipher.decryptText(message, key))
                .filter(this::isValidText)
                .count());
    }


    private boolean isValidText(String text) {
        Controller controller = new Controller();
        ReaderWriter.printMessage(text);
        ReaderWriter.setConfirmText("Can you read the text?");

        int answer = ReaderWriter.readConfirmationMessage();

        switch (answer) {
            case 0 -> {
                return true;
            }
            case 1 -> {
                return false;
            }
            case 2 -> controller.exit();
        }
        return false;
    }
}

