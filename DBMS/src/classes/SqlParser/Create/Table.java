package classes.SqlParser.Create;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import classes.DBNode;
import classes.DateNode;
import classes.FloatNode;
import classes.HeadImp;
import classes.IntegerNode;
import classes.StringNode;
import classes.SqlParser.sqlFactoryImp;

public class Table extends Create {

    String tableName;
    
    public Table(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> create() throws Exception {
        this.pattern = Pattern.compile(Messages.getString("Table.0")); //$NON-NLS-1$
        this.matcher = pattern.matcher(input);
        if (matcher.find()) {
            tableName = matcher.group(1);
            if(tableName.equalsIgnoreCase(Messages.getString("Table.1"))) //$NON-NLS-1$
                throw new SQLException();
            if(tableName.equalsIgnoreCase(Messages.getString("Table.2"))) //$NON-NLS-1$
                throw new SQLException();
            ArrayList<DBNode> nodes = ToNodes(matcher.group(2));
            try {
                sqlFactoryImp.head.MakeTable(dBName, tableName, nodes);
            } catch (Exception e) {
                throw new RuntimeException(Messages.getString("Table.3")); //$NON-NLS-1$
            }
        } else {
            throw new RuntimeException(Messages.getString("Table.4")); //$NON-NLS-1$
        }
        return LastReturn(tableName);
    }

    private ArrayList<DBNode> ToNodes(String group) {
        String[] node = group.split(Messages.getString("Table.5")); //$NON-NLS-1$
        String[] nodes;
        ArrayList<DBNode> AllNodes = new ArrayList<>();
        for (String x : node) {
            nodes = x.split(Messages.getString("Table.6")); //$NON-NLS-1$
            DBNode z;
            if (nodes[1].trim().equals(Messages.getString("Table.7"))) //$NON-NLS-1$
                z = new IntegerNode(nodes[0]);
            else if (nodes[1].trim().equals(Messages.getString("Table.8"))) //$NON-NLS-1$
                z = new StringNode(nodes[0]);
            else if (nodes[1].trim().equals(Messages.getString("Table.9"))) //$NON-NLS-1$
                z = new FloatNode(nodes[0]);
            else if (nodes[1].trim().equals(Messages.getString("Table.10"))) //$NON-NLS-1$
                z = new DateNode(nodes[0]);
            else
                throw new RuntimeException(Messages.getString("Table.11")); //$NON-NLS-1$
            AllNodes.add(z);
        }
        return AllNodes;
    }
}