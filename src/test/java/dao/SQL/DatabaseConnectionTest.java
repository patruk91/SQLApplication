package dao.SQL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {
    private String url;
    private String user;
    private String password;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException{
        this.url = "jdbc:postgresql://localhost:5432/sqlapplication";
        this.user = "pl";
        this.password = "postgres";
        this.connection = DatabaseConnection.getConnection(url, user, password);
    }

    @Test
    void checkConnectionWithValidData() throws SQLException {
        assertTrue(connection.isValid(1));
    }

    @Test
    void checkIfConnectionIsNotNull() {
        assertNotNull(connection);
    }

    @Test
    void checkIfConnectionIsNotClosed() throws SQLException{
        assertFalse(connection.isClosed());
    }

    @Test
    void checkConnectionWithInvalidDatabaseURL() {
        String invalidURL = "";
        assertThrows(SQLException.class, () -> DatabaseConnection.getConnection(invalidURL, user, password));
    }

    @Test
    void checkConnectionWithInvalidUser() {
        String invalidUser = "";
        assertThrows(SQLException.class, () -> DatabaseConnection.getConnection(url, invalidUser, password));
    }

    @Test
    void checkConnectionWithInvalidPassword() {
        String invalidPassword = "";
        assertThrows(SQLException.class, () -> DatabaseConnection.getConnection(url, user, invalidPassword));
    }

    @AfterEach
    void closeConnectionAfterTest() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }



}