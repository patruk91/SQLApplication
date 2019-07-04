package dao.SQL;

import dao.ApplicantDao;
import model.Applicant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApplicantDaoSQL implements ApplicantDao {
    private String url;
    private String user;
    private String password;

    public ApplicantDaoSQL(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public List<String[]> getApplicantCarol() {
        String query = "SELECT first_name || ' ' || last_name " +
                "AS full_name, phone_number FROM applicants\n" +
                "WHERE first_name LIKE 'Carol';";
        return getApplicantsFullNameAndPhoneNumber(query);
    }

    @Override
    public List<String[]> getApplicantByIndicatedEmail() {
        String query = "SELECT first_name || ' ' || last_name " +
                "AS full_name, phone_number FROM applicants\n" +
                "WHERE email LIKE '%@adipiscingenimmi.edu'";
        return getApplicantsFullNameAndPhoneNumber(query);
    }

    private List<String[]> getApplicantsFullNameAndPhoneNumber(String query) {
        List<String[]> applicants = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            addApplicantsFullNameAndPhoneNumber(stmt, applicants);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return applicants;
    }

    private void addApplicantsFullNameAndPhoneNumber(PreparedStatement stmt, List<String[]> applicants) throws SQLException {
        ResultSet resultSet = stmt.executeQuery();
        int id = 1;
        while (resultSet.next()) {
            String fullName = resultSet.getString("full_name");
            String phoneNumber = resultSet.getString("phone_number");
            String[] applicant = {String.valueOf(id), fullName, phoneNumber};
            applicants.add(applicant);
            id++;
        }
    }

    @Override
    public void insertApplicantMarkus() {
        String query = "INSERT INTO applicants\n" +
                "(first_name, last_name, phone_number, email, application_code)\n" +
                "VALUES ('Markus', 'Schaffarzyk', '003620/725-2666', 'djnovus@groovecoverage.com', 54823)";

        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }

    @Override
    public List<String[]> displayApplicantMarkus() {
        String query = "SELECT first_name, last_name, phone_number, email, application_code FROM applicants\n" +
                "WHERE application_code = 54823;";
        List<String[]> applicants = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            addApplicantMarkus(stmt, applicants);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return applicants;
    }

    private void addApplicantMarkus(PreparedStatement stmt, List<String[]> applicants) throws SQLException {
        ResultSet resultSet = stmt.executeQuery();
        int id = 1;
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String phoneNumber = resultSet.getString("phone_number");
            String email = resultSet.getString("email");
            String applicationCode = resultSet.getString("application_code");
            String[] mentorsNames = {String.valueOf(id), firstName, lastName, phoneNumber, email, applicationCode};
            applicants.add(mentorsNames);
            id++;
        }
    }


}
