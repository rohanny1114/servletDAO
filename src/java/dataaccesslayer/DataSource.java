/*
Student Name: Rohan Kim
Student Number: 041070929
Course & Section #: 23S_CST8288_011
Declaration: This is my own original work and is free from Plagiarism.
*/
package dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class gets a connection to the database 
 * using JDBC URL, user name, and password.
 * 
 * @author Rohan Kim                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
 */
public class DataSource {

    private Connection connection = null;

    /**
     * Properties for initializing the JDBC connection 
     */
    private final String url = "jdbc:mysql://localhost:3306/peertutor?useSSL=false&allowPublicKeyRetrieval=true";
    private String username = "CST8288";
    private String password = "CST8288?";

    public DataSource() {
    }

  
    /**
     * This method creates a connection to database 
     * which only use one connection for this application, prevent memory leaks.
     * 
     * @return connection to DB
     */
    public Connection createConnection() {
        try {
            if (connection != null) {
                System.out.println("[SYSTEM]Cannot create new connection, one exists already");
            } else {
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return connection;
    }

}
