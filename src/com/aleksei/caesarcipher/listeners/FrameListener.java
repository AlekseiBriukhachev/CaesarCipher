package com.aleksei.caesarcipher.listeners;

import com.aleksei.caesarcipher.View;

import java.awt.event.WindowAdapter;

public class FrameListener extends WindowAdapter {
    private View view;

    public FrameListener(View view) {
        this.view = view;
    }

}
