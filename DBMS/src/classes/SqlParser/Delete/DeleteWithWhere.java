package classes.SqlParser.Delete;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class DeleteWithWhere extends Delete {
    
    String tableName;
    
    public DeleteWithWhere(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> delete() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("DeleteWithWhere.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            tableName = matcher.group(1);
            String colmnName = matcher.group(2);
            Print(colmnName);
            String condition = matcher.group(3);
            Print(condition);
            String element = (matcher.group(6) != null) ? matcher.group(6) : (matcher.group(4) != null) ? matcher.group(4) : (matcher.group(7) != null) ? matcher.group(7) : matcher.group(8);
            Print(element);
            DeleteCondetion(getIndexiesForWhere(tableName, colmnName, condition, element), condition);
            return LastReturn(tableName);
        } else {
            throw new RuntimeException(Messages.getString("DeleteWithWhere.1")); //$NON-NLS-1$
        }
    }

    private void DeleteCondetion(ArrayList<Integer> rowindex, String condition) throws Exception {
        // TODO Auto-generated method stub
        if (rowindex.size() == 0)
            throw new RuntimeException(Messages.getString("DeleteWithWhere.2")); //$NON-NLS-1$
        if (condition.equals(Messages.getString("DeleteWithWhere.3"))) //$NON-NLS-1$
            for (int i = 0; i < rowindex.size(); i++) {
                sqlFactoryImp.head.deleteFromTable(rowindex.get(i) - i, dBName, tableName);
            }
        else if (condition.equals(Messages.getString("DeleteWithWhere.4"))) { //$NON-NLS-1$
            for (int i = 0; i < rowindex.size(); i++) {
                sqlFactoryImp.head.deleteFromTable(rowindex.get(i) - i, dBName, tableName);
            }
        } else if (condition.equals(Messages.getString("DeleteWithWhere.5"))) { //$NON-NLS-1$
            for (int i = 0; i < rowindex.size(); i++) {
                sqlFactoryImp.head.deleteFromTable(rowindex.get(i) - i, dBName, tableName);
            }
        } else {
            throw new RuntimeException(Messages.getString("DeleteWithWhere.6")); //$NON-NLS-1$
        }
    }
    
    public void Print(String x) {
        System.out.println(x);
    }
}
