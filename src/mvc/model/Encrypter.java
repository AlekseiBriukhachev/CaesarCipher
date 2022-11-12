package mvc.model;



import mvc.CaesarCipher;

import java.util.Objects;

public class Encrypter {

    public void encrypt(){
        CaesarCipher caesarCipher = new CaesarCipher();

        ReaderWriter.setDialogText("Please enter the path to file for encrypting:");
       String pathOfFile = ReaderWriter.readDialogMessage();

        ReaderWriter.setDialogText("Please enter the key:");
        int key = Integer.parseInt(Objects.requireNonNull(ReaderWriter.readDialogMessage()));

        ReaderWriter.setDialogText("Please enter the path for saving of encrypted file:");
        String pathOfEncryptedFile = ReaderWriter.readDialogMessage();

        String textFromFile = ReaderWriter.readFile(pathOfFile);
        String encryptString = caesarCipher.encryptText(textFromFile, key);
        ReaderWriter.printMessage(encryptString+ System.lineSeparator());
        ReaderWriter.writeFile(encryptString + System.lineSeparator(), pathOfEncryptedFile);

        ReaderWriter.setDialogText("Encrypting is done!");
    }
}
