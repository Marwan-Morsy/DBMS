package classes.SqlParser.Select;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.TabelImp;
import classes.SqlParser.Statements;

public abstract class Select extends Statements {
	
    Pattern pattern;
    Matcher matcher;
    String input;
    String [] columms;
    
    public Select(String input) {
        this.input = input;
    }
    public abstract ArrayList<ArrayList<String>> select() throws Exception;
    
    protected String[] divideComma(String columns) {
        // TODO Auto-generated method stub
        columns = columns.replaceAll(Messages.getString("Select.0"), Messages.getString("Select.1")); //$NON-NLS-1$ //$NON-NLS-2$
        return columns.split(Messages.getString("Select.2")); //$NON-NLS-1$
    }
    protected String[] dividespace(String columns) {
        // TODO Auto-generated method stub
        columns = columns.trim().replaceAll(Messages.getString("Select.3"), Messages.getString("Select.4")); //$NON-NLS-1$ //$NON-NLS-2$
        return columns.split(Messages.getString("Select.5")); //$NON-NLS-1$
    }
    
    public String[] getColumms() {
        return columms;
    }
    public void setColumms(String[] columms) {
        this.columms = columms;
    }
    
    protected ArrayList<ArrayList<String>> orderBy(ArrayList<ArrayList<String>> rows, String[] orderCol, String[] type) {
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
        for(int i = orderCol.length -1; i >=0 ; i--) {
            if(type[i].equals(Messages.getString("Select.6"))) //$NON-NLS-1$
            rowEdit = sortItASC(rowEdit,index[i]);
            else if(type[i].equals(Messages.getString("Select.7"))) //$NON-NLS-1$
                rowEdit = sortItDESC(rowEdit,index[i]);
            else
                rowEdit = sortItASC(rowEdit,index[i]);
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

        private String[][] sortItASC(String[][] rowEdit, int x) {
            Object[][] doIt = new Object[rowEdit.length][rowEdit[0].length];
            int n = rowEdit.length;
            String temp;
            for(int i = 0; i < rowEdit.length; i++) {
                for (int j = 0; j < rowEdit[0].length; j++) {
                    doIt[i][j] = convertToType(rowEdit[i][j]);
                    System.out.print(rowEdit[i][j]+Messages.getString("Select.8")); //$NON-NLS-1$
                }
                System.out.println();
            }
            for (int i = 0; i < rowEdit[0].length; i++) {
                for (int j = 1; j < (rowEdit[0].length - i); j++) {
                    if(doIt[x][j-1] instanceof String) {
                    if (((String)doIt[x][j-1]).compareTo((String)doIt[x][j]) > 0) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[t][j-1];
                            rowEdit[t][j-1] = rowEdit[t][j];
                            rowEdit[t][j] = temp;
                            Object temp2 = doIt[t][j-1];
                            doIt[t][j-1] = doIt[t][j];
                            doIt[t][j] = temp2;
                        }
                    }
                    } else if (doIt[x][j-1] instanceof Integer) {
                        if ((Integer)doIt[x][j-1] > ((Integer)doIt[x][j])) {
                            // swap the elements!
                            for(int t = 0; t < n; t++) {
                                temp = rowEdit[t][j-1];
                                rowEdit[t][j-1] = rowEdit[t][j];
                                rowEdit[t][j] = temp;
                                Object temp2 = doIt[t][j-1];
                                doIt[t][j-1] = doIt[t][j];
                                doIt[t][j] = temp2;
                            }
                        }
                    } else if (doIt[x][j-1] instanceof Float) {
                        if ((Float)doIt[x][j-1] > ((Float)doIt[x][j])) {
                            // swap the elements!
                            for(int t = 0; t < n; t++) {
                                temp = rowEdit[t][j-1];
                                rowEdit[t][j-1] = rowEdit[t][j];
                                rowEdit[t][j] = temp;
                                Object temp2 = doIt[t][j-1];
                                doIt[t][j-1] = doIt[t][j];
                                doIt[t][j] = temp2;
                            }
                        }
                    } else if (doIt[x][j-1] instanceof Date) {
                        if (((Date)doIt[x][j-1]).after((Date)doIt[x][j])) {
                            // swap the elements!
                            for(int t = 0; t < n; t++) {
                                temp = rowEdit[t][j-1];
                                rowEdit[t][j-1] = rowEdit[t][j];
                                rowEdit[t][j] = temp;
                                Object temp2 = doIt[t][j-1];
                                doIt[t][j-1] = doIt[t][j];
                                doIt[t][j] = temp2;
                            }
                        }
                    }
                }
            }
            return rowEdit;
        }
        
