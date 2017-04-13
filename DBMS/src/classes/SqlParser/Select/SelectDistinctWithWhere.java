package classes.SqlParser.Select;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class SelectDistinctWithWhere extends Select {

    String tableName;

    public SelectDistinctWithWhere(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> select() throws Exception {
        pattern = Pattern.compile(Messages.getString("SelectDistinctWithWhere.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        if (matcher.find()) {
            tableName = matcher.group(3);
            String colms = matcher.group(1);
            SelectColumnsWithWhere x = new SelectColumnsWithWhere(matcher.group(0).replaceAll(Messages.getString("SelectDistinctWithWhere.1"),Messages.getString("SelectDistinctWithWhere.2"))); //$NON-NLS-1$ //$NON-NLS-2$
            x.select();
            ArrayList<ArrayList<String>> columns = x.getSelected();
            ArrayList<ArrayList<String>> columnsEdited = ellimenate(columns);
            System.out.println(Messages.getString("SelectDistinctWithWhere.3")); //$NON-NLS-1$
            drawTable(divideComma(colms), columnsEdited);
            System.out.println();
            setColumms(divideComma(colms));
            return columnsEdited;
        } else {
            throw new RuntimeException(Messages.getString("SelectDistinctWithWhere.4")); //$NON-NLS-1$
        }
    }
}
