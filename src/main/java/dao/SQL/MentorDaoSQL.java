package dao.SQL;

import dao.MentorDao;
import model.Mentor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MentorDaoSQL implements MentorDao {
    private String url;
    private String user;
    private String password;

    public MentorDaoSQL(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public List<String[]> getMentorsFirstAndLastName() {
        String query = "SELECT first_name, last_name FROM mentors";
        List<String[]> mentors = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection(url, user, password);
            PreparedStatement stmt = connection.prepareStatement(query)) {
            addMentorsNames(stmt, mentors);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return mentors;
    }

    private void addMentorsNames(PreparedStatement stmt, List<String[]> mentors) throws SQLException {
        ResultSet resultSet = stmt.executeQuery();
        int id = 1;
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String[] mentorsNames = {String.valueOf(id), firstName, lastName};
            mentors.add(mentorsNames);
            id++;
        }
    }

    @Override
    public List<String[]> getMentorsNickNames() {
        String query = "SELECT nick_name FROM mentors";
        List<String[]> mentors = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection(url, user, password);
            PreparedStatement stmt = connection.prepareStatement(query)) {
            addMentorsNickNames(stmt, mentors);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return mentors;
    }

    private void addMentorsNickNames(PreparedStatement stmt, List<String[]> mentors) throws SQLException {
        ResultSet resultSet = stmt.executeQuery();
        int id = 1;
        while (resultSet.next()) {
            String nickName = resultSet.getString("nick_name");
            String[] mentorsNames = {String.valueOf(id), nickName};
            mentors.add(mentorsNames);
            id++;
        }
    }

    @Override
    public List<String[]> getAllMentors() {
        String query = "SELECT * FROM mentors";
        return getMentorsData(query);
    }

    private List<String[]> getMentorsData(String query) {
        List<String[]> applicants = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            addMentors(stmt, applicants);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
        return applicants;
    }

    private void addMentors(PreparedStatement stmt, List<String[]> applicants) throws SQLException {
        ResultSet resultSet = stmt.executeQuery();
        int id = 1;
        while (resultSet.next()) {
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String nickName = resultSet.getString("nick_name");
            String phoneNumber = resultSet.getString("phone_number");
            String email = resultSet.getString("email");
            String city = resultSet.getString("city");
            int num = resultSet.getInt("favourite_number");
            String favouriteNumber = num != 0 ? String.valueOf(num) : "null";
            String[] applicant = {String.valueOf(id), firstName, lastName, nickName, phoneNumber, email, city, favouriteNumber};
            applicants.add(applicant);
            id++;
        }
    }

    @Override
    public void addNewMentor(Mentor newMentor) {
        String query = "INSERT INTO mentors\n" +
                "(first_name, last_name, nick_name, phone_number, email, city, favourite_number)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection(url, user, password);
             PreparedStatement stmt = connection.prepareStatement(query)) {
            insertMentorData(stmt, newMentor);
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage()
                    + "\nSQLState: " + e.getSQLState()
                    + "\nVendorError: " + e.getErrorCode());
        }
    }

    private void insertMentorData(PreparedStatement stmt, Mentor newMentor) throws SQLException {
        stmt.setString(1, newMentor.getFirstName());
        stmt.setString(2, newMentor.getLastName());
        stmt.setString(3, newMentor.getNickName());
        stmt.setString(4, newMentor.getPhoneNumber());
        stmt.setString(5, newMentor.getEmail());
        stmt.setString(6, newMentor.getCity());
        stmt.setInt(7, newMentor.getFavouriteNumber());
        stmt.executeUpdate();

    }
}
