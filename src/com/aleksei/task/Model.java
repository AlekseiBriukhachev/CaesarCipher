package com.aleksei.task;

public class Model {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + ".,\":-!? +-*/\\@#$%^&(){}[];'|`~=_©«»—0123456789";
    public String encrypt(String message, int key) {
        StringBuilder result = new StringBuilder();
        for (char letter : message.toCharArray()) {
            int letterPosition = ALPHABET.indexOf(letter);
            int newLetterPosition = 0;
            char encryptedLetter = 0;
            if (letterPosition >= 0) {
                if (key >= 0) {
                    newLetterPosition = (letterPosition + key) % (ALPHABET.length() / 2);
                } else {
                    int newKey = key % (ALPHABET.length() / 2);
                    newLetterPosition = (letterPosition + (ALPHABET.length() / 2) + newKey) % ALPHABET.length();
                }
                encryptedLetter = ALPHABET.charAt(newLetterPosition);
            }
            result.append(encryptedLetter);
        }
        return result.toString();
    }

    public String decrypt(String message, int key) {
        return encrypt(message, key * (-1));
    }
}
