package classes;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.sql.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import classes.SqlParser.sqlFactoryImp;
import interfaces.IsDate;
import interfaces.IsFloat;
import interfaces.IsInteger;
import interfaces.IsString;
import interfaces.WriterInterface;
import interfaces.XmlFactoryIF;

public class XMLParser implements XmlFactoryIF,WriterInterface {

    private static String dBDir;
    org.w3c.dom.Document doc;
    private ArrayList<DBNode> fields;
    private String dBDirExtended = null;
    private static XMLParser reference = null;

    private XMLParser() {
        String operatingSystem = System.getProperty(Messages.getString("XMLParser.0")); //$NON-NLS-1$
        operatingSystem = operatingSystem.substring(0, 3);
        if (operatingSystem.equals(Messages.getString("XMLParser.1"))) { //$NON-NLS-1$
            dBDirExtended = dBDir + File.separator;
        } else {
            dBDirExtended = dBDir + File.separator;
        }
    }

    @Override
    public TabelImp read(final String tableName) throws Exception {
        fields = new ArrayList<>();
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Print(dBDirExtended);
        File inputFile = new File(dBDirExtended + tableName + Messages.getString("XMLParser.2")); //$NON-NLS-1$
        InputStream inputStream = new FileInputStream(inputFile);
        org.w3c.dom.Document doc = builder.parse(dBDirExtended + tableName + Messages.getString("XMLParser.3")); //$NON-NLS-1$
        NodeList rootElement = doc.getElementsByTagName(tableName);
        NodeList tableFields = rootElement.item(0).getChildNodes();
        for (int i = 1; i < tableFields.getLength(); i = i + 2) {
            NodeList currentNode = tableFields.item(i).getChildNodes();
            LinkedList<Object> readColumn = new LinkedList<>();
            org.w3c.dom.Node name = currentNode.item(1);
            org.w3c.dom.Node type = currentNode.item(3);
            readColumn = readColms(currentNode.item(5).getChildNodes(), type, readColumn);
            DBNode element = createColmn(type, name.getTextContent());
            element.setColumn(readColumn);
            fields.add(element);
        }
        inputStream.close();
        Print(fields.toString());
        return new TabelImp(tableName, fields);
    }

    private void Print(String dBDirExtended2) {
        //System.out.println("XMl Parser   "+dBDirExtended2);
    }

    private DBNode createColmn(Node type, String readName) {
        DBNode element = null;
        if ((type.getTextContent()).equals(Messages.getString("XMLParser.4"))) { //$NON-NLS-1$
            element = new StringNode(readName);
        } else if ((type.getTextContent()).equals(Messages.getString("XMLParser.5"))) { //$NON-NLS-1$
            element = new IntegerNode(readName);
        } else if ((type.getTextContent()).equals(Messages.getString("XMLParser.6"))) { //$NON-NLS-1$
            element = new FloatNode(readName);
        } else if ((type.getTextContent()).equals(Messages.getString("XMLParser.7"))) { //$NON-NLS-1$
            element = new DateNode(readName);
        }
        return element;
    }

    private LinkedList<Object> readColms(NodeList rows, Node type, LinkedList<Object> readColumn) throws Exception, ParseException {
        for (int j = 1; j < rows.getLength(); j = j + 2) {
            org.w3c.dom.Node row = rows.item(j);
            if ((type.getTextContent()).equals(Messages.getString("XMLParser.8"))) { //$NON-NLS-1$
                readColumn.add(row.getTextContent());
            } else if ((type.getTextContent()).equals(Messages.getString("XMLParser.9"))) { //$NON-NLS-1$
                if (!row.getTextContent().equals(Messages.getString("XMLParser.10"))) //$NON-NLS-1$
                    readColumn.add(Integer.parseInt(row.getTextContent()));
                else
                    readColumn.add(null);
            } else if ((type.getTextContent()).equals(Messages.getString("XMLParser.11"))) { //$NON-NLS-1$
                if (!row.getTextContent().equals(Messages.getString("XMLParser.12"))) //$NON-NLS-1$
                    readColumn.add(Float.parseFloat(row.getTextContent()));
                else
                    readColumn.add(null);
            } else if ((type.getTextContent()).equals(Messages.getString("XMLParser.13"))) { //$NON-NLS-1$
                if (!row.getTextContent().equals(Messages.getString("XMLParser.14"))){ //$NON-NLS-1$
                    DateFormat formatter = new SimpleDateFormat(Messages.getString("XMLParser.15")); //$NON-NLS-1$
                    Date date = new Date(formatter.parse(row.getTextContent()).getTime());
                    readColumn.add(date);
                }else
                    readColumn.add(null);
            }
        }
        return readColumn;
    }

