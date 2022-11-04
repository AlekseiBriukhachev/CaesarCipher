package com.aleksei.task;

import javax.swing.filechooser.FileFilter;
import java.io.File;


public class TXTFileFilter extends FileFilter {
    @Override
    public boolean accept(File f) {
        return ((f.isDirectory())
                || (f.getName().toLowerCase().endsWith(".txt")));
    }

    @Override
    public String getDescription() {
        return "TXT files";
    }
}
