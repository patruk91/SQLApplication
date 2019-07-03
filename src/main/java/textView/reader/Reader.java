package textView.reader;

import textView.validator.Validator;
import textView.view.View;

import java.util.Scanner;

public class Reader {
    private Scanner scanner;
    private View view;
    private Validator validator;

    public Reader(View view, Validator validator) {
        this.scanner = new Scanner(System.in);
        this.view = view;
        this.validator = validator;
    }

    private String getInput() {
        return scanner.nextLine().trim();
    }

    public String getNotEmptyString() {
        String input = "";
        while (validator.isInputEmpty(input)) {
            input = getInput();
            if (validator.isInputEmpty(input)) {
                view.displayError("Please, provide not empty data");
            }
        }
        return input;
    }

    private String getNumberAsString() {
        String input = "";
        while (!validator.isNumber(input)) {
            input = getNotEmptyString();
            if (!validator.isNumber(input)) {
                view.displayError("Please, provide numeric data");
            }
        }
        return input;
    }

    public int getNumberInRange(int start, int end) {
        int number = Integer.parseInt(getNumberAsString());
        while (!validator.isInRange(number, start, end)) {
            view.displayError("Please, provide number in range: " + start + "-" + end);
            number = Integer.parseInt(getNumberAsString());
        }
        return number;
    }
}
