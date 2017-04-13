package ourJunit;

import static org.junit.Assert.*;


import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

import eg.edu.alexu.csd.oop.jdbc.JavaConnection;
import eg.edu.alexu.csd.oop.jdbc.JavaDriver;
import tests.TestRunner;

public class ConnenctionTest {
    JavaDriver driver = new JavaDriver();
    private String protocol = "xmldb";
    private String tmp = System.getProperty("java.io.tmpdir");

    private Connection createUseDatabase(String databaseName) throws SQLException {
        Properties info = new Properties();
        File dbDir = new File(tmp + "/jdbc/" + Math.round((((float) Math.random()) * 100000)));
        info.put("path", dbDir.getAbsoluteFile());

        Connection connection = driver.connect("jdbc:" + protocol + "://localhost", info); // Establish
                                                                                            // connection
                                                                                            // (really,
                                                                                            // just
                                                                                            // make
                                                                                            // sure
        // that the dbDir exists, and create it if it
        // doesn't), and just record the protocol.

        Statement statement = connection.createStatement(); // create a
                                                            // statement object
                                                            // to execute next
                                                            // statements.

        statement.execute("CREATE DATABASE " + databaseName); // you should now
                                                                // create a
                                                                // folder for
                                                                // that database
                                                                // within dbDir.

        statement.execute("USE " + databaseName); // Set the state of your
                                                    // connection to use
                                                    // "databaseName", all next
                                                    // created statements
        // (like selects and inserts) should be applied to this database.
        statement.close();
        return connection;
    }
    @Test //
    public void testCreateTable() throws SQLException {
        Connection connection = createUseDatabase("university");
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE student (Name varchar, ID int, BirthDate date)");
            statement.close();
        } catch (Throwable e) {
            Assert.fail("Create invalid table succeed");
        }
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE incomplete_table_name1");
            Assert.fail("Create invalid table succeed");
        } catch (SQLException e) {
        } catch (Throwable e) {
            TestRunner.fail("Invalid Exception thrown", e);
        }
        connection.close();
    }
        
    @Test //
    public void testConditionalUpdate() throws SQLException {
        Connection connection = createUseDatabase("university");
        try {
            Statement statement = connection.createStatement();
            statement.execute(
                    "CREATE TABLE table_name8(column_name1 varchar, column_name2 int, column_name3 date, column_name4 float)");

            int count1 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, column_name4) VALUES ('value1', 2011-01-25, 3, 1.3)");
            Assert.assertEquals("Insert returned a number != 1", 1, count1);

            int count2 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, column_name4) VALUES ('value1', 2011-01-28, 3456, 1.01)");
            Assert.assertEquals("Insert returned a number != 1", 1, count2);

            int count3 = statement.executeUpdate(
                    "INSERT INTO table_name8(column_NAME1, COLUMN_name3, column_name2, column_name4) VALUES ('value2', 2011-02-11, -123, 3.14159)");
            Assert.assertEquals("Insert returned a number != 1", 1, count3);

            int count4 = statement.executeUpdate(
                    "UPDATE table_name8 SET COLUMN_NAME2=222222, column_name3=1993-10-03 WHERE coLUmn_NAME1='value1'");
            Assert.assertEquals("Updated returned wrong number", count1 + count2, count4);

            statement.close();
        } catch (Throwable e) {
            TestRunner.fail("Failed to update table", e);
        }
        connection.close();
    }

}