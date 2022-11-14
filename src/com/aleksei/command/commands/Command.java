package com.aleksei.command.commands;

import com.aleksei.command.exception.InterruptOperationException;

import java.io.IOException;

public interface Command {
    void execute() throws InterruptOperationException, IOException;
}
