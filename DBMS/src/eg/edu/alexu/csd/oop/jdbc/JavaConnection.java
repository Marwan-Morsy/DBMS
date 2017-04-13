package eg.edu.alexu.csd.oop.jdbc;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import java.util.Properties;
import java.util.concurrent.Executor;


public class JavaConnection implements java.sql.Connection {

    private boolean closed;
    private String path;
    private String formatType;
    private  Logger history ;

    public JavaConnection(final String path, String formatType, Logger history) {
        // TODO Auto-generated constructor stub
        closed = false;
        this.path = path;
        this.formatType = formatType;
        this.history = history;
        history.info(Messages.getString("JavaConnection.0")); //$NON-NLS-1$
    }

    @Override
    public boolean isWrapperFor(final Class<?> arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.1")); //$NON-NLS-1$

    }

    @Override
    public <T> T unwrap(final Class<T> arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.2")); //$NON-NLS-1$

    }

    @Override
    public void abort(final Executor arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.3")); //$NON-NLS-1$

    }

    @Override
    public void clearWarnings() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.4")); //$NON-NLS-1$

    }

    @Override
    public void close() throws SQLException { // ========

        closed = true;
        history.info(Messages.getString("JavaConnection.5")); //$NON-NLS-1$

    }

    @Override
    public void commit() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.6")); //$NON-NLS-1$

    }

    @Override
    public Array createArrayOf(final String arg0, final Object[] arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.7")); //$NON-NLS-1$

    }

    @Override
    public Blob createBlob() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.8")); //$NON-NLS-1$

    }

    @Override
    public Clob createClob() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.9")); //$NON-NLS-1$

    }

    @Override
    public NClob createNClob() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.10")); //$NON-NLS-1$

    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.11")); //$NON-NLS-1$

    }

    @Override
    public JavaStatement createStatement() throws SQLException { // ========

        if (closed){
             history.error(Messages.getString("JavaConnection.12")); //$NON-NLS-1$
             throw new SQLException();
        }
        return new JavaStatement(this , path, formatType,history);
    }

    @Override
    public Statement createStatement(final int arg0, final int arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.13")); //$NON-NLS-1$

    }

    @Override
    public Statement createStatement(final int arg0, final int arg1, final int arg2) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.14")); //$NON-NLS-1$


    }

    @Override
    public Struct createStruct(final String arg0, final Object[] arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.15")); //$NON-NLS-1$

    }

    @Override
    public boolean getAutoCommit() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.16")); //$NON-NLS-1$

    }

    @Override
    public String getCatalog() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.17")); //$NON-NLS-1$

    }

    @Override
    public Properties getClientInfo() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.18")); //$NON-NLS-1$

    }

    @Override
    public String getClientInfo(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.19")); //$NON-NLS-1$

    }

    @Override
    public int getHoldability() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.20")); //$NON-NLS-1$

    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.21")); //$NON-NLS-1$

    }

    @Override
    public int getNetworkTimeout() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.22")); //$NON-NLS-1$

    }

    @Override
    public String getSchema() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.23")); //$NON-NLS-1$

    }

    @Override
    public int getTransactionIsolation() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.24")); //$NON-NLS-1$

    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.25")); //$NON-NLS-1$

    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.26")); //$NON-NLS-1$

    }

    @Override
    public boolean isClosed() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.27")); //$NON-NLS-1$

    }

    @Override
    public boolean isReadOnly() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.28")); //$NON-NLS-1$

    }

    @Override
    public boolean isValid(final int arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.29")); //$NON-NLS-1$

    }

    @Override
    public String nativeSQL(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.30")); //$NON-NLS-1$

    }

    @Override
    public CallableStatement prepareCall(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.31")); //$NON-NLS-1$

    }

    @Override
    public CallableStatement prepareCall(final String arg0, final int arg1, final int arg2) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.32")); //$NON-NLS-1$

    }

    @Override
    public CallableStatement prepareCall(final String arg0, final int arg1, final int arg2, final int arg3)
            throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.33")); //$NON-NLS-1$

    }

    @Override
    public PreparedStatement prepareStatement(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.34")); //$NON-NLS-1$

    }

    @Override
    public PreparedStatement prepareStatement(final String arg0, final int arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.35")); //$NON-NLS-1$

    }

    @Override
    public PreparedStatement prepareStatement(final String arg0, final int[] arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.36")); //$NON-NLS-1$

    }

    @Override
    public PreparedStatement prepareStatement(final String arg0, final String[] arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.37")); //$NON-NLS-1$

    }

    @Override
    public PreparedStatement prepareStatement(final String arg0, final int arg1, final int arg2) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.38")); //$NON-NLS-1$

    }

    @Override
    public PreparedStatement prepareStatement(final String arg0, final int arg1, final int arg2, final int arg3)
            throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.39")); //$NON-NLS-1$

    }

    @Override
    public void releaseSavepoint(final Savepoint arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.40")); //$NON-NLS-1$

    }

    @Override
    public void rollback() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.41")); //$NON-NLS-1$

    }

    @Override
    public void rollback(final Savepoint arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.42")); //$NON-NLS-1$

    }

    @Override
    public void setAutoCommit(final boolean arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.43")); //$NON-NLS-1$

    }

    @Override
    public void setCatalog(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.44")); //$NON-NLS-1$

    }

    @Override
    public void setClientInfo(final Properties arg0) throws SQLClientInfoException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.45")); //$NON-NLS-1$

    }

    @Override
    public void setClientInfo(final String arg0, final String arg1) throws SQLClientInfoException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.46")); //$NON-NLS-1$

    }

    @Override
    public void setHoldability(final int arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.47")); //$NON-NLS-1$

    }

    @Override
    public void setNetworkTimeout(final Executor arg0, final int arg1) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.48")); //$NON-NLS-1$

    }

    @Override
    public void setReadOnly(final boolean arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.49")); //$NON-NLS-1$

    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.50")); //$NON-NLS-1$

    }

    @Override
    public Savepoint setSavepoint(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.51")); //$NON-NLS-1$

    }

    @Override
    public void setSchema(final String arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.52")); //$NON-NLS-1$

    }

    @Override
    public void setTransactionIsolation(final int arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.53")); //$NON-NLS-1$

    }

    @Override
    public void setTypeMap(final Map<String, Class<?>> arg0) throws SQLException {
       throw new java.lang.UnsupportedOperationException(Messages.getString("JavaConnection.54")); //$NON-NLS-1$

    }

}
