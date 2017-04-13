package classes.SqlParser.Alter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.SqlParser.Statements;

public abstract class Alter extends Statements {
    Pattern pattern;
    Matcher matcher;
    String input;
    public Alter(String input) {
        this.input = input;
        
    }
    public abstract ArrayList<ArrayList<String>> alter()  throws Exception;

}