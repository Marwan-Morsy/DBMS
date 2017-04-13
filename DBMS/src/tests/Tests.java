package tests;
import static org.junit.Assert.*;

import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.Test;

import classes.SqlParser.sqlFactoryImp;


public class Tests {

    sqlFactoryImp tester = new sqlFactoryImp(Paths.get("").toAbsolutePath().toString(),"xmldb");

    @Test
    public void test0() throws Exception {

        assertEquals(null, tester.execute("CREATE database test;"));
        assertEquals(0,
                tester.execute(
                        "CREATE TABLE test.Persons (PersonID int(11),LastName varchar(255),FirstName varchar(255),Address varchar(255),City varchar(255));")
                        .size());

    }

    @Test
    public void test1() throws Exception {

        ArrayList<ArrayList<String>> tmp = tester.execute(
                "INSERT INTO test.Persons(PersonID, LastName, FirstName, Address, City)VALUES ('1','Tom B Erichsen','Skagen 21','Stavanger','Norway');");
        assertEquals(1, tmp.size());
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                assertEquals(getInsertCases(i, j), tmp.get(i).get(j));
            }
        }
        assertEquals(2,
                tester.execute(
                        "INSERT INTO test.Persons(PersonID, LastName, FirstName, Address, City)VALUES ('2','Mack','Skagen 1','Stavaer','Egypt');")
                        .size());
        assertEquals(3,
                tester.execute(
                        "INSERT INTO test.Persons(PersonID, LastName, FirstName, Address, City)VALUES ('3','Erichsen','Skagen','Stavang','Uk');")
                        .size());

    }
    @Test
    public void test2() throws Exception {
        assertEquals(2,
                tester.execute(
                        "Delete from test.Persons where PersonID = '3';")
                        .size());

    }
    @Test
    public void test3() throws Exception {
        assertEquals(2,
                tester.execute(
                        "select * from test.Persons;")
                        .size());
        ArrayList<ArrayList<String>> tmp = tester.execute("select * from test.Persons where PersonID = '1';");
        for(int j = 0 ; j < 5 ; j++){
            assertEquals(getInsertCases(0, j), tmp.get(0).get(j));
        }

    }
    @Test
    public void test4() throws Exception {
        ArrayList<ArrayList<String>> tmp = tester.execute("UPDATE test.Persons SET PersonID='50' WHERE City='Norway';");
        assertEquals("50", tmp.get(0).get(0));

    }

    @Test
    public void test5() throws Exception {

        assertEquals(null, tester.execute("drop table test.Persons;"));
        assertEquals(null, tester.execute("drop database test;"));

    }

    public String getInsertCases(int i, int j) {
        ArrayList<ArrayList<String>> test = new ArrayList<>();
        ArrayList<String> insideTest = new ArrayList<>();
        insideTest.add("1");
        insideTest.add("Tom B Erichsen");
        insideTest.add("Skagen 21");
        insideTest.add("Stavanger");
        insideTest.add("Norway");
        test.add(insideTest);
        return test.get(i).get(j);
    }

}
