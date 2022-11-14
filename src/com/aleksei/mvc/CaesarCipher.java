package com.aleksei.mvc;

public class CaesarCipher {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + ".,\":-!? ";

    public String encryptText(String message, int key) {
        String encryptString = "";
        for (int i = 0; i < message.length(); i++) {
            int letterPosition = ALPHABET.indexOf(message.charAt(i));
            char encryptChar;
            if (letterPosition >= 0) {
                int encryptPos = (key + letterPosition) % ALPHABET.length();
                encryptChar = ALPHABET.charAt(encryptPos);
            } else {
                encryptChar = message.charAt(i);
            }
            encryptString += encryptChar;
        }
        return encryptString;
    }

    public String decryptText(String message, int shiftKey) {
        String decryptString = "";
        for (int i = 0; i < message.length(); i++) {
            int letterPosition = ALPHABET.indexOf(message.charAt(i));
            char decryptChar;
            if (letterPosition >= 0) {
                int decryptPos = (letterPosition - shiftKey) % ALPHABET.length();
                if (decryptPos < 0) {
                    decryptPos = ALPHABET.length() + decryptPos;
                }
                decryptChar = ALPHABET.charAt(decryptPos);
            }else {
                decryptChar = message.charAt(i);
            }
            decryptString += decryptChar;
        }
        return decryptString;
    }
    public String getAlphabet(){
        return ALPHABET;
    }

}

