package classes.SqlParser.Drop;

import java.util.ArrayList;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class DataBase extends Drop {

    public DataBase(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> drop() throws Exception {
        pattern = Pattern.compile(Messages.getString("DataBase.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            String DBName = matcher.group(1);
            if (DBName != null) {
                try {
                    sqlFactoryImp.head.DeleteDB(DBName);
                } catch (Exception e) {
                    throw new RuntimeException(Messages.getString("DataBase.1")); //$NON-NLS-1$
                }
            }
        } else 
            throw new RuntimeException(Messages.getString("DataBase.2")); //$NON-NLS-1$
        return null;
    }

}
