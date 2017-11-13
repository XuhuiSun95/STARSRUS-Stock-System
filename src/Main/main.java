package Main;

import java.sql.*;

public class main {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/xuhui_sunDB";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "password";

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
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";
            ResultSet rs = stmt.executeQuery(sql);

            //  Extract data from result set
            while(rs.next()) {
                //  Retrieve by column name
                int id  = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                //  Display values
                System.out.print("ID: " + id);
                System.out.print(", Age: " + age);
                System.out.print(", First: " + first);
                System.out.println(", Last: " + last);
            }
            //  Clean-up environment
            rs.close();
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
