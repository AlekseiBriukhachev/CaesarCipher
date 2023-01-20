package com.aleksei.mvc;


import com.aleksei.mvc.View;

public class ReaderWriter {
    public String readDialogMessage(){
        return View.getDialogMessage();
    }
    public void setDialogText(String text){
        View.setDialogMessage(text);
    }
    public int readConfirmationMessage(){return View.getConfirmationMessage();}
    public void setConfirmText(String text){
        View.setConfirmationMessage(text);
    }
    public void printMessage(String message){
        View.console.setText(message + "\n");
    }
    public void setDoneMessage(String text){
        View.setDoneConfirmation(text);
        View.getDoneConfirmation();
    }
}
