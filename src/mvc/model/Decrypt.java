package mvc.model;


import mvc.CaesarCipher;
import mvc.Controller;

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

        String textFromFile = ReaderWriter.readFile(pathEncryptedFile);
        String decryptString = caesarCipher.decryptText(textFromFile, key);
        ReaderWriter.writeFile(decryptString + System.lineSeparator(), pathOfFile);
        ReaderWriter.printMessage("Decrypting is done!");
    }
}

