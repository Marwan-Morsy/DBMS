package classes.SqlParser.Create;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.TabelImp;
import classes.XMLParser;
import classes.SqlParser.Statements;
import classes.SqlParser.sqlFactoryImp;

public abstract class Create extends Statements {

    Pattern pattern;
    Matcher matcher;
    String input;
    
    public Create(String input) {
        this.input = input;
    }
    public abstract ArrayList<ArrayList<String>> create() throws Exception;
}
