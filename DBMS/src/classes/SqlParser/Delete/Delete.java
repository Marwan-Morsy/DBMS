package classes.SqlParser.Delete;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.SqlParser.Statements;

public abstract class Delete extends Statements {

    Pattern pattern;
    Matcher matcher;
    String input;
    
    public Delete(String input) {
        this.input = input;
    }
    public abstract ArrayList<ArrayList<String>> delete() throws Exception;
}
