package controller;

import dao.ApplicantDao;
import dao.MentorDao;
import dao.SQL.ApplicantDaoSQL;
import dao.SQL.MentorDaoSQL;
import model.Mentor;
import textView.reader.Reader;
import textView.validator.Validator;
import textView.view.FlipTable;
import textView.view.View;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private View view = new View();
    private Validator validator = new Validator();
    private Reader reader = new Reader(view, validator);

    private String url = "jdbc:postgresql://localhost:5432/sqlapplication";
    private String user = "pl";
    private String password = "postgres";
    private MentorDao mentorDao = new MentorDaoSQL(url, user, password);
    private ApplicantDao applicantDao = new ApplicantDaoSQL(url, user, password);

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
                    displayMenu();
                    break;
                case 2:
                    displayAddMenu();
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


    private void displayMenu() {
        boolean backToMenu = false;
        String showMenu = "Display menu:\n" +
                "1. Display first and last name for mentors\n" +
                "2. Display mentors by nicknames\n" +
                "3. Display applicant by name: Carol\n" +
                "4. Display applicant by email: '@adipiscingenimmi.edu'\n" +
                "5. Back to menu\n";

        while (!backToMenu) {
            view.displayMenu(showMenu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 5);
            switch (option) {
                case 1:
                    List<String[]> mentorsNames = mentorDao.getMentorsFirstAndLastName();
                    view.displayTable(new String[]{"Id", "First name", "Last name" },
                            mentorsNames.toArray(new String[0][0]));
                    break;
                case 2:
                    List<String[]> mentorsNickNames = mentorDao.getMentorsNickNames();
                    view.displayTable(new String[]{"Id", "Nick name"},
                            mentorsNickNames.toArray(new String[0][0]));
                    break;
                case 3:
                    List<String[]> applicantCarol = applicantDao.getApplicantCarol();
                    view.displayTable(new String[]{"Id", "Nick name", "Phone Number"},
                            applicantCarol.toArray(new String[0][0]));
                    break;
                case 4:
                    List<String[]> applicantByIndicatedEmail = applicantDao.getApplicantByIndicatedEmail();
                    view.displayTable(new String[]{"Id", "Nick name", "Phone Number"},
                            applicantByIndicatedEmail.toArray(new String[0][0]));
                    break;
                case 5:
                    backToMenu = true;
                    break;
                default:
                    view.displayMessage("No option available!");
            }
        }
    }


    private void displayAddMenu() {
        boolean backToMenu = false;
        String showMenu = "Add menu:\n" +
                "1. Add Markus Schaffarzyk\n" +
                "2. temp\n" +
                "3. temp\n" +
                "4. temp'\n" +
                "5. Back to menu\n";

        while (!backToMenu) {
            view.displayMenu(showMenu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 5);
            switch (option) {
                case 1:
                    applicantDao.insertApplicantMarkus();
                    List<String[]> applicantMarkus = applicantDao.displayApplicantMarkus();;
                    view.displayTable(new String[]{"Id", "First name", "Last name", "Phone Number", "Email", "Application Code"},
                            applicantMarkus.toArray(new String[0][0]));
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    backToMenu = true;
                    break;
                default:
                    view.displayMessage("No option available!");
            }
        }
    }
}
