package textView.view;


public class View {
    public void displayMenu(String menu) {
        System.out.println(menu);
    }

    public void displayMessage(String message) {
        System.out.print(message);
    }

    public void displayQuestion(String question) {
        System.out.print(question + "?: ");
    }

    public void displayError(String error) {
        System.out.println("Error: " + error + "!");
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void displayTable(String[] headers, String[][] data) {
        System.out.println(FlipTable.of(headers, data));

    }
}
