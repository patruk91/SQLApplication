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
}
