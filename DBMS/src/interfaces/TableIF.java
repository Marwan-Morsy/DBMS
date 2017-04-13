package interfaces;

import java.util.ArrayList;

import classes.DBNode;

public interface TableIF {
     /**
     * Function that add rows
     * 
     * Array List will contain each element in row
     * 
     * ex row[0] = element in first Col
     * 
     * 
     **/
    public void insertIntoTable(ArrayList<String> row);
    /**
     * get row with index
     */
    public ArrayList<String> getRow(int index);
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
    public void DeleteFromTable(int indexOfCol, int flag, String condition);
    /*
     * this function gets table name 
     */
    public String GetTableName() ;
    /*
     * this function gets table size 
     */
    public int GetTableSize() ;
    /*
     * this function gets the number of rows in the table
     */
    public int getNumberOfRows();
    /*
     * this function returns the table
     */
    public ArrayList<DBNode> getTable() ;
    /*
     * this function gets a node by its index 
     */
    public DBNode getFromTable(int index) ;

    public boolean isIn(String item) ;
    /*
     * this function gets the index of the value in the table by hash map 
     */
    public int getIndex(String item) ;
}
