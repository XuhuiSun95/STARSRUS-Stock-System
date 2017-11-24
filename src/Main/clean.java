package Main;

import java.io.*;
import java.sql.*;

public class clean {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/xuhui_sunDB";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "950802cherry";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            //  Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //  Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            //  Execute a query
            System.out.println("Deleting table in given database...");
            stmt = conn.createStatement();

            //  clean data
            try (BufferedReader br = new BufferedReader(new FileReader("src/data/clean.data"))) {
                String sql;
                while ((sql = br.readLine()) != null) {
                   // process the line.
                    stmt.executeUpdate(sql);
                }
            }
            System.out.println("Table  deleted in given database...");

            //  Clean-up environment
            stmt.close();
            conn.close();
        }
        catch (SQLException se) {
            //  Handle errors for JDBC
            se.printStackTrace();
        }
        catch (Exception e) {
            //  Handle errors for Class.forName
            e.printStackTrace();
        }
        finally {
            //  finally block used to close resources
            try {
                if(stmt!=null)
                    stmt.close();
            }
            catch (SQLException se2) {
            }// nothing we can do
            try {
                if(conn!=null)
                    conn.close();
            }
            catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
