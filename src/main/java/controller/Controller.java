package controller;

import textView.reader.Reader;
import textView.validator.Validator;
import textView.view.View;

public class Controller {
    private View view = new View();
    private Validator validator = new Validator();
    private Reader reader = new Reader(view, validator);

    public void run() {
        boolean exitApp = false;
        String menu = "Main menu:\n" +
                "1. Display...\n" +
                "2. Add...\n" +
                "3. Update...\n" +
                "4. Delete...\n" +
                "5. Exit...\n";

        while (!exitApp) {
            view.displayMenu(menu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 5);
            switch(option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exitApp = true;
                    break;
                default:
                    view.displayError("No option available");
                    break;
            }
        }
    }
}