        private String[][] sortItDESC(String[][] rowEdit, int x) {
            Object[][] doIt = new Object[rowEdit.length][rowEdit[0].length];
            int n = rowEdit.length;
            String temp;
            for(int i = 0; i < rowEdit.length; i++) {
                for (int j = 0; j < rowEdit[0].length; j++) {
                    doIt[i][j] = convertToType(rowEdit[i][j]);
                    System.out.print(rowEdit[i][j]+Messages.getString("Select.9")); //$NON-NLS-1$
                }
                System.out.println();
            }
            for (int i = 0; i < rowEdit[0].length; i++) {
                for (int j = 1; j < (rowEdit[0].length - i); j++) {
                    if(doIt[x][j-1] instanceof String) {
                    if (((String)doIt[x][j-1]).compareTo((String)doIt[x][j]) < 0) {
                        // swap the elements!
                        for(int t = 0; t < n; t++) {
                            temp = rowEdit[t][j-1];
                            rowEdit[t][j-1] = rowEdit[t][j];
                            rowEdit[t][j] = temp;
                            Object temp2 = doIt[t][j-1];
                            doIt[t][j-1] = doIt[t][j];
                            doIt[t][j] = temp2;
                        }
                    }
                    } else if (doIt[x][j-1] instanceof Integer) {
                        if ((Integer)doIt[x][j-1] < ((Integer)doIt[x][j])) {
                            // swap the elements!
                            for(int t = 0; t < n; t++) {
                                temp = rowEdit[t][j-1];
                                rowEdit[t][j-1] = rowEdit[t][j];
                                rowEdit[t][j] = temp;
                                Object temp2 = doIt[t][j-1];
                                doIt[t][j-1] = doIt[t][j];
                                doIt[t][j] = temp2;
                            }
                        }
                    } else if (doIt[x][j-1] instanceof Float) {
                        if ((Float)doIt[x][j-1] < ((Float)doIt[x][j])) {
                            // swap the elements!
                            for(int t = 0; t < n; t++) {
                                temp = rowEdit[t][j-1];
                                rowEdit[t][j-1] = rowEdit[t][j];
                                rowEdit[t][j] = temp;
                                Object temp2 = doIt[t][j-1];
                                doIt[t][j-1] = doIt[t][j];
                                doIt[t][j] = temp2;
                            }
                        }
                    } else if (doIt[x][j-1] instanceof Date) {
                        if (((Date)doIt[x][j-1]).before((Date)doIt[x][j])) {
                            // swap the elements!
                            for(int t = 0; t < n; t++) {
                                temp = rowEdit[t][j-1];
                                rowEdit[t][j-1] = rowEdit[t][j];
                                rowEdit[t][j] = temp;
                                Object temp2 = doIt[t][j-1];
                                doIt[t][j-1] = doIt[t][j];
                                doIt[t][j] = temp2;
                            }
                        }
                    }
                }
            }
            return rowEdit;
        }
        /*protected Object convertToType(String value) {
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
                Date TTT = new Date(formatter.parse(value).getTime());
                val = TTT;
            } catch (Exception e) {}
            return val;
        }*/
        
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
