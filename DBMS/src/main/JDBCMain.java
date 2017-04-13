package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import classes.DrawTable;
import eg.edu.alexu.csd.oop.jdbc.JavaDriver;
import eg.edu.alexu.csd.oop.jdbc.JavaResultset;

public class JDBCMain {

private Properties configProp = new Properties();

    public void loadProps() throws FileNotFoundException {
        InputStream in = new FileInputStream("Resources/messages.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws SQLException, FileNotFoundException {
        // TODO Auto-generated method stub
        new JDBCMain().loadProps();
        Scanner input = new Scanner(System.in);
        Driver driver = new JavaDriver();
        ResultSet result ;
        while (true) {
            System.out.println("Enter URL :");
            String url = input.nextLine();
            //String url ="jdbc:" + "xmldb" + "://localhost";
            if (driver.acceptsURL(url)) {
                System.out.println("Enter Path:");
                String path = input.nextLine();
                //String path = "C:\\Users\\Ahmed Maghawry\\AppData\\Local\\Temp\\Dodo";
                //C:\Users\Ahmed Maghawry\AppData\Local\Temp\Dodo3
                Properties info = new Properties();
                File dbDir = new File(path);
                info.put("path", dbDir.getAbsoluteFile());
                Connection connection = driver.connect(url, info);
                Statement statment = connection.createStatement();
                while (true) {
                    try {
                        System.out.print("sql>");
                        String tmp = input.nextLine();
                        if(statment.execute(tmp)){
                            result = statment.getResultSet();
                            ArrayList<ArrayList<String>> output = ((JavaResultset) result).getArrayList();
                            //new DrawTable( , output);
                        }
                    } catch (Exception e) {
                        System.out.println("Error");
                    }
                }

            }
        }
    }
}
