package dao.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateNewDatabse {
    private String url;
    private String user;
    private String password;

    public CreateNewDatabse(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void createDatabase() {
        createMentorsTable();
        createApplicantsTable();
        insertStartedDataFromCSVToMentorsTable();
        insertStartedDataFromCSVToApplicatnsTable();
    }

    private void createMentorsTable() {
        String query = "CREATE TABLE IF NOT EXISTS mentors (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    first_name TEXT NOT NULL,\n" +
                "    last_name TEXT NOT NULL,\n" +
                "    nick_name TEXT NOT NULL,\n" +
                "    phone_number TEXT NOT NULL UNIQUE,\n" +
                "    email TEXT NOT NULL UNIQUE,\n" +
                "    city TEXT NOT NULL,\n" +
                "    favourite_number BIGINT\n" +
                ")";
        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }

    private void createApplicantsTable() {
        String query = "CREATE TABLE IF NOT EXISTS applicants (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    first_name TEXT NOT NULL,\n" +
                "    last_name TEXT NOT NULL,\n" +
                "    phone_number TEXT NOT NULL UNIQUE,\n" +
                "    email TEXT NOT NULL UNIQUE,\n" +
                "    application_code BIGINT\n" +
                ")";
        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }

    private void insertStartedDataFromCSVToMentorsTable() {
        String query = "COPY mentors(first_name, last_name, nick_name, phone_number, email, city, favourite_number)\n" +
                "FROM '/home/pl/IdeaProjects/WEB/PROJECTS/SQLApplication/documentation/mentors.csv'\n" +
                "NULL AS 'NULL' DELIMITER ',' QUOTE '''' CSV HEADER";

        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }

    private void insertStartedDataFromCSVToApplicatnsTable() {
        String query = "COPY applicants(first_name, last_name, phone_number, email, application_code)\n" +
                "FROM '/home/pl/IdeaProjects/WEB/PROJECTS/SQLApplication/documentation/applicants.csv'\n" +
                "DELIMITER ',' QUOTE '''' CSV HEADER";

        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }
}
