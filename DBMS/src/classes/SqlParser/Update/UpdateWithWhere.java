package classes.SqlParser.Update;

import java.util.ArrayList;
import java.util.regex.Pattern;

import classes.HeadImp;
import classes.SqlParser.sqlFactoryImp;

public class UpdateWithWhere extends Update {

    String tableName;
    int updateSize = 0;

    public UpdateWithWhere(String input) {
        super(input);
    }

    @Override
    public ArrayList<ArrayList<String>> update() throws Exception {
        pattern = Pattern.compile(
                Messages.getString("UpdateWithWhere.0")); //$NON-NLS-1$
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
                rows.add(Spliter[i].replaceAll(Messages.getString("UpdateWithWhere.1"), Messages.getString("UpdateWithWhere.2"))); //$NON-NLS-1$ //$NON-NLS-2$
            }
            UpdateCondition(Colms, rows, matcher.group(11), matcher.group(12), checkGroup13(matcher.group(13)));
            return LastReturn(tableName);
        } else {
            throw new RuntimeException(Messages.getString("UpdateWithWhere.3")); //$NON-NLS-1$
        }
    }

    private void UpdateCondition(ArrayList<String> colms, ArrayList<String> rows, String group, String group2, String group3) throws Exception {
        // TODO Auto-generated method stub
        ArrayList<ArrayList<String>> modifications = new ArrayList<>();
        for (int i = 0; i < colms.size(); i++) {
            ArrayList<String> node = new ArrayList<>();
            node.add(colms.get(i));
            node.add(rows.get(i));
            modifications.add(node);
        }
        ArrayList<Object> conditions = new ArrayList<>();
        conditions.add(group);
        conditions.add(group2);
        conditions.add(convertToType(group3));
        sqlFactoryImp.head.modify(dBName, tableName, modifications, conditions);
        updateSize = sqlFactoryImp.head.getUpdateSize();
    }
    
    private String checkGroup13(String x) {
        if (x.startsWith(Messages.getString("UpdateWithWhere.4"))){ //$NON-NLS-1$
            String y;
            y = x.substring(1, x.length()-1);
            return y;
        }
        return x;
    }
    
    public int getUpdateSize() {
        return updateSize;
    }
}
