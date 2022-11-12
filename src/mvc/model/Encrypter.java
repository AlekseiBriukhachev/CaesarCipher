package mvc.model;


import mvc.CaesarCipher;
import mvc.Controller;

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

        String textFromFile = ReaderWriter.readFile(pathOfFile);
        String encryptString = caesarCipher.encryptText(textFromFile, key);
        ReaderWriter.printMessage(encryptString + System.lineSeparator());
        ReaderWriter.writeFile(encryptString + System.lineSeparator(), pathOfEncryptedFile);

        ReaderWriter.printMessage("Encrypting is done!");

    }
}
