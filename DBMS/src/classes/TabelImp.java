package classes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TabelImp {

    private ArrayList<DBNode> Colomes;
    private Map<String, Integer> hm;
    private ArrayList<ArrayList<String>> rows;
    private String TableName;

    public TabelImp(String TableName, ArrayList<DBNode> table) {
        this.TableName = TableName;
        this.Colomes = table;
        this.hm = new HashMap<String, Integer>();
        this.rows = new ArrayList<>();
        if (table.size() > 0) {
            for (int i = 0; i < table.size(); i++) {
                hm.put(table.get(i).getName(), i);
            }
            for (int i = 0; i < table.get(0).getColumn().size(); i++) {
                ArrayList<String> tmp = new ArrayList<>();
                for (int j = 0; j < table.size(); j++) {
                    if (table.get(j).getColumn().get(i) != null)
                        tmp.add(table.get(j).getColumn().get(i).toString());
                    else {
                        tmp.add(null);
                    }
                }
                rows.add(tmp);
            }
        }
    }

    public ArrayList<DBNode> getTable() {
        return Colomes;
    }

    public DBNode getFromTable(int index) {
        return Colomes.get(index);
    }

    public boolean isIn(String item) {
        if (hm.containsKey(item)) {
            return true;
        }
        return false;
    }

    public int getIndex(String item) {
        return hm.get(item);
    }

    /**
     * Function that add rows
     * 
     * Array List will contain each element in row
     * 
     * ex row[0] = element in first Col
     * 
     * 
     **/
    public void insertIntoTable(ArrayList<String> row) {
        for (int i = 0; i < Colomes.size(); i++) {
            Colomes.get(i).getColumn().add(row.get(i));
        }
        rows.add(row);
    }

    /**
     * get row with index
     */
    public ArrayList<String> getRow(int index) {
        return rows.get(index);
    }

    public void SetinRow(int Rowindex, Object ColName, Object Value) {
        Colomes.get(getIndex((String)ColName)).getColumn().set(Rowindex, Value);
        rows.get(Rowindex).set(getIndex(ColName.toString()), Value.toString());
    }
    /**
     * 
     * Delete rows from Table let us consider this command : Delete Table_name
     * where Col_name = Value ;
     * 
     * @param indexOfCol
     *            : used to get the index of Col_name using hm of the condition
     * 
     * @param flag
     *            : used to determine the operation if flag = 1 then operation
     *            is = , else if flag = 2 operation is > else <
     * 
     * @param condition
     *            : this is the Value from the upper Sql expression
     * 
     */
    public void DeleteFromTable(int indexOfCol, int flag, String condition) {
        for (int i = 0; i < rows.size(); i++) {
            if (flag == 1) {
                if (condition.equals(rows.get(i).get(indexOfCol))) {
                    rows.remove(i);
                }
            } else if (flag == 2) {
                if (Integer.parseInt(condition) < Integer.parseInt(rows.get(i).get(indexOfCol))) {
                    rows.remove(i);
                }
            } else {
                if (Integer.parseInt(condition) > Integer.parseInt(rows.get(i).get(indexOfCol))) {
                    rows.remove(i);
                }
            }
        }
        Colomes.clear();
        for (int i = 0; i < rows.size(); i++) {
            insertIntoTable(rows.get(i));
        }
    }
    
    public String GetTableName() {
        return TableName;
    }
    
    public int GetTableSize() {
        return Colomes.size();
    }
    
    public int getNumberOfRows(){
        return rows.size();
    }
    
    public String[] getCoulmnNames() {
        String[] columns = new String[Colomes.size()];
        for (int i = 0; i < Colomes.size(); i++) {
            columns[i] = Colomes.get(i).getName();
        }
        return columns;
    }

}