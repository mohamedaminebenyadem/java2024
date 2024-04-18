/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectionDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnector {
    private String url = "jdbc:mysql://localhost:3306/LifeLinedb";
    private String login = "root";
    private String password ="";

    Connection cnx;
    private static DataBaseConnector instance;

    private DataBaseConnector(){
        try {
            cnx = DriverManager.getConnection(url, login, password);
            System.out.println("Connected to DB ! ");
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public Connection getCnx(){
        return cnx ;
    }
    public static DataBaseConnector getInstance() {

        if (instance == null){
            instance = new DataBaseConnector();
        }
        return instance;
    }




}

