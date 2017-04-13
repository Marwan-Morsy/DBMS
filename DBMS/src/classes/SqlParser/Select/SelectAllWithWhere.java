package classes.SqlParser.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.regex.Pattern;

import classes.FileSystemFactory;
import classes.TabelImp;
import classes.XMLParser;
import interfaces.WriterInterface;

public class SelectAllWithWhere extends Select {

    String tableName;

    public SelectAllWithWhere(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> select() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("SelectAllWithWhere.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        ArrayList<ArrayList<ArrayList<String>>> resu = new ArrayList<>();
        while (matcher.find()) {
            tableName = matcher.group(4);
            String colName = matcher.group(6);
            String condition = matcher.group(7);
            String rowName = (matcher.group(10) != null) ? matcher.group(10)
                    : (matcher.group(8) != null) ? matcher.group(8) : matcher.group(11);
            ArrayList<Integer> indexies = getIndexiesForWhere(tableName, colName, condition, rowName);
            TabelImp table = parser.read(tableName);
            if (condition.equals(Messages.getString("SelectAllWithWhere.1")) || condition.equals(Messages.getString("SelectAllWithWhere.2")) || condition.equals(Messages.getString("SelectAllWithWhere.3"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                ArrayList<ArrayList<String>> x = new ArrayList<>();
                for (int i = 0; i < table.GetTableSize(); i++) {
                    ArrayList<String> y = new ArrayList<>();
                    for (int j : indexies) {
                        if (table.getFromTable(i).getColumn().get(j) instanceof Date) {
                            y.add(new SimpleDateFormat(Messages.getString("SelectAllWithWhere.4")).format(table.getFromTable(i).getColumn().get(j))); //$NON-NLS-1$
                        } else {
                            y.add((table.getFromTable(i).getColumn().get(j) != null)
                                    ? table.getFromTable(i).getColumn().get(j).toString() : null);
                        }
                    }
                    x.add(y);
                }
                drawTable(table.getCoulmnNames(), x);
                setColumms(table.getCoulmnNames());
                if(matcher.group(11) != null) {
                    //there is order by
                    String []orderColumn = divideComma(matcher.group(12));
                    String retColum[] = new String[orderColumn.length];
                    String rettype[] = new String[orderColumn.length];
                    for(int i =0; i < orderColumn.length;i++) {
                        String [] test = dividespace(orderColumn[i]);
                        retColum[i] = test[0];
                        rettype[i] = test[1];
                    }
                    resu.add(orderBy(x,retColum,rettype));
                    continue;
                }
                resu.add(x);
            } else {
                throw new RuntimeException(Messages.getString("SelectAllWithWhere.5")); //$NON-NLS-1$
            }
        }
        if (resu.size() == 2) {
            if(resu.get(0).size() == resu.get(1).size() && resu.get(0).get(0).size() != 0 && resu.get(1).get(0).size() != 0){
                for (int i = 0; i < resu.get(0).size(); i++) {
                    if (convertToType(resu.get(0).get(i).get(0)) instanceof String && convertToType(resu.get(1).get(i).get(0)) instanceof String) {
                        
                    } else if (convertToType(resu.get(0).get(i).get(0)) instanceof Integer && convertToType(resu.get(1).get(i).get(0)) instanceof Integer) {
                        
                    }else if (convertToType(resu.get(0).get(i).get(0)) instanceof Float && convertToType(resu.get(1).get(i).get(0)) instanceof Float) {
                        
                    }else if (convertToType(resu.get(0).get(i).get(0)) instanceof Date && convertToType(resu.get(1).get(i).get(0)) instanceof Date) {
                        
                    } else {
                        throw new SQLException();
                    }
                }
                ArrayList<ArrayList<String>> conca = new ArrayList<>();
                conca.addAll(resu.get(0));
                for (int i = 0; i < resu.get(1).size(); i++) {
                    for (int j = 0; j < resu.get(1).get(0).size(); j++) {
                        conca.get(i).add(resu.get(1).get(i).get(j));
                    }
                }
               return ellimenate(conca);
            }
    } else if (resu.size() == 1) {
        return resu.get(0);
    } else {
        throw new RuntimeException();
    }
        throw new RuntimeException();
    }
}
