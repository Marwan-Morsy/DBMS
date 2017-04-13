package classes.SqlParser;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.*;
import classes.SqlParser.Alter.*;
import classes.SqlParser.Create.Create;
import classes.SqlParser.Create.DataBase;
import classes.SqlParser.Create.Table;
import classes.SqlParser.Delete.*;
import classes.SqlParser.Drop.Drop;
import classes.SqlParser.Insert.Insert;
import classes.SqlParser.Insert.InsertAll;
import classes.SqlParser.Insert.InsertRow;
import classes.SqlParser.Select.*;
import classes.SqlParser.Update.*;
import interfaces.*;

public class sqlFactoryImp {

    private String Input;
    public static String DBName;
    private String TableName;
    public static HeadImp head;
    int changed = 0;
    String[] columnsSelected;
    ArrayList<ArrayList<String>> ret;
    
    public sqlFactoryImp (String path, String saveType) {
        head = new HeadImp(path, saveType);
    }
    public ArrayList<ArrayList<String>> execute(String Input) throws Exception {
        this.Input = Input;
        return Distributor();
    }

    public ArrayList<ArrayList<String>> Distributor() throws Exception {
        Pattern sele = Pattern.compile(
                Messages.getString("sqlFactoryImp.0")); //$NON-NLS-1$
        Matcher matcher = sele.matcher(Input.trim().replaceAll(Messages.getString("sqlFactoryImp.1"), Messages.getString("sqlFactoryImp.2"))); //$NON-NLS-1$ //$NON-NLS-2$
        if (matcher.find()) {
            Statements statements = null;
            if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.3"))) //$NON-NLS-1$
                return SelectMachine();
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.4"))) { //$NON-NLS-1$
                statements = new Table(Input);
                return ((Create) statements).create();
            }
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.5"))){ //$NON-NLS-1$
                statements = new DataBase(Input);
                return ((Create) statements).create();
            }
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.6"))){ //$NON-NLS-1$
                statements = new classes.SqlParser.Drop.Table(Input);
                return ((Drop) statements).drop();
            }
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.7"))){ //$NON-NLS-1$
                statements = new classes.SqlParser.Drop.DataBase(Input);
                return ((Drop) statements).drop();
            }
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.8"))){ //$NON-NLS-1$
                return DeleteMachine(statements);
            }
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.9"))){ //$NON-NLS-1$
                return UpdateMachine(statements);
            }
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.10"))) //$NON-NLS-1$
                return InsertMachine();
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.11"))) //$NON-NLS-1$
                return useMachine() ;
            else if (matcher.group(1).toLowerCase().equals(Messages.getString("sqlFactoryImp.12"))){ //$NON-NLS-1$
                return AlterMachine();
            }
            else
                throw new RuntimeException(Messages.getString("sqlFactoryImp.13")); //$NON-NLS-1$
        } else {
            throw new RuntimeException(Messages.getString("sqlFactoryImp.14")); //$NON-NLS-1$
        }
    }

    private ArrayList<ArrayList<String>> useMachine() {
        Pattern use = Pattern.compile(Messages.getString("sqlFactoryImp.15")); //$NON-NLS-1$
        Matcher matcher = use.matcher(Input);
        if (matcher.find()) {
            DBName = matcher.group(1);
        }
        return null;
    }
    
    public ArrayList<ArrayList<String>> AlterMachine() throws Exception {
        Pattern alterPattern = Pattern.compile(Messages.getString("sqlFactoryImp.16")); //$NON-NLS-1$
        Matcher alterMatcher = alterPattern.matcher(Input);
        if(alterMatcher.find()){
            TableName = alterMatcher.group(1);
            if(alterMatcher.group(4)==null){
                Statements statement = new AddColumn(Input);
                return ((Alter)statement).alter();
            }else {
                Statements statement = new DropColumn(Input);
                return ((Alter)statement).alter();
            }
        }
        else {
            throw new RuntimeException(Messages.getString("sqlFactoryImp.17")); //$NON-NLS-1$
        }
    }


    public ArrayList<ArrayList<String>> SelectMachine() throws Exception {
        Pattern selecCol = Pattern.compile(
                Messages.getString("sqlFactoryImp.18")); //$NON-NLS-1$
        Matcher matcherCol = selecCol.matcher(Input);
        Statements statements;
        if (matcherCol.find()) {
            TableName = matcherCol.group(4);
            String Columns = matcherCol.group(1);
            String[] colms = divideComma(Columns);
            if (matcherCol.group(5) != null) {
                if (colms.length == 1 && colms[0].equals(Messages.getString("sqlFactoryImp.19"))) { //$NON-NLS-1$
                    statements = new SelectAllWithWhere(Input);
                    ret = ((Select) statements).select();
                    columnsSelected = ((Select) statements).getColumms();
                    return ret;
                }else if (Columns.contains(Messages.getString("sqlFactoryImp.20")) || Columns.contains(Messages.getString("sqlFactoryImp.21"))) {  //$NON-NLS-1$ //$NON-NLS-2$
                    statements = new SelectDistinctWithWhere(Input);
                    ret = ((Select) statements).select();
                    columnsSelected = ((Select) statements).getColumms();
                    return ret;
                }else {
                    statements = new SelectColumnsWithWhere(Input);
                    ret = ((Select) statements).select();
                    columnsSelected = ((Select) statements).getColumms();
                    return ret;
                }
            } else {
                if (colms.length == 1 && colms[0].equals(Messages.getString("sqlFactoryImp.22"))) { //$NON-NLS-1$
                    statements = new SelectAllWithoutWhere(Input);
                    ret = ((Select) statements).select();
                    return ret;
                } else if (Columns.contains(Messages.getString("sqlFactoryImp.23")) || Columns.contains(Messages.getString("sqlFactoryImp.24"))) {  //$NON-NLS-1$ //$NON-NLS-2$
                    statements = new SelectDistinct(Input);
                    ret = ((Select) statements).select();
                    columnsSelected = ((Select) statements).getColumms();
                    return ret;
                } else {
                    statements = new SelectColumnsWithoutWhere(Input);
                    ret = ((Select) statements).select();
                    columnsSelected = ((Select) statements).getColumms();
                    return ret;
                }
            }
        } else
            throw new RuntimeException(Messages.getString("sqlFactoryImp.25")); //$NON-NLS-1$
    }

    private String[] divideComma(String columns) {
        // TODO Auto-generated method stub
        columns = columns.replaceAll(Messages.getString("sqlFactoryImp.26"), Messages.getString("sqlFactoryImp.27")); //$NON-NLS-1$ //$NON-NLS-2$
        return columns.split(Messages.getString("sqlFactoryImp.28")); //$NON-NLS-1$
    }

    public ArrayList<ArrayList<String>> DeleteMachine(Statements statements) throws Exception {
        // TODO Auto-generated method stub
        Pattern DeleteAll = Pattern.compile(Messages.getString("sqlFactoryImp.29")); //$NON-NLS-1$
        Matcher matcherAll = DeleteAll.matcher(Input);
        Pattern DeleteRow = Pattern.compile(
                Messages.getString("sqlFactoryImp.30")); //$NON-NLS-1$
        Matcher matcherRow = DeleteRow.matcher(Input);
        if (matcherRow.find()) {
            TableName = matcherRow.group(1);
            statements = new DeleteWithWhere(Input);
            ArrayList<ArrayList<String>> beforeDelete = (statements).LastReturn(TableName);
            ArrayList<ArrayList<String>> afterDelete =  ((Delete) statements).delete() ; 
            changed = (beforeDelete.get(0).size()-afterDelete.get(0).size());
            return afterDelete;
        } else if (matcherAll.find()) {
            TableName = matcherAll.group(1);
            statements = new DeleteAll(Input);
            ArrayList<ArrayList<String>> beforeDelete = ((Delete) statements).LastReturn(TableName);
            ArrayList<ArrayList<String>> afterDelete =  ((Delete) statements).delete() ; 
            changed = (beforeDelete.get(0).size()-afterDelete.get(0).size());
            return afterDelete;
            } else {
            throw new RuntimeException(Messages.getString("sqlFactoryImp.31")); //$NON-NLS-1$
        }
    }

    public ArrayList<ArrayList<String>> UpdateMachine(Statements statements) throws Exception {
        // TODO Auto-generated method stub
        Pattern Update = Pattern.compile(
                Messages.getString("sqlFactoryImp.32")); //$NON-NLS-1$
        Matcher Updatem = Update.matcher(Input);
        if (Updatem.find()) {
            TableName = Updatem.group(1);
            if (Updatem.group(10) != null) {
                // There is Where
                statements = new UpdateWithWhere(Input);
                ArrayList<ArrayList<String>> xx=((Update) statements).update();
                changed = (((UpdateWithWhere) statements).getUpdateSize());
                return xx;
            } else {
                // There isn't where
                statements = new UpdateAll(Input);
                ArrayList<ArrayList<String>> ans = statements.LastReturn(TableName);
                changed = (ans.get(0).size());
                return ((Update) statements).update();
            }
        } else {
            throw new RuntimeException(Messages.getString("sqlFactoryImp.33")); //$NON-NLS-1$
        }
    }

    public ArrayList<ArrayList<String>> InsertMachine() throws Exception {
        // TODO Auto-generated method stub
        Pattern FullInsert = Pattern.compile(
                Messages.getString("sqlFactoryImp.34")); //$NON-NLS-1$
        Matcher FullInsertm = FullInsert.matcher(Input);
        if (FullInsertm.find()) {
            //DBName = FullInsertm.group(1);
            TableName = FullInsertm.group(1);
            if (FullInsertm.group(2) != (null)) {
                Statements statements = new InsertRow(Input);
                ArrayList<ArrayList<String>> ans = ((Insert) statements).insert();
                changed = 1;
                return (ans);
            } else {
                Statements statements = new InsertAll(Input);
                ArrayList<ArrayList<String>> ans = ((Insert) statements).insert();
                changed = 1;
                return (ans);
                }
        } else {
            throw new RuntimeException(Messages.getString("sqlFactoryImp.35")); //$NON-NLS-1$
        }
    }

    public String[] Colms() throws Exception {
        Print(DBName);
        Print(TableName);
        WriterInterface parser = FileSystemFactory.getInstance().fileBuilder(head.formatType,head.Dir+DBName);
        TabelImp Table = parser.read(TableName);
        ArrayList<DBNode> x = Table.getTable();
        Print(x.toString());
        String[] Names = new String[x.size()];
        int i = 0;
        for (DBNode y : x) {
            Names[i] = y.getName();
            i++;
        }
        return Names;
    }
    
    public void Print(String x) {
        //System.out.println(x);
    }

    public TabelImp getTable() throws Exception {
        WriterInterface fileWriter = FileSystemFactory.getInstance().fileBuilder(head.formatType, head.Dir+DBName);
        TabelImp tableToUse = fileWriter.read(TableName);
        ArrayList<ArrayList<String>> rows = new ArrayList<>(ret);
        String[] colmnsNames = tableToUse.getCoulmnNames();
        if(colmnsNames.length == rows.size()){
            columnsSelected = colmnsNames;
        }
        ArrayList<DBNode> columns = convertToDBNodes(rows, colmnsNames, tableToUse);
        return new TabelImp(TableName, columns);
    }
    
    private ArrayList<DBNode> convertToDBNodes(ArrayList<ArrayList<String>> rows, String[] colmnsNames,
            TabelImp tableToUse) {
        ArrayList<DBNode> result = new ArrayList<>();
        for (int i = 0; i < rows.size(); i++) {
            LinkedList<Object> row = new LinkedList<>();
            for (int j = 0; j < rows.get(0).size(); j++) {
                row.add(convertToType(rows.get(i).get(j)));
            }
            DBNode res = null;
            if(!row.isEmpty()) {
            if(row.get(0) instanceof Integer) {
                res = new IntegerNode(columnsSelected[i]);
                res.setColumn(row);
            } else if (row.get(0) instanceof String) {
                res = new StringNode(columnsSelected[i]);
                res.setColumn(row);
            } else if (row.get(0) instanceof Float) {
                res = new FloatNode(columnsSelected[i]);
                res.setColumn(row);
            } else if (row.get(0) instanceof Date) {
                res = new DateNode(columnsSelected[i]);
                res.setColumn(row);
            }
            }
            result.add(res);
        }
        return result;
    }

    public int getChangedSize() {
        return changed;
    }
    
    protected Object convertToType(String value) {
        Object val = null;
        try {
            val = value;
        } catch (Exception e) {}
        try {
            val = Float.parseFloat(value);
        } catch (Exception e) {}
        try {
            val = Integer.parseInt(value);
        } catch (Exception e) {}
        try {
            DateFormat formatter = new SimpleDateFormat(Messages.getString("sqlFactoryImp.36")); //$NON-NLS-1$
            Date TTT = new Date(formatter.parse(value).getTime());
            val = TTT;
        } catch (Exception e) {}
        return val;
    }

}
// insert into Finall.Ezz values ('Dodo');
// delete from Final.Test where Name='Ahmed';
// insert into Final.Test2 (Name, ID) values ('Ahmed','5');
// Select * from Final.Test where Name='Ahmede';
// update Final.Test2 set ID=9 where Name='MAr';