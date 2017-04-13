package classes.SqlParser.Create;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class DataBase extends Create {

    public DataBase(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> create() throws Exception {
        Pattern createDB = Pattern.compile(Messages.getString("DataBase.0")); //$NON-NLS-1$
        Matcher matcherCreateDB = createDB.matcher(input);
        if (matcherCreateDB.find()) {
            String DBNameNotGlobal = matcherCreateDB.group(1);
            sqlFactoryImp.head.MakeDB(DBNameNotGlobal);
        }
        return null;
    }

}
