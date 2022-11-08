package com.aleksei.task.listeners;

import com.aleksei.task.View;

import java.awt.event.WindowAdapter;

public class FrameListener extends WindowAdapter {
    private View view;

    public FrameListener(View view) {
        this.view = view;
    }

}
