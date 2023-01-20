package com.aleksei.mvc.model;


import com.aleksei.mvc.Controller;
import com.aleksei.mvc.ReaderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Decrypt {
    private final ReaderWriter readerWriter = new ReaderWriter();


    public void decrypt() {
        CaesarCipher caesarCipher = new CaesarCipher();
        Controller controller = new Controller();

        readerWriter.setDialogText("Please enter the path to file for decrypting:");
        String  pathEncryptedFile = readerWriter.readDialogMessage();


        readerWriter.setDialogText("Enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(readerWriter.readDialogMessage()));
        if (key == 0) controller.exit();

        readerWriter.setDialogText("Please enter the path for saving decrypted file:");
        String pathOfFile = readerWriter.readDialogMessage();


        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfFile))){
            while (reader.ready()){
                String line = reader.readLine();
                String decryptedString = caesarCipher.decryptText(line, key);
                writer.write(decryptedString + System.lineSeparator());
            }
            readerWriter.setDoneMessage("Decrypting is done!");
        } catch (IOException e) {
            readerWriter.setConfirmText("Not correct entered data");
        }

    }
}

