package dao.SQL;

import dao.HandleTablesInDatabaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HandleTablesInDatabaseDaoSQL implements HandleTablesInDatabaseDao {
    private String url;
    private String user;
    private String password;

    public HandleTablesInDatabaseDaoSQL(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void createData() {
        createTables();
        insertStartedDataFromCSVTables();
    }

    @Override
    public void removeTables() {
        String queryMentors = "DROP TABLE IF EXISTS mentors";
        String queryApplicants = "DROP TABLE IF EXISTS applicants";
        executeQuery(queryMentors, queryApplicants);
    }

    private void createTables() {
        String queryMentors = "CREATE TABLE IF NOT EXISTS mentors (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    first_name TEXT NOT NULL,\n" +
                "    last_name TEXT NOT NULL,\n" +
                "    nick_name TEXT NOT NULL,\n" +
                "    phone_number TEXT NOT NULL UNIQUE,\n" +
                "    email TEXT NOT NULL UNIQUE,\n" +
                "    city TEXT NOT NULL,\n" +
                "    favourite_number BIGINT\n" +
                ")";

        String queryApplicants = "CREATE TABLE IF NOT EXISTS applicants (\n" +
                "    id SERIAL PRIMARY KEY,\n" +
                "    first_name TEXT NOT NULL,\n" +
                "    last_name TEXT NOT NULL,\n" +
                "    phone_number TEXT NOT NULL UNIQUE,\n" +
                "    email TEXT NOT NULL UNIQUE,\n" +
                "    application_code BIGINT\n" +
                ")";

        executeQuery(queryMentors, queryApplicants);
    }

    private void insertStartedDataFromCSVTables() {
        String queryMentors = "COPY mentors(first_name, last_name, nick_name, phone_number, email, city, favourite_number)\n" +
                "FROM '/home/pl/IdeaProjects/WEB/PROJECTS/SQLApplication/documentation/mentors.csv'\n" +
                "NULL AS 'NULL' DELIMITER ',' QUOTE '''' CSV HEADER";

        String queryApplicants = "COPY applicants(first_name, last_name, phone_number, email, application_code)\n" +
                "FROM '/home/pl/IdeaProjects/WEB/PROJECTS/SQLApplication/documentation/applicants.csv'\n" +
                "DELIMITER ',' QUOTE '''' CSV HEADER";

        executeQuery(queryMentors, queryApplicants);
    }

    private void executeQuery(String queryMentors, String queryApplicants) {
        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmtMentors = connection.prepareStatement(queryMentors);
             PreparedStatement stmtApplicants = connection.prepareStatement(queryApplicants)) {
            stmtMentors.executeUpdate();
            stmtApplicants.executeUpdate();
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }


}
