package mvc.model;


import mvc.CaesarCipher;
import mvc.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Decrypt {


    public void decrypt() {
        CaesarCipher caesarCipher = new CaesarCipher();
        Controller controller = new Controller();

        ReaderWriter.setDialogText("Please enter the path to file for decrypting:");
        String  pathEncryptedFile = ReaderWriter.readDialogMessage();
        if (pathEncryptedFile == null) controller.exit();

        ReaderWriter.setDialogText("Enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ReaderWriter.readDialogMessage()));
        if (key == 0) controller.exit();

        ReaderWriter.setDialogText("Please enter the path for saving decrypted file:");
        String pathOfFile = ReaderWriter.readDialogMessage();
        if (pathOfFile == null) controller.exit();

        try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathOfFile))){
            while (reader.ready()){
                String line = reader.readLine();
                String decryptedString = caesarCipher.decryptText(line, key);
                writer.write(decryptedString + System.lineSeparator());
            }
            ReaderWriter.setDoneMessage("Decrypting is done!");
        } catch (IOException e) {
            ReaderWriter.setConfirmText("Not correct entered data");
        }

    }
}

