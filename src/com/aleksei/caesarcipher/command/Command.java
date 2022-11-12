package com.aleksei.caesarcipher.command;

import com.aleksei.caesarcipher.exception.InterruptOperationException;

import java.io.IOException;

public interface Command {
    void execute() throws InterruptOperationException, IOException;
}
