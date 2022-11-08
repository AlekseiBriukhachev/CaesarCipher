package com.aleksei.task;


public class Controller {
    private View view;
    public Controller(View view) {
        this.view = view;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);

        view.setController(controller);
        view.init();
        try {
            Operation operation;
            do {
                operation = ConsoleHelper.askOperation();
                CommandExecutor.execute(operation);
            } while (operation != Operation.EXIT);
            ConsoleHelper.printExitMessage();
        } catch (Exception ignored) {
//            ConsoleHelper.writeMessage("Invalid entered data.");
            ConsoleHelper.printExitMessage();
            ignored.printStackTrace();
        }
        view.exit();
    }

}
