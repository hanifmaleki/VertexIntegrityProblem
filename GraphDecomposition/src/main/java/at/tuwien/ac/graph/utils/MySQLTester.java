package at.tuwien.ac.graph.utils;


import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLTester
{
    /* the default framework is embedded */
    private String framework = "embedded";
    private String protocol = "jdbc:derby:";

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {

        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver).newInstance();

        DriverManager.getConnection("jdbc:mysql://localhost/backdoor?" +
                "user=root&password=root");


    }

}