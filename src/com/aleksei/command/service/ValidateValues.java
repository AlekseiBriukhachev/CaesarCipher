package com.aleksei.command.service;

import java.nio.file.Path;
import java.util.Objects;

public class ValidateValues {
    private final ConsoleHelper consoleHelper = new ConsoleHelper();
    public void validatePath(String path){
        boolean isPath = false;

        do {
            assert path != null;
            if (Path.of(path).isAbsolute()){
                isPath = true;
            }else {
                consoleHelper.writeMessage("Not correct entered data. Please try again");
                path = consoleHelper.readString();
            }
        } while (!isPath);
    }
    public int validateKey(String value){
        int result = 0;
        boolean isNumber = false;
        do {
            try {
                assert value != null;
                result = Integer.parseInt(value);
                isNumber = true;
            } catch (NumberFormatException e) {
                consoleHelper.writeMessage("Not correct entered data. Please try again");
                value = consoleHelper.readString();
            }
        }while (!isNumber);

        return result;

    }
}
