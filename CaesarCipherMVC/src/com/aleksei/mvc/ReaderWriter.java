package com.aleksei.mvc;


import com.aleksei.mvc.View;

public class ReaderWriter {
    public static String readDialogMessage(){
        return View.getDialogMessage();
    }
    public static void setDialogText(String text){
        View.setDialogMessage(text);
    }
    public static int readConfirmationMessage(){return View.getConfirmationMessage();}
    public static void setConfirmText(String text){
        View.setConfirmationMessage(text);
    }
    public static void printMessage(String message){
        View.console.setText(message + "\n");
    }
    public static void setDoneMessage(String text){
        View.setDoneConfirmation(text);
        View.getDoneConfirmation();
    }
}
