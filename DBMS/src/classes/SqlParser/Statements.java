package classes.SqlParser;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;

import classes.DrawTable;
import classes.FileSystemFactory;
import classes.HeadImp;
import classes.TabelImp;
import interfaces.IsDate;
import interfaces.IsFloat;
import interfaces.IsInteger;
import interfaces.IsString;
import interfaces.WriterInterface;

public class Statements {

    protected String dBName = sqlFactoryImp.DBName;
    protected int changes;
    protected String input;
    protected WriterInterface parser = FileSystemFactory.getInstance().fileBuilder(sqlFactoryImp.head.formatType, sqlFactoryImp.head.Dir + dBName);
    
    protected ArrayList<Integer> getIndexiesForWhere(String tableName, String colName, String condition, String rowName)
            throws Exception {
        ArrayList<Integer> indexies = new ArrayList<>();
        TabelImp table = parser.read(tableName);
        int colIndex = table.getIndex(colName);
        LinkedList<Object> rows = table.getFromTable(colIndex).getColumn();
        for (int i = 0; i < rows.size(); i++) {
            if (condition.equals(Messages.getString("Statements.0"))) { //$NON-NLS-1$
                indexies = equalWhere(rows, colIndex, i, indexies, table, rowName);
            } else if (condition.equals(Messages.getString("Statements.1"))) { //$NON-NLS-1$
                indexies = lessWhere(rows, colIndex, i, indexies, table, rowName);
            } else if (condition.equals(Messages.getString("Statements.2"))) { //$NON-NLS-1$
                indexies = greaterWhere(rows, colIndex, i, indexies, table, rowName);
            } else {
                throw new RuntimeException(Messages.getString("Statements.3")); //$NON-NLS-1$
            }
        }
        return indexies;
    }

    private ArrayList<Integer> greaterWhere(LinkedList<Object> rows, int colIndex, int i, ArrayList<Integer> indexies,
            TabelImp table, String rowName) throws ParseException {
        if (rows.get(i) != null) {
            if (table.getFromTable(colIndex) instanceof IsString) {
                if (rows.get(i).equals(rowName))
                    if (rows.get(i).toString().compareTo(rowName) > 0)
                        indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsInteger) {
                if (Integer.parseInt(rows.get(i).toString()) > (Integer.parseInt(rowName)))
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsFloat) {
                if (Float.parseFloat(rowName + Messages.getString("Statements.4")) < Float.parseFloat(rows.get(i).toString() + Messages.getString("Statements.5"))) //$NON-NLS-1$ //$NON-NLS-2$
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsDate) {
                DateFormat formatter = new SimpleDateFormat(Messages.getString("Statements.6")); //$NON-NLS-1$
                Date date = (Date)rows.get(i);
                Date date2 = new Date(formatter.parse(rowName).getTime());
                if (date.after(date2)) {
                    indexies.add(i);
                }
            }
        }
        return indexies;
    }

    private ArrayList<Integer> lessWhere(LinkedList<Object> rows, int colIndex, int i, ArrayList<Integer> indexies,
            TabelImp table, String rowName) throws ParseException {
        if (rows.get(i) != null) {
            if (table.getFromTable(colIndex) instanceof IsString) {
                    if (rows.get(i).toString().compareTo(rowName) < 0)
                        indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsInteger) {
                if (Integer.parseInt(rows.get(i).toString()) < (Integer.parseInt(rowName)))
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsFloat) {
                if (Float.parseFloat(rowName + Messages.getString("Statements.7")) > Float.parseFloat(rows.get(i).toString() + Messages.getString("Statements.8"))) //$NON-NLS-1$ //$NON-NLS-2$
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsDate) {
                DateFormat formatter = new SimpleDateFormat(Messages.getString("Statements.9")); //$NON-NLS-1$
                Date date = (Date) rows.get(i);
                Date date2 = new Date(formatter.parse(rowName).getTime());
                if (date.before(date2)) {
                    indexies.add(i);
                }
            }
        }
        return indexies;
    }

    private ArrayList<Integer> equalWhere(LinkedList<Object> rows, int colIndex, int i, ArrayList<Integer> indexies,
            TabelImp table, String rowName) throws ParseException {
        if (rows.get(i) != null) {
            if (table.getFromTable(colIndex) instanceof IsString) {
                if (rows.get(i).equals(rowName))
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsInteger) {
                if ((Integer.parseInt(rows.get(i).toString())) == (Integer.parseInt(rowName)))
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsFloat) {
                if (Float.parseFloat(rowName + Messages.getString("Statements.10")) == Float.parseFloat(rows.get(i).toString() + Messages.getString("Statements.11"))) //$NON-NLS-1$ //$NON-NLS-2$
                    indexies.add(i);
            } else if (table.getFromTable(colIndex) instanceof IsDate) {
                DateFormat formatter = new SimpleDateFormat(Messages.getString("Statements.12")); //$NON-NLS-1$
                Date date = (Date) rows.get(i);
                Date date2 = new Date(formatter.parse(rowName).getTime());
                if (date.equals(date2)) {
                    indexies.add(i);
                }
            }
        }
        return indexies;
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
            DateFormat formatter = new SimpleDateFormat(Messages.getString("Statements.13")); //$NON-NLS-1$
            Date TTT = new Date(formatter.parse(value).getTime());
            val = TTT;
        } catch (Exception e) {}
        return val;
    }

    protected ArrayList<ArrayList<String>> LastReturn(String tableName) throws Exception {
        TabelImp table = parser.read(tableName);
        ArrayList<ArrayList<String>> x = new ArrayList<>();
        for (int i = 0; i < table.GetTableSize(); i++) {
            ArrayList<String> y = new ArrayList<>();
            for (int j = 0; j < table.getFromTable(i).getColumn().size(); j++) {
                if (table.getFromTable(i).getColumn().get(j) instanceof Date) {
                    y.add(new SimpleDateFormat(Messages.getString("Statements.14")).format(table.getFromTable(i).getColumn().get(j))); //$NON-NLS-1$
                } else {
                    y.add((table.getFromTable(i).getColumn().get(j) != null)
                            ? table.getFromTable(i).getColumn().get(j).toString() : null);
                }
            }
            x.add(y);
        }
        return x;
    }

    protected void drawTable(String[] colms, ArrayList<ArrayList<String>> y) {
        DrawTable Draw = new DrawTable(colms, y);
        Draw.DrawLine();
        Draw.Title();
        Draw.DrawLine();
        Draw.Columns();
        Draw.DrawLine();
    }

    public void Print(String x) {
        // System.out.println(x);
    }
}
