package mobiiliSovellus;

import java.sql.*;

public class Sovellus {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Step 1: Load and register the JDBC driver
            Class.forName("org.sqlite.JDBC");

            // Step 2: Establish a connection to the SQLite database
            connection = DriverManager.getConnection("jdbc:sqlite:data/database.db");

            // Step 3: Create a statement
            statement = connection.createStatement();

            // Step 4: Create a table (if not exists)
            String createTableQuery = "CREATE TABLE IF NOT EXISTS users (id INTEGER PRIMARY KEY, name TEXT, email TEXT)";
            statement.executeUpdate(createTableQuery);

            // Step 5: Insert data into the table
            String insertQuery = "INSERT INTO users (name, email) VALUES ('John Doe', 'john@example.com')";
            statement.executeUpdate(insertQuery);

            // Step 6: Retrieve data from the table
            String selectQuery = "SELECT * FROM users";
            ResultSet resultSet = statement.executeQuery(selectQuery);

            // Step 7: Process the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);
                System.out.println("------------------------");
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load JDBC driver.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Database connection error.");
            e.printStackTrace();
        } finally {
            // Step 8: Close the resources
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close database resources.");
                e.printStackTrace();
            }
        }
    }
}
