package mvc.model;


import mvc.Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StatisticAnalyze {

    private final Map<Character, Integer> mapEncryptedFile = new HashMap<>();
    private final Map<Character, Integer> mapStatisticFile = new HashMap<>();
    private final Map<Character, Character> mapDeEncrypted = new HashMap<>();

    public void analyze() throws IOException {
        Controller controller = new Controller();
        ReaderWriter.setDialogText("Please enter the path to file for decrypting:");
        String pathEncryptedFile = ReaderWriter.readDialogMessage();
        if (pathEncryptedFile == null) controller.exit();

        ReaderWriter.setDialogText("Please enter the path to open file the same author and the same style:");
        String pathStatisticFile = ReaderWriter.readDialogMessage();
        if (pathStatisticFile == null) controller.exit();

        ReaderWriter.setDialogText("Please enter the path for saving decrypted file:");
        String pathNotEncryptedFile = ReaderWriter.readDialogMessage();
        if (pathNotEncryptedFile == null) controller.exit();

        List<Map.Entry<Character, Integer>> listEncryptedFile = mapToList(fillMapValues(mapEncryptedFile, pathEncryptedFile));
        List<Map.Entry<Character, Integer>> listStatisticFile = mapToList(fillMapValues(mapStatisticFile, pathStatisticFile));

        if (listEncryptedFile.size() <= listStatisticFile.size()) {
            for (int i = 0; i < listEncryptedFile.size(); i++) {
                mapDeEncrypted.put(listEncryptedFile.get(i).getKey(), listStatisticFile.get(i).getKey());
            }

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
                ReaderWriter.setDoneMessage("Not correct entered data");
            }

            ReaderWriter.setDoneMessage("File is decrypted by statistic analyze");
        } else {
            ReaderWriter.setDoneMessage("The capacity of open file is lower than capacity of encrypted file - must be more.");
        }
    }

    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                stringBuilder.append(string);
            }
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
    }

    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());

        Comparator<Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();

        list.sort(comparator.reversed());

        return list;
    }

}
