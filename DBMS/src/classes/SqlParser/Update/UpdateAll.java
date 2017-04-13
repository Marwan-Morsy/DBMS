package classes.SqlParser.Update;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class UpdateAll extends Update{
    
    String tableName;
    
    public UpdateAll(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> update() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("UpdateAll.0")); //$NON-NLS-1$
        matcher = pattern.matcher(input);
        ArrayList<String> Colms = new ArrayList<>();
        ArrayList<String> rows = new ArrayList<>();
        if (matcher.find()) {
            tableName = matcher.group(1);
            String[] Spliter = SplitUpdate(DummyCheck(matcher.group(2)));
            for (int i = 0; i < Spliter.length; i += 2) {
                Colms.add(Spliter[i]);
            }
            for (int i = 1; i < Spliter.length; i += 2) {
                rows.add(Spliter[i].replaceAll(Messages.getString("UpdateAll.1"), Messages.getString("UpdateAll.2"))); //$NON-NLS-1$ //$NON-NLS-2$
            }
            UpdateIt(Colms, rows);
            return LastReturn(tableName);
        } else
            throw new RuntimeException(Messages.getString("UpdateAll.3")); //$NON-NLS-1$
    }
    
    private void UpdateIt(ArrayList<String> colms, ArrayList<String> rows)
            throws Exception {
        ArrayList<ArrayList<String>> modifications = new ArrayList<>();
        for (int i = 0; i < colms.size(); i++) {
            ArrayList<String> node = new ArrayList<>();
            node.add(colms.get(i));
            node.add(rows.get(i));
            modifications.add(node);
        }
        sqlFactoryImp.head.modify(dBName, tableName, modifications, null);
    }
}
