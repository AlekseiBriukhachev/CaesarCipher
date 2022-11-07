package com.aleksei.task;

import com.aleksei.task.command.*;
import com.aleksei.task.exception.InterruptOperationException;

import java.util.HashMap;
import java.util.Map;

public class CommandExecutor {
    private static final Map<Operation, Command> commandMap = new HashMap<>();
    static {
        commandMap.put(Operation.ENCRYPT, new EncryptCommand());
        commandMap.put(Operation.DECRYPT, new DecryptCommand());
        commandMap.put(Operation.BRUTEFORCE, new BruteForceCommand());
        commandMap.put(Operation.STATISTIC, new StatisticAnalyzeCommand());
        commandMap.put(Operation.EXIT, new ExitCommand());
    }

    public static final void execute(Operation operation) throws InterruptOperationException {
        commandMap.get(operation).execute();
    }
}
