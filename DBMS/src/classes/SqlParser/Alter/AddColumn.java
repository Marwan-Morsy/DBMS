package classes.SqlParser.Alter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.DBNode;
import classes.DateNode;
import classes.FileSystemFactory;
import classes.FloatNode;
import classes.IntegerNode;
import classes.StringNode;
import classes.TabelImp;
import interfaces.WriterInterface;

public class AddColumn extends Alter{
    String tableName,columnName;
    String type;
    public AddColumn(String input) {
        super(input);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<ArrayList<String>> alter() throws Exception {
        // TODO Auto-generated method stub
        Pattern alterPattern = Pattern.compile(
                Messages.getString("AddColumn.0")); //$NON-NLS-1$
        Matcher alterMatcher = alterPattern.matcher(input);
        if (alterMatcher.find()) {
            tableName = alterMatcher.group(1);
            columnName = alterMatcher.group(2) == null ? alterMatcher.group(3) : alterMatcher.group(2);
            type = alterMatcher.group(4);
            try {
                TabelImp table = parser.read(tableName);
                DBNode column = makeColumn(type, columnName);
                ArrayList<DBNode> columns = makeColumns(table, column);
                TabelImp updated = updateNewTable(columns);
                parser.write(updated);
                return LastReturn(tableName);
            } catch (Exception e) {
                throw new RuntimeException(Messages.getString("AddColumn.1")); //$NON-NLS-1$
            }
        } else
            throw new RuntimeException(Messages.getString("AddColumn.2")); //$NON-NLS-1$
    }
    
    private DBNode makeColumn(String type,String name){
        if(type.toLowerCase().equals(Messages.getString("AddColumn.3"))) //$NON-NLS-1$
            return  new IntegerNode(name);
        else if(type.toLowerCase().equals(Messages.getString("AddColumn.4"))) //$NON-NLS-1$
            return  new StringNode(name);
        else if (type.toLowerCase().equals(Messages.getString("AddColumn.5"))) //$NON-NLS-1$
            return new DateNode(name);
        else if (type.toLowerCase().equals(Messages.getString("AddColumn.6"))) //$NON-NLS-1$
            return new FloatNode(name);
        else
            throw new RuntimeException(Messages.getString("AddColumn.7")); //$NON-NLS-1$
    }
    private ArrayList<DBNode> makeColumns (TabelImp table, DBNode x) throws Exception{
       
        ArrayList<DBNode> all= table.getTable() ;
        for(int i = 0 ; i < all.get(0).getColumn().size(); i++){
            x.getColumn().add(null);
        }
        all.add(x);
        return all;
    }
    private TabelImp updateNewTable(ArrayList<DBNode>all){
        TabelImp newTable = new TabelImp(tableName, all);
        return newTable;
    }
    
    
}