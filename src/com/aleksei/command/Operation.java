package com.aleksei.command;

public enum Operation {
    ENCRYPT,
    DECRYPT,
    BRUTEFORCE,
    STATISTIC,
    EXIT;

    public static Operation getAllowableOperationByOrdinal(Integer i) {

        return switch (i) {
            case 1 -> ENCRYPT;
            case 2 -> DECRYPT;
            case 3 -> BRUTEFORCE;
            case 4 -> STATISTIC;
            case 5 -> EXIT;
            default -> null;
        };
    }
}
