package main;

import java.awt.CheckboxGroup;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import classes.TabelImp;

public class Dummy {
public static void main(String[] args) throws Exception {
    String[]columms = {"Ezzat","Ahmed","Dodo","sfd"};
    String[]ordercol = {"Dodo","Ezzat"};
    ArrayList<String> a = new ArrayList<>();
    ArrayList<String> b = new ArrayList<>();
    ArrayList<String> c = new ArrayList<>();
    a.add("19");
    a.add("2");
    a.add("4");
    a.add("4");
    b.add("6");
    b.add("8");
    b.add("1");
    b.add("1");
    c.add("6");
    c.add("2");
    c.add("17");
    c.add("17");
    ArrayList<ArrayList<String>> ss = new ArrayList<>();
    ss.add(a);
    ss.add(b);
    ss.add(c);
    System.out.println(orderBy(columms, ss, ordercol, "desc").toString());
}

protected static ArrayList<ArrayList<String>> orderBy(String[] columms, ArrayList<ArrayList<String>> rows, String[] orderCol, String type) {
    int[] index = new int[orderCol.length];
    for (int i = 0; i < orderCol.length; i++) {
        for (int j = 0; j < columms.length; j++) {
            if(orderCol[i].equals(columms[j])) {
                index[i] = j;
                break;
            }
        }
    }
    String[][] rowEdit = new String[rows.size()][rows.get(0).size()];
    for(int i = 0; i < rows.size(); i++) {
        for (int j = 0; j < rows.get(0).size(); j++) {
            rowEdit[i][j] = rows.get(i).get(j);
        }
    }
    for (int i = 0; i < orderCol.length; i++) {
        for (int j = 0; j < columms.length; j++) {
            if(orderCol[i].equals(columms[j])) {
                index[i] = j;
                break;
            }
        }
    }
    for(int i = orderCol.length - 1; i >= 0; i--) {
        if(type.equals("asc"))
        rowEdit = sortItASC(rowEdit,index[i]);
        else if(type.equals("desc"))
            rowEdit = sortItDESC(rowEdit,index[i]);
    }
    ArrayList<ArrayList<String>> returnIt = new ArrayList<>();
    for(int i = 0; i < rows.size(); i++) {
        ArrayList<String> test = new ArrayList();
        for (int j = 0; j < rows.get(0).size(); j++) {
            test.add(rowEdit[i][j]);
        }
        returnIt.add(test);
    }
    return returnIt;
}

    private static String[][] sortItASC(String[][] rowEdit, int x) {
        Object[][] doIt = new Object[rowEdit.length][rowEdit[0].length];
        int n = rowEdit.length;
        String temp;
        for(int i = 0; i < rowEdit.length; i++) {
            for (int j = 0; j < rowEdit[0].length; j++) {
                doIt[i][j] = convertToType(rowEdit[i][j]);
                System.out.print(rowEdit[i][j]+"  ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if(doIt[j-1][x] instanceof String) {
                if (((String)doIt[j-1][x]).compareTo((String)doIt[j][x]) > 0) {
                    // swap the elements!
                    for(int t = 0; t < n; t++) {
                        temp = rowEdit[j-1][t];
                        rowEdit[j-1][t] = rowEdit[j][t];
                        rowEdit[j][t] = temp;
                        Object temp2 = doIt[j-1][t];
                        doIt[j-1][t] = doIt[j][t];
                        doIt[j][t] = temp2;
                    }
                }
                } else if (doIt[j-1][x] instanceof Integer) {
                    if ((Integer)doIt[j-1][x] > ((Integer)doIt[j][x])) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[j-1][t];
                            rowEdit[j-1][t] = rowEdit[j][t];
                            rowEdit[j][t] = temp;
                            Object temp2 = doIt[j-1][t];
                            doIt[j-1][t] = doIt[j][t];
                            doIt[j][t] = temp2;
                        }
                    }
                } else if (doIt[j-1][x] instanceof Float) {
                    if ((Float)doIt[j-1][x] > ((Float)doIt[j][x])) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[j-1][t];
                            rowEdit[j-1][t] = rowEdit[j][t];
                            rowEdit[j][t] = temp;
                            Object temp2 = doIt[j-1][t];
                            doIt[j-1][t] = doIt[j][t];
                            doIt[j][t] = temp2;
                        }
                    }
                } else if (doIt[j-1][x] instanceof Date) {
                    if (((Date)doIt[j-1][x]).after((Date)doIt[j][x])) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[j-1][t];
                            rowEdit[j-1][t] = rowEdit[j][t];
                            rowEdit[j][t] = temp;
                            Object temp2 = doIt[j-1][t];
                            doIt[j-1][t] = doIt[j][t];
                            doIt[j][t] = temp2;
                        }
                    }
                }
            }
        }
        return rowEdit;
    }
    
    private static String[][] sortItDESC(String[][] rowEdit, int x) {
        Object[][] doIt = new Object[rowEdit.length][rowEdit[0].length];
        int n = rowEdit.length;
        String temp;
        for(int i = 0; i < rowEdit.length; i++) {
            for (int j = 0; j < rowEdit[0].length; j++) {
                doIt[i][j] = convertToType(rowEdit[i][j]);
                System.out.print(rowEdit[i][j]+"  ");
            }
            System.out.println();
        }
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < (n - i); j++) {
                if(doIt[j-1][x] instanceof String) {
                if (((String)doIt[j-1][x]).compareTo((String)doIt[j][x]) < 0) {
                    // swap the elements!
                    for(int t = 0; t < n; t++) {
                        temp = rowEdit[j-1][t];
                        rowEdit[j-1][t] = rowEdit[j][t];
                        rowEdit[j][t] = temp;
                        Object temp2 = doIt[j-1][t];
                        doIt[j-1][t] = doIt[j][t];
                        doIt[j][t] = temp2;
                    }
                }
                } else if (doIt[j-1][x] instanceof Integer) {
                    if ((Integer)doIt[j-1][x] < ((Integer)doIt[j][x])) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[j-1][t];
                            rowEdit[j-1][t] = rowEdit[j][t];
                            rowEdit[j][t] = temp;
                            Object temp2 = doIt[j-1][t];
                            doIt[j-1][t] = doIt[j][t];
                            doIt[j][t] = temp2;
                        }
                    }
                } else if (doIt[j-1][x] instanceof Float) {
                    if ((Float)doIt[j-1][x] < ((Float)doIt[j][x])) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[j-1][t];
                            rowEdit[j-1][t] = rowEdit[j][t];
                            rowEdit[j][t] = temp;
                            Object temp2 = doIt[j-1][t];
                            doIt[j-1][t] = doIt[j][t];
                            doIt[j][t] = temp2;
                        }
                    }
                } else if (doIt[j-1][x] instanceof Date) {
                    if (((Date)doIt[j-1][x]).before((Date)doIt[j][x])) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[j-1][t];
                            rowEdit[j-1][t] = rowEdit[j][t];
                            rowEdit[j][t] = temp;
                            Object temp2 = doIt[j-1][t];
                            doIt[j-1][t] = doIt[j][t];
                            doIt[j][t] = temp2;
                        }
                    }
                }
            }
        }
        return rowEdit;
    }
    protected static Object convertToType(String value) {
        Object val = null;
        try {
            val = value;
        } catch (Exception e) {}
        try {
            val = Float.parseFloat(value);
        } catch (Exception e) {}
        try {
            val = Integer.parseInt(value);
        } catch (Exception e) {}
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");
            Date TTT = formatter.parse(value);
            val = TTT;
        } catch (Exception e) {}
        return val;
    }
}
