package com.aleksei.mvc.model;


import com.aleksei.mvc.ReaderWriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StatisticAnalyze {
    private final Map<Character, Character> mapDeEncrypted = new HashMap<>();
    private final ReaderWriter readerWriter = new ReaderWriter();

    public void analyze() throws IOException {

        readerWriter.setDialogText("Please enter the path to file for decrypting:");
        String pathEncryptedFile = readerWriter.readDialogMessage();

        readerWriter.setDialogText("Please enter the path to open file the same author and the same style:");
        String pathStatisticFile = readerWriter.readDialogMessage();


        readerWriter.setDialogText("Please enter the path for saving decrypted file:");
        String pathNotEncryptedFile = readerWriter.readDialogMessage();


        List<Map.Entry<Character, Long>> listEncryptedFile = mapToList(Objects.requireNonNull(fillMapValues(pathEncryptedFile)));
        List<Map.Entry<Character, Long>> listStatisticFile = mapToList(Objects.requireNonNull(fillMapValues(pathStatisticFile)));

        if (listEncryptedFile.size() <= listStatisticFile.size()) {
            IntStream.range(0, listEncryptedFile.size())
                    .mapToObj(i -> mapDeEncrypted.put(listEncryptedFile.get(i).getKey(), listStatisticFile.get(i).getKey()));

            StringBuilder stringBuilder = new StringBuilder();

            try(BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {
                while (reader.ready()) {
                    String line = reader.readLine();
                    for (char encryptedChar : line.toCharArray()) {
                        Character deEncryptedChar = mapDeEncrypted.get(encryptedChar);
                        stringBuilder.append(deEncryptedChar);
                    }
                    writer.write(stringBuilder + System.lineSeparator());
                }
            }catch (IOException e){
                readerWriter.setDoneMessage("Not correct entered data");
            }

            readerWriter.setDoneMessage("File is decrypted by statistic analyze");
        } else {
            readerWriter.setDoneMessage("The capacity of open file is lower than capacity of encrypted file - must be more.");
        }
    }

    private Map<Character, Long> fillMapValues(String path) {

        try {
            return Files.lines(Paths.get(path))
                    .flatMapToInt(String::chars)
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Map.Entry<Character, Long>> mapToList(Map<Character, Long> map) {
        List<Map.Entry<Character, Long>> list = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<Character, Long>> comparator = Map.Entry.comparingByValue();

        list.sort(comparator.reversed());

        return list;
    }

}
