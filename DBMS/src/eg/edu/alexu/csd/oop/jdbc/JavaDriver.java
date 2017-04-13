package eg.edu.alexu.csd.oop.jdbc;

import java.io.File;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Properties;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JavaDriver implements java.sql.Driver {

    private ArrayList<Connection> currentConections;
    private ArrayList<String> urls;
    private String formatType;
    private  Logger history =  LogManager.getLogger();

    private enum Format {
        XML, JSON
    };

    public JavaDriver() {
        // TODO Auto-generated constructor stub
        currentConections = new ArrayList<>();
        urls = new ArrayList<>();
        history.info(Messages.getString("JavaDriver.0")); //$NON-NLS-1$

    }

    @Override
    public boolean acceptsURL(final String url) throws SQLException { // ========
        Pattern check = Pattern.compile(Messages.getString("JavaDriver.1")); //$NON-NLS-1$
        Matcher mould = check.matcher(url);
        if (mould.find()) {
            if (mould.group(1).equals(Messages.getString("JavaDriver.2"))) { //$NON-NLS-1$
                formatType = Format.XML.toString();
            } else {
                formatType = Format.JSON.toString();
            }
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Connection connect(final String url, final Properties info) throws SQLException { // ========
        // TODO Auto-generated method stub
        urls.add(url);
        File f = (File) info.get(Messages.getString("JavaDriver.3")); //$NON-NLS-1$
        if (f.exists()){
            history.error(Messages.getString("JavaDriver.4")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaDriver.5")); //$NON-NLS-1$
        }
        acceptsURL(url);
        f.mkdir();
        Connection conncection = new JavaConnection(f.getAbsolutePath(),formatType,history);
        currentConections.add(conncection);
        return conncection;
    }

    @Override
    public int getMajorVersion() {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaDriver.6")); //$NON-NLS-1$
    }

    @Override
    public int getMinorVersion() {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaDriver.7")); //$NON-NLS-1$
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaDriver.8")); //$NON-NLS-1$
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(final String url, final Properties info) throws SQLException { // ========
        // TODO Auto-generated method stub
        DriverPropertyInfo[] driverInfo = new DriverPropertyInfo[info.size()];
        if (!urls.isEmpty()) {
            if (!urls.get(urls.size() - 1).equals(url)) {
                urls.add(url);
            }
        } else {
            urls.add(url);
        }
        int x = 0;
        for (Object item : info.keySet()) {
            driverInfo[x].name = item.toString();
            driverInfo[x].value = info.get(item).toString();
            x++;
        }
        return driverInfo;
    }

    @Override
    public boolean jdbcCompliant() {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaDriver.9")); //$NON-NLS-1$
    }

}