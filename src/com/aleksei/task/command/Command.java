package com.aleksei.task.command;

import com.aleksei.task.exception.InterruptOperationException;

import java.io.IOException;

public interface Command {
    void execute() throws InterruptOperationException, IOException;
}
