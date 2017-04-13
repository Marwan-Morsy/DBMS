package classes.SqlParser.Update;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.SqlParser.Statements;

public abstract class Update extends Statements {

    Pattern pattern;
    Matcher matcher;
    String input;
    
    public Update(String input) {
        this.input = input;
    }
    
    public abstract ArrayList<ArrayList<String>> update() throws Exception;
    
    protected String DummyCheck(String group) {
        String x = group;
        Matcher m = Pattern.compile(Messages.getString("Update.0")).matcher(x); //$NON-NLS-1$
        while (m.find()) {
            x = x.replace(m.group(1), Messages.getString("Update.1") + m.group(1) + Messages.getString("Update.2")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return x;
    }
    
    protected String[] SplitUpdate(String group) {
        return group.split(Messages.getString("Update.3")); //$NON-NLS-1$
    }
}
