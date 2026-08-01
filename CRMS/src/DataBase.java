import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DataBase extends Thread
{
    private static final String URL = "jdbc:mysql://localhost:3306/c103";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private static Connection con = null;
    private static boolean driverLoaded = false;
    public static boolean conStatus=testConnection();

    Scanner sc=new Scanner(System.in);

    // Load JDBC Driver once
    static
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            driverLoaded = true;
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("[WARNING] MySQL JDBC Driver not found in classpath. SQL features will be disabled.");
        }
    }

    public static Connection getConnection() throws SQLException
    {
        if (!driverLoaded)
        {
            throw new SQLException("JDBC Driver was not loaded.");
        }

        // Return active connection or create a new one if closed
        if (con == null || con.isClosed())
        {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return con;
    }

    public static boolean testConnection()
    {
        try
        {
            Connection conn = getConnection();
            return conn != null && !conn.isClosed();
        }
        catch (SQLException e)
        {
            return false;
        }
    }

    public static void closeConnection()
    {
        if (con != null)
        {
            try
            {
                if (!con.isClosed())
                {
                    con.close();
                }
            }
            catch (SQLException e)
            {
                System.err.println("[ERROR] Failed to close database connection: " + e.getMessage());
            }
        }
    }

    void DBSync()
    {
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("|              DATABASE & SYSTEM CONFIGURATION MANAGEMENT              |");
        System.out.println("+----------------------------------------------------------------------+");
        System.out.println();

        if(testConnection())
        {
            System.out.println("Status: ONLINE MODE (Connected to MySQL Database)");
            System.out.println("Connection String: jdbc:mysql://localhost:3306/c103");
            System.out.println();
        }
        else
        {
            System.out.println("Status: OFFLINE MODE (In-memory simulation active)");
            System.out.println("Crime Record Management System could not connect to XAMPP MySQL Database at start...");
            System.out.println("Make sure XAMPP Control Panel is running and MySQL service is started....");
            System.out.println();

            System.out.print("Do you want to test and establish MySQL connection now???");
            char conAns=sc.next().charAt(0);

            if (conAns=='y' || conAns=='Y')
            {
                System.out.println("Attempting to connect to jdbc:mysql://localhost:3306/c103...");
                if (testConnection())
                {
                    System.out.println("Database connection established! System synchronized to live database.");
                    System.out.println("System reconnected and synchronized with live MySQL DB");
                }
                else
                {
                    System.out.println("Reconnection failed. Staying in Offline Mode.");
                }
            }
        }
    }

    public DataBase()
    {
        conStatus=testConnection();
    }

    @Override
    public void run()
    {
        while(true)
        {
            Status();
        }
    }

    void Status()
    {
        while (true)
        {
            try
            {
                Thread.sleep(60000); // Check every 60 seconds
                if (testConnection())
                {
                    if (!conStatus)
                    {
                        System.out.println("[INFO] Database connection restored.");
                        conStatus = true;
                    }
                }
                else
                {
                    if (conStatus)
                    {
                        System.out.println("[WARNING] Database connection lost.");
                        conStatus = false;
                    }
                }
            }
            catch (InterruptedException e)
            {
                System.err.println("[ERROR] DBSync thread interrupted: " + e.getMessage());
            }
        }
    }
}