    @Override
    public void write(final TabelImp table) throws Exception {
        Print(Messages.getString("XMLParser.16")+dBDirExtended); //$NON-NLS-1$
        File file = new File(dBDirExtended + table.GetTableName() + Messages.getString("XMLParser.17")); //$NON-NLS-1$
        file.delete();
        DocumentBuilderFactory docFact = DocumentBuilderFactory.newInstance();
        DocumentBuilder build = docFact.newDocumentBuilder();
        doc = build.newDocument();
        DOMImplementation domImpl = doc.getImplementation();
        DocumentType doctype = domImpl.createDocumentType(table.GetTableName(), Messages.getString("XMLParser.18"), //$NON-NLS-1$
                table.GetTableName() + Messages.getString("XMLParser.19")); //$NON-NLS-1$
        doc.appendChild(doctype);
        Element root = doc.createElement(table.GetTableName());
        doc.appendChild(root);
        ArrayList<DBNode> colmns = table.getTable();
        createNodes(colmns, root);
        rotineProtocol(table, doctype);
    }

    private void createNodes(ArrayList<DBNode> colmns, Element root) {
        for (int i = 0; i < colmns.size(); i++) {
            Element dBnode = doc.createElement(Messages.getString("XMLParser.20")); //$NON-NLS-1$
            root.appendChild(dBnode);
            Element name = doc.createElement(Messages.getString("XMLParser.21")); //$NON-NLS-1$
            name.appendChild(doc.createTextNode(colmns.get(i).getName()));
            Element type = doc.createElement(Messages.getString("XMLParser.22")); //$NON-NLS-1$
            type.appendChild(doc.createTextNode(getType(colmns, i)));
            Element column = doc.createElement(Messages.getString("XMLParser.23")); //$NON-NLS-1$
            for (int j = 0; j < colmns.get(i).getColumn().size(); j++) {
                Element row = doc.createElement(Messages.getString("XMLParser.24")); //$NON-NLS-1$
                if (colmns.get(i).getColumn().get(j) instanceof Date) {
                    DateFormat format = new SimpleDateFormat(Messages.getString("XMLParser.25")); //$NON-NLS-1$
                    row.appendChild(doc.createTextNode(format.format(colmns.get(i).getColumn().get(j))));
                }else {
                    row.appendChild(doc.createTextNode(String.valueOf(colmns.get(i).getColumn().get(j))));
                }
                column.appendChild(row);
            }
            dBnode.appendChild(name);
            dBnode.appendChild(type);
            dBnode.appendChild(column);
        }
    }

    private void rotineProtocol(TabelImp table, DocumentType doctype) throws Exception {
        TransformerFactory tranFactory = TransformerFactory.newInstance();
        Transformer aTransformer;
        aTransformer = tranFactory.newTransformer();
        aTransformer.setOutputProperty(OutputKeys.ENCODING, Messages.getString("XMLParser.26")); //$NON-NLS-1$
        aTransformer.setOutputProperty(Messages.getString("XMLParser.27"), Messages.getString("XMLParser.28")); //$NON-NLS-1$ //$NON-NLS-2$
        aTransformer.setOutputProperty(OutputKeys.INDENT, Messages.getString("XMLParser.29")); //$NON-NLS-1$
        aTransformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, doctype.getSystemId());
        DOMSource source = new DOMSource(doc);
        FileWriter fos = new FileWriter(dBDirExtended+table.GetTableName()+Messages.getString("XMLParser.30")); //$NON-NLS-1$
        StreamResult result = new StreamResult(fos);
        aTransformer.transform(source, result);
        new HeadImp(sqlFactoryImp.head.Dir,sqlFactoryImp.head.formatType).makeDtd(table.GetTableName(), dBDirExtended);
        fos.close();
    }

    private String getType(ArrayList<DBNode> s, int i) {
        String t = null;
        if (s.get(i) instanceof IsInteger) {
            t = Messages.getString("XMLParser.31"); //$NON-NLS-1$
        } else if (s.get(i) instanceof IsString) {
            t = Messages.getString("XMLParser.32"); //$NON-NLS-1$
        } else if (s.get(i) instanceof IsFloat) {
            t = Messages.getString("XMLParser.33"); //$NON-NLS-1$
        } else if (s.get(i) instanceof IsDate) {
            t = Messages.getString("XMLParser.34"); //$NON-NLS-1$
        }
        return t;
    }
    
    private void setDirectory(final String dBDir){
        this.dBDir = dBDir ;
        String operatingSystem = System.getProperty(Messages.getString("XMLParser.35")); //$NON-NLS-1$
        operatingSystem = operatingSystem.substring(0, 3);
        if (operatingSystem.equals(Messages.getString("XMLParser.36"))) { //$NON-NLS-1$
            dBDirExtended = dBDir + File.separator;
        } else {
            dBDirExtended = dBDir + File.separator;
        }
    }
    
    public static XMLParser getInstance(final String dbDir){

        if (reference != null){
            reference.setDirectory(dbDir);
        }
        reference = new XMLParser();
        reference.setDirectory(dbDir);
        return reference;
    }
}