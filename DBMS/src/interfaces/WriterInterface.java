package interfaces;

import classes.TabelImp;

public interface WriterInterface {

    /**
     * Read A file in the Database Folder
     * which have been initialized in the creation of the object
     * then convert it to Table Object to used at any time.
     * @param TableName The Table Name (file-specific Name).
     * @return Table with all its parameters.
     * @throws Exception
     */
    public TabelImp read(String TableName) throws Exception;

    /**
     * Takes a table and convert it to a proper format file
     * by deleting the similar format file which have the same name
     * and create a new one with the new parameters.
     * @param Table table which full of data.
     * @throws Exception
     */
    public void write(TabelImp Table) throws Exception;


}