package classes;

import interfaces.WriterInterface;

public class FileSystemFactory {

    private static FileSystemFactory reference = null;

    private FileSystemFactory(){

    }

    public WriterInterface fileBuilder(final String formatType,final String dbDir){

        WriterInterface Writer = null ;
        Print(Messages.getString("FileSystemFactory.0")+dbDir); //$NON-NLS-1$
        if (formatType.equals(Messages.getString("FileSystemFactory.1"))){ //$NON-NLS-1$
            Writer = XMLParser.getInstance(dbDir);
        } else if (formatType.equals(Messages.getString("FileSystemFactory.2"))) { //$NON-NLS-1$
            Writer = JSONParser.getInstance(dbDir);
        } else {
        //  Writer = JSONWriter.getInstance(dbDir);
        }
        return Writer;
    }

    private void Print(String dbDir) {
        //System.out.println(dbDir);
    }

    public static FileSystemFactory  getInstance(){

        if (reference == null){
            reference = new FileSystemFactory();
        }

        return reference;

    }
}