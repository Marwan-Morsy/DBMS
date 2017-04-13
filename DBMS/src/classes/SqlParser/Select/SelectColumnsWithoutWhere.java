package classes.SqlParser.Select;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.DBNode;
import classes.FileSystemFactory;
import classes.TabelImp;
import classes.XMLParser;
import interfaces.WriterInterface;

public class SelectColumnsWithoutWhere extends Select {

    String tableName;
    ArrayList<ArrayList<String>> y = new ArrayList<>();

    public SelectColumnsWithoutWhere(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> select() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("SelectColumnsWithoutWhere.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        ArrayList<ArrayList<ArrayList<String>>> resu = new ArrayList<>();
        while (matcher.find()) {
            String columns = matcher.group(1);
            String[] colms = divideComma(columns);
            tableName = matcher.group(4);
            TabelImp table = parser.read(tableName);
            for (String visit : colms) {
                if (table.isIn(visit)) {
                    ArrayList<String> z = new ArrayList<>();
                    DBNode elements = table.getFromTable(table.getIndex(visit));
                    for (int i = 0; i < elements.getColumn().size(); i++) {
                        if (elements.getColumn().get(i) instanceof Date) {
                            z.add(new SimpleDateFormat(Messages.getString("SelectColumnsWithoutWhere.1")).format(elements.getColumn().get(i))); //$NON-NLS-1$
                        } else {
                            z.add((elements.getColumn().get(i) != null) ? elements.getColumn().get(i).toString()
                                    : null);
                        }
                    }
                    y.add(z);
                } else
                    throw new RuntimeException(Messages.getString("SelectColumnsWithoutWhere.2") + visit + Messages.getString("SelectColumnsWithoutWhere.3")); //$NON-NLS-1$ //$NON-NLS-2$
            }
            drawTable(colms, y);
            setColumms(colms);
            if (matcher.group(11) != null) {
                // there is order by
                String[] orderColumn = divideComma(matcher.group(12));
                String retColum[] = new String[orderColumn.length];
                String rettype[] = new String[orderColumn.length];
                for (int i = 0; i < orderColumn.length; i++) {
                    String[] test = dividespace(orderColumn[i]);
                    retColum[i] = test[0];
                    rettype[i] = test[1];
                }
                resu.add(orderBy(y, retColum, rettype));
                continue;
            }
            resu.add(y);
        }
        if (resu.size() == 2) {
            if (resu.get(0).size() == resu.get(1).size() && resu.get(0).get(0).size() != 0
                    && resu.get(1).get(0).size() != 0) {
                for (int i = 0; i < resu.get(0).size(); i++) {
                    if (convertToType(resu.get(0).get(i).get(0)) instanceof String
                            && convertToType(resu.get(1).get(i).get(0)) instanceof String) {

                    } else if (convertToType(resu.get(0).get(i).get(0)) instanceof Integer
                            && convertToType(resu.get(1).get(i).get(0)) instanceof Integer) {

                    } else if (convertToType(resu.get(0).get(i).get(0)) instanceof Float
                            && convertToType(resu.get(1).get(i).get(0)) instanceof Float) {

                    } else if (convertToType(resu.get(0).get(i).get(0)) instanceof Date
                            && convertToType(resu.get(1).get(i).get(0)) instanceof Date) {

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

    public ArrayList<ArrayList<String>> getSelected() {
        return y;
    }
}