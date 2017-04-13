package classes.SqlParser.Drop;

import java.util.ArrayList;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class Table extends Drop {

    String tableName;
    
    public Table(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> drop() throws Exception {
        pattern = Pattern.compile(Messages.getString("Table.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            tableName = matcher.group(1);
        }
        if (dBName != null && tableName != null) {
            try {
                sqlFactoryImp.head.DeleteTable(dBName, tableName);
            } catch (Exception e) {
                throw new RuntimeException(Messages.getString("Table.1")); //$NON-NLS-1$
            }
        }
        return null;
    }
}
