package com.aleksei.command;

import java.util.stream.IntStream;

public class CaesarCipher {
    private static final String ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ"
            + ".,\":-!? ";

    public String encryptText(String message, int key) {
        return IntStream.range(0, message.length())
                .mapToObj(i -> encryptChar(message.charAt(i), key))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public String decryptText(String message, int key) {
        return IntStream.range(0, message.length())
                .mapToObj(i -> decryptChar(message.charAt(i), key))
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }
    private static char encryptChar(char c, int key) {
        int letterPosition = ALPHABET.indexOf(c);
            char encryptChar;
            if (letterPosition >= 0) {
                int encryptPos = (key + letterPosition) % ALPHABET.length();
                encryptChar = ALPHABET.charAt(encryptPos);
            } else {
                encryptChar = c;
            }
            return encryptChar;
    }

    private static char decryptChar(char c, int key) {
        int letterPosition = ALPHABET.indexOf(c);
            char decryptChar;
            if (letterPosition >= 0) {
                int decryptPos = (letterPosition - key) % ALPHABET.length();
                if (decryptPos < 0) {
                    decryptPos = ALPHABET.length() + decryptPos;
                }
                decryptChar = ALPHABET.charAt(decryptPos);
            }else {
                decryptChar = c;
            }
            return decryptChar;
    }

    public String getAlphabet(){
        return ALPHABET;
    }

}

