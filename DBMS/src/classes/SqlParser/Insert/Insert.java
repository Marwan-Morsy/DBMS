package classes.SqlParser.Insert;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.FileSystemFactory;
import classes.HeadImp;
import classes.TabelImp;
import classes.XMLParser;
import classes.SqlParser.Statements;
import interfaces.WriterInterface;

public abstract class Insert extends Statements {

    Pattern pattern;
    Matcher matcher;
    String input;
    
    public Insert(String input) {
        this.input = input;
    }
    
    public abstract ArrayList<ArrayList<String>> insert() throws Exception;

    protected String[] divideCommaVal(String group) {
        String x = group.replaceAll(Messages.getString("Insert.0"), Messages.getString("Insert.1")); //$NON-NLS-1$ //$NON-NLS-2$
        return x.split(Messages.getString("Insert.2")); //$NON-NLS-1$
    }

    protected String[] divideCommaColm(String group) {
        return group.split(Messages.getString("Insert.3")); //$NON-NLS-1$
    }
    
    protected boolean checkInsert(String tableName, String firstValue, String[] restValues)
            throws Exception {
        TabelImp x = parser.read(tableName);
        // Check Name
        if (x.getTable().size() == 0)
            return false;
        if (!(restValues.length == 1 && restValues[0].equals(Messages.getString("Insert.4")))) { //$NON-NLS-1$
            if (x.getTable().size() != restValues.length)
                return false;
            // Check Type
            for (int i = 1; i < x.GetTableSize(); i++) {
                // the Eroeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrr is
                // Hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
                /*
                 * if (isString(restValues[i]) == x.getTable().get(i).getType())
                 * return false;
                 */
            }
        }
        // the Eroeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrr is
        // Hereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
        /*
         * if (isString(firstValue) == x.getTable().get(0).getType()) return
         * false;
         */
        return true;
    }
    
    public String firstValue(String x) {
        if (x.startsWith(Messages.getString("Insert.5"))){ //$NON-NLS-1$
            String y;
            y = x.substring(1, x.length()-1);
            return y;
        }
        return x;
    }
}
