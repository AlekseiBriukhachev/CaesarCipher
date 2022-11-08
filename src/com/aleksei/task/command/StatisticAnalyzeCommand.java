package com.aleksei.task.command;

import com.aleksei.task.ConsoleHelper;
import com.aleksei.task.exception.InterruptOperationException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StatisticAnalyzeCommand implements Command {

    private final Map<Character, Integer> mapEncryptedFile = new HashMap<>();
    private final Map<Character, Integer> mapStatisticFile = new HashMap<>();
    private final Map<Character, Character> mapDeEncrypted = new HashMap<>();

    @Override
    public void execute() throws InterruptOperationException, IOException {
        ConsoleHelper.writeMessage("Please enter the path to file for decrypting:");
        String pathEncryptedFile = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Please enter the path to open file the same author and the same style:");
        String pathStatisticFile = ConsoleHelper.readString();

        ConsoleHelper.writeMessage("Please enter the path for saving decrypted file:");
        String pathNotEncryptedFile = ConsoleHelper.readString();

        List<Map.Entry<Character, Integer>> listEncryptedFile = mapToList(fillMapValues(mapEncryptedFile, pathEncryptedFile));
        List<Map.Entry<Character, Integer>> listStatisticFile = mapToList(fillMapValues(mapStatisticFile, pathStatisticFile));

        if (listEncryptedFile.size() <= listStatisticFile.size()) {
            for (int i = 0; i < listEncryptedFile.size(); i++) {
                mapDeEncrypted.put(listEncryptedFile.get(i).getKey(), listStatisticFile.get(i).getKey());
            }

            StringBuilder stringBuilder = new StringBuilder();
            String fileString = ConsoleHelper.readFile(pathEncryptedFile);
            for (char encryptedChar : fileString.toCharArray()) {
                Character deEncryptedChar = mapDeEncrypted.get(encryptedChar);
                stringBuilder.append(deEncryptedChar);
            }
            ConsoleHelper.writeFile(stringBuilder + System.lineSeparator(), pathNotEncryptedFile);


            ConsoleHelper.writeMessage("File is decrypted by statistic analyze");
        } else {
            ConsoleHelper.writeMessage("The capacity of open file is lower than capacity of encrypted file - must be more.");
        }
    }

    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String stringToFilling = ConsoleHelper.readFile(path);
        stringBuilder.append(stringToFilling);
        String file = stringBuilder.toString();
        for (int i = 0; i < file.length(); i++) {
            char charAt = file.charAt(i);
            if (!map.containsKey(charAt)) {
                map.put(charAt, 1);
            } else {
                map.put(charAt, map.get(charAt) + 1);
            }
        }
        return map;
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();

        list.sort(comparator.reversed());

        return list;
    }

}
