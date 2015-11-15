/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author Katangoori
 */
public class DataBaseConnection {
    private static Connection connection=null;
    private static Statement statement=null;
    
    public static Connection getDataBaseConnection() {
        try {
            if(connection==null) {
                Class.forName("com.mysql.jdbc.Driver");
                //jdbc:mysql://mysql36161-ssist.whelastic.net/testdatabase?user=root&password=root
                //jdbc:mysql://localhost:3306/testdatabase?user=root&password=root
                connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=root&password=root");
            }
            if(connection.isClosed()) {
                Class.forName("com.mysql.jdbc.Driver");
                //jdbc:mysql://mysql36161-ssist.whelastic.net/testdatabase?user=root&password=root
                //jdbc:mysql://localhost:3306/testdatabase?user=root&password=root
                connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=root&password=root");
            }
            else {
                Class.forName("com.mysql.jdbc.Driver");
                //jdbc:mysql://mysql36161-ssist.whelastic.net/testdatabase?user=root&password=root
                //jdbc:mysql://localhost:3306/testdatabase?user=root&password=root
                connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdatabase?user=root&password=root");
            }
            return connection;
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
    
    public static Statement getStatement() {
        try {
            if(statement==null) {
                statement=DataBaseConnection.getDataBaseConnection().createStatement();
            }
            else {
                return statement;
            }
        }
        catch(Exception e) {
            System.err.println(e);
        }
        return statement;
    }
}
