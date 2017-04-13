package classes.SqlParser.Select;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SelectDistinct extends Select {

    String tableName;

    public SelectDistinct(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> select() throws Exception {
        pattern = Pattern.compile(Messages.getString("SelectDistinct.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            tableName = matcher.group(3);
            String colms = matcher.group(1);
            SelectColumnsWithoutWhere x = new SelectColumnsWithoutWhere(matcher.group(0).replaceAll(Messages.getString("SelectDistinct.1"),Messages.getString("SelectDistinct.2"))); //$NON-NLS-1$ //$NON-NLS-2$
            x.select();
            ArrayList<ArrayList<String>> columns = x.getSelected();
            ArrayList<ArrayList<String>> columnsEdited = ellimenate(columns);
            System.out.println(Messages.getString("SelectDistinct.3")); //$NON-NLS-1$
            drawTable(divideComma(colms), columnsEdited);
            System.out.println();
            setColumms(divideComma(colms));
            return columnsEdited;
        } else {
            throw new RuntimeException(Messages.getString("SelectDistinct.4")); //$NON-NLS-1$
        }
    }

    protected ArrayList<ArrayList<String>> ellimenate(ArrayList<ArrayList<String>> columns) {
        ArrayList<ArrayList<String>> result = columns;
        for (int rows = 0; rows < result.get(0).size(); rows++) {
            String test = result.get(0).get(rows);
            for (int i = rows+1; i < result.get(0).size(); i++) {
                if (result.get(0).get(i).equals(test)) {
                    boolean flag = true;
                    for (int colms = 1; colms < result.size(); colms++) {
                        if (!result.get(colms).get(rows).equals(result.get(colms).get(i))) {
                            flag = false;
                            break;
                        }
                    }
                    if(flag) {
                        for (int colms = 0; colms < result.size(); colms++) {
                            result.get(colms).remove(i);
                        }
                        i--;
                    }
                }
            }
        }
        return result;
    }
}
