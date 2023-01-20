package com.aleksei.command;


import com.aleksei.command.enumeration.Operation;
import com.aleksei.command.service.CommandExecutor;
import com.aleksei.command.service.ConsoleHelper;

public class Runner {


    public static void main(String[] args) {

            Operation operation;

            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);

            ConsoleHelper.printExitMessage();
    }

}
