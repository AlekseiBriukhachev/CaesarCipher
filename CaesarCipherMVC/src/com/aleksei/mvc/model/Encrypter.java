package com.aleksei.mvc.model;


import com.aleksei.mvc.ReaderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Encrypter {

    public void encrypt() {
        CaesarCipher caesarCipher = new CaesarCipher();
        final ReaderWriter readerWriter = new ReaderWriter();

        readerWriter.setDialogText("Please enter the path to file for encrypting:");
        String pathOfFile = readerWriter.readDialogMessage();


        readerWriter.setDialogText("Please enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(readerWriter.readDialogMessage()));

        readerWriter.setDialogText("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = readerWriter.readDialogMessage();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathOfFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfEncryptedFile))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String encryptString = caesarCipher.encryptText(line, key);
                writer.write(encryptString + System.lineSeparator());
            }
            readerWriter.setDoneMessage("Encrypting is done!");
        } catch (IOException e) {
            readerWriter.setDoneMessage("Not correct entered data");
        }

    }
}
