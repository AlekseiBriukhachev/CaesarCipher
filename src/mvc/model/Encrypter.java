package mvc.model;


import mvc.CaesarCipher;
import mvc.Controller;

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
        if (pathOfFile == null) controller.exit();

        ReaderWriter.setDialogText("Please enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ReaderWriter.readDialogMessage()));
        if (key == 0) controller.exit();

        ReaderWriter.setDialogText("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = ReaderWriter.readDialogMessage();
        if (pathOfEncryptedFile == null) controller.exit();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathOfFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfEncryptedFile))){
            while (reader.ready()){
                String line = reader.readLine();
                String encryptString = caesarCipher.encryptText(line, key);
                writer.write(encryptString + System.lineSeparator());
            }
        ReaderWriter.setConfirmText("Encrypting is done!");
        } catch (IOException e) {
            ReaderWriter.setConfirmText("Not correct entered data");
        }

    }
}
