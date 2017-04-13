package classes;

import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonWriter;

import classes.SqlParser.sqlFactoryImp;

import java.io.FileReader;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import interfaces.IsDate;
import interfaces.IsFloat;
import interfaces.IsInteger;
import interfaces.IsString;
import interfaces.WriterInterface;

public class JSONParser implements WriterInterface{
    
    private String DBDirExtended = null;
    private static String dBDir;
    private static JSONParser reference = null;
    
    private JSONParser(){
        String operatingSystem = System.getProperty(Messages.getString("JSONParser.0")); //$NON-NLS-1$
        operatingSystem = operatingSystem.substring(0, 3);
        if(operatingSystem.equals(Messages.getString("JSONParser.1"))) { //$NON-NLS-1$
            DBDirExtended = dBDir + File.separator;
        } else {
            DBDirExtended = dBDir + File.separator;
        }
    }

    //The ProtoccolBuffer code here
    @Override
    public TabelImp read(final String TableName) throws Exception {
        ArrayList<DBNode> fields = new ArrayList<>();
        File inputFile = new File(DBDirExtended + TableName + Messages.getString("JSONParser.2")); //$NON-NLS-1$
        FileReader inputStream = new FileReader(inputFile);
    	org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();
        Object obj = parser.parse(inputStream);
		JSONObject root = (JSONObject) obj;
        JSONArray table = (JSONArray) root.get(TableName);
        for(int i=0;i<table.size();i++){
              JSONObject node = (JSONObject) table.get(i);
              String name = (String) node.get(Messages.getString("JSONParser.3")); //$NON-NLS-1$
              String dbNodeType =(String) node.get(Messages.getString("JSONParser.4")); //$NON-NLS-1$
              DBNode x = createColmn(dbNodeType, name);
              JSONArray col=(JSONArray) node.get(Messages.getString("JSONParser.5")); //$NON-NLS-1$
              for(int j=0;j<col.size();j++){
                  Object val = convertToType(col.get(j).toString());
                  x.getColumn().add(val);
              }
             fields.add(x);
        }
        inputStream.close();
        return new TabelImp(TableName, fields);
    }

    private DBNode createColmn(String type, String readName) {
        DBNode element = null;
        if (type.equals(Messages.getString("JSONParser.6"))) { //$NON-NLS-1$
            element = new StringNode(readName);
        } else if ((type).equals(Messages.getString("JSONParser.7"))) { //$NON-NLS-1$
            element = new IntegerNode(readName);
        } else if ((type).equals(Messages.getString("JSONParser.8"))) { //$NON-NLS-1$
            element = new FloatNode(readName);
        } else if ((type).equals(Messages.getString("JSONParser.9"))) { //$NON-NLS-1$
            element = new DateNode(readName);
        }
        return element;
    }
    
    @Override
    public void write(final TabelImp Table) throws Exception {
        File file = new File(DBDirExtended+Table.GetTableName()+Messages.getString("JSONParser.10")); //$NON-NLS-1$
        file.delete();
        JSONObject obj = new JSONObject();
        JSONArray table = new JSONArray();
        ArrayList<DBNode> s = Table.getTable();
        for(int i=0;i<s.size();i++){
            JSONObject DBnode = new JSONObject();
            DBnode.put(Messages.getString("JSONParser.11"),s.get(i).getName()); //$NON-NLS-1$
            DBnode.put(Messages.getString("JSONParser.12"),getType(s,i));          //$NON-NLS-1$
            JSONArray columns = new JSONArray();
            for(int j=0;j<s.get(i).getColumn().size();j++){
                if(s.get(i).getColumn().get(j) != null) {
                if (s.get(i).getColumn().get(j) instanceof Date) {
                    DateFormat format = new SimpleDateFormat(Messages.getString("JSONParser.13")); //$NON-NLS-1$
                    columns.add(format.format(s.get(i).getColumn().get(j)));
                }else {
                    columns.add(s.get(i).getColumn().get(j));
                }
                } else {
                    columns.add(Messages.getString("JSONParser.14")); //$NON-NLS-1$
                }
            }
            DBnode.put(Messages.getString("JSONParser.15"),columns); //$NON-NLS-1$
            table.add(DBnode);
        }
        obj.put(Table.GetTableName(), table);
        FileWriter fos = new FileWriter(DBDirExtended + Table.GetTableName() + Messages.getString("JSONParser.16")); //$NON-NLS-1$
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        fos.write(gson.toJson(obj));
        new HeadImp(sqlFactoryImp.head.Dir,sqlFactoryImp.head.formatType).makeDtd(Table.GetTableName(), DBDirExtended);
        fos.close();
    }

    private String getType(ArrayList<DBNode> s, int i) {
        String t = null;
        if (s.get(i) instanceof IsInteger) {
            t = Messages.getString("JSONParser.17"); //$NON-NLS-1$
        } else if (s.get(i) instanceof IsString) {
            t = Messages.getString("JSONParser.18"); //$NON-NLS-1$
        } else if (s.get(i) instanceof IsFloat) {
            t = Messages.getString("JSONParser.19"); //$NON-NLS-1$
        } else if (s.get(i) instanceof IsDate) {
            t = Messages.getString("JSONParser.20"); //$NON-NLS-1$
        }
        return t;
    }
    
    private void setDirectory(final String Directory){
        this.dBDir = Directory;
        String operatingSystem = System.getProperty(Messages.getString("JSONParser.21")); //$NON-NLS-1$
        operatingSystem = operatingSystem.substring(0, 3);
        if(operatingSystem.equals(Messages.getString("JSONParser.22"))) { //$NON-NLS-1$
            DBDirExtended = dBDir + File.separator;
        } else {
            DBDirExtended = dBDir + File.separator;
        }
    }
    
    public static JSONParser getInstance(final String dbDir){

        if (reference != null){
            reference.setDirectory(dbDir);
        }
        reference = new JSONParser();
        reference.setDirectory(dbDir);
        return reference;
    }
    
    protected Object convertToType(String value) {
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
            DateFormat formatter = new SimpleDateFormat(Messages.getString("JSONParser.23")); //$NON-NLS-1$
            Date TTT = new Date(formatter.parse(value).getTime());
            val = TTT;
        } catch (Exception e) {}
        return val;
    }
}