package classes.SqlParser.Insert;

import java.util.ArrayList;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class InsertAll extends Insert {

    String tableName;
    
    public InsertAll(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> insert() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("InsertAll.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            tableName = matcher.group(1);
            String firstValue = firstValue(matcher.group(7));//LOGMEONTOCseD#19PLEASE
            String[] xx = { Messages.getString("InsertAll.1") }; //$NON-NLS-1$
            String[] restValues;
            if (matcher.group(10) != null) {
                restValues = divideCommaVal(matcher.group(10));
            } else {
                restValues = xx;
            }
            if (checkInsert(tableName, firstValue, restValues)) {
                insertIntoTable(tableName, firstValue, restValues);
            } else
                throw new RuntimeException(Messages.getString("InsertAll.2")); //$NON-NLS-1$
            
            return LastReturn(tableName);
        } else
            throw new RuntimeException(Messages.getString("InsertAll.3")); //$NON-NLS-1$
    }

    private void insertIntoTable(String tableName, String firstValue, String[] restValues) throws Exception {
        ArrayList<Object> ob = new ArrayList<>();
        ob.add(convertToType(firstValue));
        if (!(restValues.length == 1 && restValues[0].equals(Messages.getString("InsertAll.4")))) { //$NON-NLS-1$
            for (int i = 1; i < restValues.length; i++) {
                ob.add(convertToType(restValues[i]));
            }
        }
        sqlFactoryImp.head.insertIntoTable(ob, dBName, tableName);
    }
}
