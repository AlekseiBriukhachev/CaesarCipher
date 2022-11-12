package mvc.model;


import mvc.CaesarCipher;
import mvc.Controller;

public class BruteForce {
    private final CaesarCipher caesarCipher = new CaesarCipher();
//    private final Controller controller = new Controller();

    public void bruteForce() {
        Controller controller = new Controller();
        ReaderWriter.setConfirmText("WARNING! Brut forcing is NOT LEGAL!\nDo you want to continue?");
        switch (ReaderWriter.readConfirmationMessage()) {
            case 0 -> {
                ReaderWriter.setDialogText("Please enter the path to file for decrypting:");
                String pathEncryptedFile = ReaderWriter.readDialogMessage();
                ReaderWriter.setDialogText("Please enter the path for saving decrypted file:");
                String pathNotEncryptedFile = ReaderWriter.readDialogMessage();
                String fileText = ReaderWriter.readFile(pathEncryptedFile);
                int key = getDecryptingKey(fileText);
                String decryptString = caesarCipher.decryptText(fileText, key);
                ReaderWriter.writeFile(decryptString + System.lineSeparator(), pathNotEncryptedFile);
                ReaderWriter.printMessage("File is decrypted by brute forcing. Key is " + key);
            }
            case 2 -> controller.exit();
        }
    }


    public int getDecryptingKey(String message) {
        int key = 0;
        String decryptString = "";

        for (int i = 0; i < caesarCipher.getAlphabet().length(); i++) {
            for (int j = 0; j < message.length(); j++) {
                int letterPosition = caesarCipher.getAlphabet().indexOf(message.charAt(j));
                char decryptChar;
                if (letterPosition >= 0) {
                    if (message.toCharArray()[j] != ' ') {
                        int decryptPos = (letterPosition - i) % caesarCipher.getAlphabet().length();
                        if (decryptPos < 0) {
                            decryptPos = caesarCipher.getAlphabet().length() + decryptPos;
                        }
                        decryptChar = caesarCipher.getAlphabet().charAt(decryptPos);
                    } else {
                        decryptChar = ' ';
                    }
                } else {
                    decryptChar = message.charAt(j);
                }
                decryptString += decryptChar;
            }
            if (isValidText(decryptString)) {
                key = i;
                break;
            } else {
                decryptString = "";
            }
        }
        return key;
    }


    private boolean isValidText(String text) {
        Controller controller = new Controller();
        ReaderWriter.printMessage(text);
        ReaderWriter.setConfirmText("Can you read the text?");

        int answer = ReaderWriter.readConfirmationMessage();

        switch (answer) {
            case 0:
                return true;
            case 1:
                return false;
            case 2:
                controller.exit();
        }
        return false;
    }
}

