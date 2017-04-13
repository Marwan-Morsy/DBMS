package classes.SqlParser.Select;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import classes.FileSystemFactory;
import classes.TabelImp;
import interfaces.WriterInterface;

public class SelectAllWithoutWhere extends Select {

    String tableName;

    public SelectAllWithoutWhere(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> select() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("SelectAllWithoutWhere.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        ArrayList<ArrayList<ArrayList<String>>> resu = new ArrayList<>();
        while (matcher.find()) {
            tableName = matcher.group(4);
            TabelImp table = parser.read(tableName);
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
                resu.add(orderBy(LastReturn(tableName),retColum,rettype));
                continue;
            }
            resu.add(LastReturn(tableName));
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
