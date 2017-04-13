package classes;

import java.util.ArrayList;

public class DrawTable {

    String[] Colms;
    ArrayList<ArrayList<String>> Rows;

    public DrawTable(String[] Colms, ArrayList<ArrayList<String>> Rows) {
        this.Colms = Colms;
        this.Rows = Rows;
    }

    public void DrawLine() {
        for (int j = 0; j < Colms.length; j++) {
            System.out.print(Messages.getString("DrawTable.0")); //$NON-NLS-1$
            for (int y = 0; y < Colms[j].length(); y++) {
                System.out.print(Messages.getString("DrawTable.1")); //$NON-NLS-1$
            }
            for (int k = 0; k < 15 - Colms[j].length(); k++) {
                System.out.print(Messages.getString("DrawTable.2")); //$NON-NLS-1$
            }
        }
        System.out.print(Messages.getString("DrawTable.3")); //$NON-NLS-1$
        System.out.println(Messages.getString("DrawTable.4")); //$NON-NLS-1$
    }

    public void Title() {
        for (int j = 0; j < Colms.length; j++) {
            System.out.print(Messages.getString("DrawTable.5")); //$NON-NLS-1$
            System.out.print(Colms[j]);
            for (int k = 0; k < 15 - Colms[j].length(); k++) {
                System.out.print(Messages.getString("DrawTable.6")); //$NON-NLS-1$
            }
        }
        System.out.print(Messages.getString("DrawTable.7")); //$NON-NLS-1$
        System.out.println();
    }
    
    public void Columns() {
        for (int p = 0; p < Rows.get(0).size(); p++) {
            for (int j = 0; j < Rows.size(); j++) {
                System.out.print(Messages.getString("DrawTable.8")); //$NON-NLS-1$
                System.out.print((Rows.get(j).get(p) != null)?Rows.get(j).get(p) : Messages.getString("DrawTable.9")); //$NON-NLS-1$
                for (int k = 0; k < 15 - ((Rows.get(j).get(p) != null)?Rows.get(j).get(p) : Messages.getString("DrawTable.10")).length(); k++) { //$NON-NLS-1$
                    System.out.print(Messages.getString("DrawTable.11")); //$NON-NLS-1$
                }
            }
            System.out.print(Messages.getString("DrawTable.12")); //$NON-NLS-1$
            System.out.println();
        }
    }
}
