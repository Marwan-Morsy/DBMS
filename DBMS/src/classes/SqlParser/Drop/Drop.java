package classes.SqlParser.Drop;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.SqlParser.Statements;

public abstract class Drop extends Statements {

    Pattern pattern;
    Matcher matcher;
    String input;
    
    public Drop(String input) {
        this.input = input;
    }
    public abstract ArrayList<ArrayList<String>> drop() throws Exception;
}
