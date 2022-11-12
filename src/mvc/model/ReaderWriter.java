package mvc.model;


import mvc.View;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReaderWriter {
    private View view = new View();
    public static String readDialogMessage(){
        return View.getDialogMessage();
    }
    public static void setDialogText(String text){
        View.setDialogMessage(text);
    }
    public static void setConfirmText(String text){
        View.setConfirmationMessage(text);
    }
    public static void printMessage(String message){
        View.console.setText(message + "\n");
    }
    public static String readFile(String path) {
        String file = "";
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            while (reader.ready()) {
                String string = reader.readLine();
                file += string;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    public static void writeFile(String file, String path) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            writer.write(file);
        } catch (IOException e) {
            ReaderWriter.printMessage("Not correct entered data");
        }
    }
}
