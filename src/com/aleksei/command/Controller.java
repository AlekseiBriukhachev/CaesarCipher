package com.aleksei.command;


public class Controller {


    public static void main(String[] args) {

        try {
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
            ConsoleHelper.printExitMessage();
        } catch (Exception ignored) {
            ConsoleHelper.printExitMessage();
            ignored.printStackTrace();
        }
    }

}
