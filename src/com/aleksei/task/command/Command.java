package com.aleksei.task.command;

import com.aleksei.task.exception.InterruptOperationException;

public interface Command {
    void execute() throws InterruptOperationException;
}
