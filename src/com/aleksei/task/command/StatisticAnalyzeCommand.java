package com.aleksei.task.command;

import com.aleksei.task.exception.InterruptOperationException;

public class StatisticAnalyzeCommand implements Command {
    @Override
    public void execute() throws InterruptOperationException {

    }
    //    private final Scanner scanner = new Scanner(System.in);
//
//    private final Map<Character, Integer> mapEncryptedFile = new HashMap<>();
//    private final Map<Character, Integer> mapStatisticFile = new HashMap<>();
//    private final Map<Character, Character> mapDeEncrypted = new HashMap<>();
//
//    public void choiceFour() throws IOException {
//
//        System.out.println("Введите полный путь к файлу, для его расшифровки:");
//        String pathEncryptedFile = scanner.nextLine();
//
//        System.out.println("Введите полный путь к файлу, для набора статистики:");
//        String pathStatisticFile  = scanner.nextLine();
//
//        System.out.println("Введите полный путь к файлу, в который записать расшифрованый текст:");
//        String pathNotEncryptedFile = scanner.nextLine();
//
//        List<Map.Entry<Character, Integer>> listEncryptedFile = mapToList(fillMapValues(mapEncryptedFile, pathEncryptedFile));
//        List<Map.Entry<Character, Integer>> listStatisticFile = mapToList(fillMapValues(mapStatisticFile, pathStatisticFile));
//
//        if (listEncryptedFile.size() <= listStatisticFile.size() ) {
//            for (int i = 0; i < listEncryptedFile.size(); i++) {
//                mapDeEncrypted.put(listEncryptedFile.get(i).getKey(), listStatisticFile.get(i).getKey());
//            }
//            try (BufferedReader reader = Files.newBufferedReader(Paths.get(pathEncryptedFile));
//                 BufferedWriter writer = Files.newBufferedWriter(Paths.get(pathNotEncryptedFile))) {
//                StringBuilder stringBuilder = new StringBuilder();
//                while (reader.ready()) {
//                    String string = reader.readLine();
//                    for (char encryptedChar : string.toCharArray()) {
//                        Character deEncryptedChar = mapDeEncrypted.get(encryptedChar);
//                        stringBuilder.append(deEncryptedChar);
//                    }
//                    writer.write(stringBuilder + System.lineSeparator());
//                }
//            }
//            System.out.println("Содержимое файла расшифровано методом статистического анализа.");
//        } else {
//            System.out.println("Размер файла статистики недостаточен для расшифровки, необходим файл большей длины чем зашифрованный");
//        }
//    }
//
//    private Map<Character, Integer> fillMapValues(Map<Character, Integer> map, String path) throws IOException {
//
//        StringBuilder stringBuilder = new StringBuilder();
//        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
//            while (reader.ready()) {
//                String string = reader.readLine();
//                stringBuilder.append(string);
//            }
//            String bigString = stringBuilder.toString();
//            for (int i = 0; i < bigString.length(); i++) {
//                char charAt = bigString.charAt(i);
//                if (!map.containsKey(charAt)) {
//                    map.put(charAt, 1);
//                } else {
//                    map.put(charAt, map.get(charAt) + 1);
//                }
//            }
//            return map;
//        }
//    }
//
//    private List<Map.Entry<Character, Integer>> mapToList(Map<Character, Integer> map) {
//        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
//
//        Comparator<Map.Entry<Character, Integer>> comparator = Map.Entry.comparingByValue();
//
//        list.sort(comparator.reversed());
//
//        return list;
//    }
}
