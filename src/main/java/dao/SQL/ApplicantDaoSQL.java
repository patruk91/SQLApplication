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
        List<String[]> applicants = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection(url, user, password);
            PreparedStatement stmt = connection.prepareStatement(query)) {
            addApplicantCarol(stmt, applicants);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return applicants;
    }

    private void addApplicantCarol(PreparedStatement stmt, List<String[]> applicants) throws SQLException {
        ResultSet resultSet = stmt.executeQuery();
        int id = 1;
        while (resultSet.next()) {
            String fullName = resultSet.getString("full_name");
            String phoneNumber = resultSet.getString("phone_number");
            String[] mentorsNames = {String.valueOf(id), fullName, phoneNumber};
            applicants.add(mentorsNames);
            id++;
        }
    }
}
