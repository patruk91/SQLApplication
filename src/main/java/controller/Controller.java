package controller;

import dao.ApplicantDao;
import dao.HandleTablesInDatabaseDao;
import dao.MentorDao;
import dao.SQL.ApplicantDaoSQL;
import dao.SQL.HandleTablesInDatabaseDaoSQL;
import dao.SQL.MentorDaoSQL;
import model.Applicant;
import model.Mentor;
import textView.reader.Reader;
import textView.validator.Validator;
import textView.view.View;

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
    private HandleTablesInDatabaseDao handleTablesInDatabaseDao = new HandleTablesInDatabaseDaoSQL(url, user, password);

    public void run() {
        handleTablesInDatabaseDao.createData();
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
                    displayUpdateMenu();
                    break;
                case 4:
                    displayDeleteMenu();
                    break;
                case 5:
                    exitApp = true;
                    handleTablesInDatabaseDao.removeTables();
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
                "5. Display all mentors data\n" +
                "6. Display all applicants data\n" +
                "7. Back to menu\n";

        while (!backToMenu) {
            view.displayMenu(showMenu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 7);
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
                    List<String[]> allMentors = mentorDao.getAllMentors();
                    view.displayTable(new String[]{"Id", "First Name", "Last Name", "Nick name", "Phone Number", "Email", "City", "Favourite number"},
                            allMentors.toArray(new String[0][0]));
                    break;
                case 6:
                    List<String[]> allApplicants = applicantDao.getAllApplicants();
                    view.displayTable(new String[]{"Id", "First Name", "Last Name", "Phone Number", "Email", "Application code"},
                            allApplicants.toArray(new String[0][0]));
                    break;
                case 7:
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
                "2. Add mentor\n" +
                "3. Add applicant\n" +
                "4. Back to menu\n";

        while (!backToMenu) {
            view.displayMenu(showMenu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 4);
            switch (option) {
                case 1:
                    applicantDao.insertApplicantMarkus();
                    List<String[]> applicantMarkus = applicantDao.displayApplicantMarkus();;
                    view.displayTable(new String[]{"Id", "First name", "Last name", "Phone Number", "Email", "Application Code"},
                            applicantMarkus.toArray(new String[0][0]));
                    break;
                case 2:
                    Mentor newMentor = createNewMentor();
                    mentorDao.addNewMentor(newMentor);
                    break;
                case 3:
                    Applicant applicant = createNewApplicant();
                    applicantDao.addNewApplicant(applicant);
                    break;
                case 4:
                    backToMenu = true;
                    break;
                default:
                    view.displayMessage("No option available!");
            }
        }
    }

    private Mentor createNewMentor() {
        String firstName = getString("First name:");
        String lastName = getString("Last name:");
        String nickName = getString("Nick name:");
        String phoneNumber = getString("Phone number:");
        String email = getString("Email:");
        String city = getString("City:");
        int favouriteNumber = getNumber("Favourite number:");
        Mentor.MentorBuilder mentorBuilder = new Mentor.MentorBuilder();
        mentorBuilder
                .setFirstName(firstName)
                .setLastName(lastName)
                .setNickName(nickName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setCity(city)
                .setFavouriteNumber(favouriteNumber);
        return mentorBuilder.build();
    }



    private Applicant createNewApplicant() {
        String firstName = getString("First name:");
        String lastName = getString("Last name:");
        String phoneNumber = getString("Phone number:");
        String email = getString("Email:");
        int applicationCode = getNumber("Application code:");
        Applicant.ApplicantBuilder applicantBuilder = new Applicant.ApplicantBuilder();
        applicantBuilder
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhoneNumber(phoneNumber)
                .setEmail(email)
                .setApplicationCode(applicationCode);
        return applicantBuilder.build();
    }

    private String getString(String s) {
        view.displayMessage(s);
        return reader.getNotEmptyString();
    }

    private int getNumber(String s) {
        view.displayMessage(s);
        return reader.getNumberInRange(Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private void displayUpdateMenu() {
        boolean backToMenu = false;
        String showMenu = "Update menu:\n" +
                "1. Update phone number for Jemima Foreman\n" +
                "2. Back to menu\n";

        while (!backToMenu) {
            view.displayMenu(showMenu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 5);
            switch (option) {
                case 1:
                    applicantDao.updatePhoneNumberForJemimaForeman();
                    List<String[]> applicantMarkus = applicantDao.displayApplicantJemima();;
                    view.displayTable(new String[]{"Id", "First name", "Last name", "Phone Number", "Email", "Application Code"},
                            applicantMarkus.toArray(new String[0][0]));
                    break;
                case 2:
                    backToMenu = true;
                    break;
                default:
                    view.displayMessage("No option available!");
            }
        }
    }

    private void displayDeleteMenu() {
        boolean backToMenu = false;
        String showMenu = "Delete menu:\n" +
                "1. Delete applicants who applied from domain: 'mauriseu.net'\n" +
                "2. Back to menu\n";

        while (!backToMenu) {
            view.displayMenu(showMenu);
            view.displayQuestion("Choose menu option");
            int option = reader.getNumberInRange(1, 5);
            switch (option) {
                case 1:
                    applicantDao.deleteApplicantFromDomainMaurisuNet();
                    break;
                case 2:
                    backToMenu = true;
                    break;
                default:
                    view.displayMessage("No option available!");
            }
        }
    }
}
