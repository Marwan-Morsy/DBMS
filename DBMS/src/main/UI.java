package main;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import classes.DrawTable;
import classes.SqlParser.sqlFactoryImp;

public class UI {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String path = Paths.get("").toAbsolutePath().toString();
        String type = "xmldb";
        sqlFactoryImp user = new sqlFactoryImp(path,type);
        ArrayList<ArrayList<String>> printFormat;
        System.out.println("Working.....");
        Scanner s = new Scanner(System.in).useDelimiter(";");
        while (true) {
            try {
                String input = s.next();
                printFormat = user.execute(input.replaceAll("[\r\n]+", " ") + ";");
                try {
                    String[] q = user.Colms();
                    DrawTable Table = new DrawTable(q, printFormat);
                    Table.DrawLine();
                    Table.Title();
                    Table.DrawLine();
                    Table.Columns();
                    Table.DrawLine();
                } catch (Exception e) {
                    Print("Plaaaaaaaaaaa");
                }
                s.nextLine();
            } catch (Exception e) {
                System.out.println("Error :");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    public static void Print(String x) {
        //System.out.println(x);
    }
}
// Upadte uni.persons set lastName='AAA SSS',City='ads' where firstName='Ali';
// Update uni.persons set LastName='dodo ezzat' , City='dodo' where
// delete * from x.b where Name='Do Ezzat';
// insert into a values ('Ahmed',1);
//create table A (Name varchar, ID int);
//Update Ahmed set grade=9.33 , Name='Ezza' where grade>55.36;
//insert into Ahmed values ('Ahmed',5,8.8);
//insert into Ezzat values ('Ahmed',05-06-2016);
//create Table S (Name varchar,date date);
//create table B (Name varchar,ID int,Salary float,Date date);
//insert into B values ('Ahmed',1,3.6,05-06-2016);
//insert into B (Name,ID) values ('Ezzat',2);
//Update B set salary=9.33 , Name='Ezza' where ID=2;