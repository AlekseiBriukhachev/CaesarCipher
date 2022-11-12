package mvc.model;


import mvc.CaesarCipher;

import java.util.Objects;

public class Decrypt {


    public void decrypt() {
        CaesarCipher caesarCipher = new CaesarCipher();

        ReaderWriter.setDialogText("Please enter the path to file for decrypting:");
        String  pathEncryptedFile = ReaderWriter.readDialogMessage();

        ReaderWriter.setDialogText("Enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ReaderWriter.readDialogMessage()));

        ReaderWriter.setDialogText("Please enter the path for saving decrypted file:");
        String pathOfFile = ReaderWriter.readDialogMessage();

        String textFromFile = ReaderWriter.readFile(pathEncryptedFile);
        String decryptString = caesarCipher.decryptText(textFromFile, key);
        ReaderWriter.writeFile(decryptString + System.lineSeparator(), pathOfFile);
        ReaderWriter.setDialogText("Decrypting is done!");
    }
}

