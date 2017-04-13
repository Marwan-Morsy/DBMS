package interfaces;

import java.util.ArrayList;

import classes.DBNode;

public interface HeadIF {
    /*
     * this function creates an empty database
     */
    public void MakeDB(final String Name);

    /*
     * this function deletes the selected database
     */
    public void DeleteDB(final String Name);

    /*
     * this function creates an empty table with only column name in it
     */
    public void MakeTable(final String Db, final String tableName, final ArrayList<DBNode> s) throws Exception;

    /*
     * this function makes the DTD file
     */
    public void makeDtd(final String tabelName, final String path);

    /*
     * this function deletes the selected
     */
    public void DeleteTable(final String Db, final String Name);

    /*
     * these functions are responsible for inserting into table
     */
    public void insertIntoTable(final ArrayList<Object> x, final int index, final String dBase, final String tableName)
            throws Exception;

    public void insertIntoTable(final ArrayList<Object> x, final String dBase, final String tableName) throws Exception;

    /*
     * these functions are responsible for deleting from table
     */
    public void deleteFromTable(final int z, final String dBase, final String tableName) throws Exception;

    public void deleteAll(final String dBase, final String tableName) throws Exception;

    /*
     * this funciton modifies the table and update it
     */
    public void modify(final String Db, final String Name, ArrayList<ArrayList<String>> wantedCol,
            ArrayList<Object> conditions) throws Exception;

    public ArrayList<DBNode> select(final String tableName, final String dBase, final ArrayList<String> wanted)
            throws Exception;

}
