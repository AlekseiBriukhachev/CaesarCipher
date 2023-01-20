package com.aleksei.command.service;

import java.nio.file.Path;
import java.util.Objects;

public class ValidateValues {
    public void validatePath(String path){
        boolean isPath = false;

        do {
            assert path != null;
            if (Path.of(path).isAbsolute()){
                isPath = true;
            }else {
                ConsoleHelper.writeMessage("Not correct entered data. Please try again");
                path = ConsoleHelper.readString();
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
                ConsoleHelper.writeMessage("Not correct entered data. Please try again");
                value = ConsoleHelper.readString();
            }
        }while (!isNumber);

        return result;

    }
}
