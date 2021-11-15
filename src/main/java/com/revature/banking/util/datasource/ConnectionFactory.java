package com.revature.banking.util.datasource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
    singleton design pattern
        creation pattern
        restricts a class so that only a single instance of it can be made within an application
        constructor cannot be invoked outside of the class
        only one instance can be created with the app

    Factory design pattern
        creation pattern
        used to abstract away the creation/instantiation lofic of an object
 */
public class ConnectionFactory {

    private static final ConnectionFactory connectionFactory = new ConnectionFactory();
    private Properties props = new Properties();

    static {
        try {
            //forcibly load the Postgresql Driver into JVM memory so that it can create a connection.
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private ConnectionFactory() {
        try {
            props.load(new FileReader("src/main/resources/db.properties"));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static ConnectionFactory getInstance(){
        return connectionFactory;
    }

    public Connection getConnection() {
        Connection conn = null;

        try{
            conn = DriverManager.getConnection(props.getProperty("url"), props.getProperty("username"), props.getProperty("password"));

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
