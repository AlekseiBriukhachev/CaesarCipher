package com.aleksei.mvc.model;


import com.aleksei.mvc.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Encrypter {

    public void encrypt() {
        CaesarCipher caesarCipher = new CaesarCipher();
        Controller controller = new Controller();

        ReaderWriter.setDialogText("Please enter the path to file for encrypting:");
        String pathOfFile = ReaderWriter.readDialogMessage();


        ReaderWriter.setDialogText("Please enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ReaderWriter.readDialogMessage()));

        ReaderWriter.setDialogText("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = ReaderWriter.readDialogMessage();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathOfFile));
             BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfEncryptedFile))) {
            while (reader.ready()) {
                String line = reader.readLine();
                String encryptString = caesarCipher.encryptText(line, key);
                writer.write(encryptString + System.lineSeparator());
            }
            ReaderWriter.setDoneMessage("Encrypting is done!");
        } catch (IOException e) {
            ReaderWriter.setDoneMessage("Not correct entered data");
        }

    }
}
