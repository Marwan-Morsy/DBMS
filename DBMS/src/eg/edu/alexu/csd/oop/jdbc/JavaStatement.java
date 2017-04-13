package eg.edu.alexu.csd.oop.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;

import classes.SqlParser.sqlFactoryImp;

public class JavaStatement implements java.sql.Statement {

    private ArrayList<String> commands;
    private sqlFactoryImp statment;
    private boolean isClosed;
    private JavaResultset result;
    private int lastResult;
    private JavaConnection father;
    private ArrayList<ArrayList<String>> receiver;
    private String path;
    private Logger history;

    public JavaStatement(final JavaConnection father, String path, String formatType, Logger history) {
        // TODO Auto-generated constructor stub
        commands = new ArrayList<>();
        statment = new sqlFactoryImp(path, formatType);
        isClosed = false;
        this.father = father;
        lastResult = -1;
        this.path = path;
        this.history = history;
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.0") + Messages.getString("JavaStatement.1")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.2") + Messages.getString("JavaStatement.3")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public void addBatch(final String arg0) throws SQLException { // ========
        // TODO Auto-generated method stub

        if (!isClosed) {
            if (keyFound(arg0, Messages.getString("JavaStatement.4")) || keyFound(arg0, Messages.getString("JavaStatement.5"))) { //$NON-NLS-1$ //$NON-NLS-2$
                commands.add(arg0.toLowerCase());
                history.info(Messages.getString("JavaStatement.6")); //$NON-NLS-1$
                return;
            }
        }
        history.error(Messages.getString("JavaStatement.7")); //$NON-NLS-1$
        throw new SQLException(Messages.getString("JavaStatement.8")); //$NON-NLS-1$

    }

    @Override
    public void cancel() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.9") + Messages.getString("JavaStatement.10")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void clearBatch() throws SQLException { // ========
        // TODO Auto-generated method stub
        if (!isClosed) {
            commands.clear();
            history.info(Messages.getString("JavaStatement.11")); //$NON-NLS-1$
        } else {
            history.error(Messages.getString("JavaStatement.12")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaStatement.13")); //$NON-NLS-1$
        }

    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.14") + Messages.getString("JavaStatement.15")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void close() throws SQLException { // ========
        // TODO Auto-generated method stub
        commands = null;
        statment = null;
        isClosed = true;
        lastResult = -1;
        history.info(Messages.getString("JavaStatement.16")); //$NON-NLS-1$

    }

    @Override
    public void closeOnCompletion() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.17") + Messages.getString("JavaStatement.18")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    // any thing
    public boolean execute(final String arg0) throws SQLException { // ========
        // TODO Auto-generated method stub
        lastResult = -1;
        if (!isClosed) {
            try {
                statment.execute(arg0.toLowerCase());
            } catch (Exception e) {
                history.error(Messages.getString("JavaStatement.19")); //$NON-NLS-1$
                throw new SQLException(Messages.getString("JavaStatement.20")); //$NON-NLS-1$
            }
            if (keyFound(arg0, Messages.getString("JavaStatement.21"))) { //$NON-NLS-1$
                try {
                    if (statment.getTable().getFromTable(0) != null) {
                        result = new JavaResultset(statment.getTable(), this, history);
                        history.info(Messages.getString("JavaStatement.22")); //$NON-NLS-1$
                        return true;
                    }
                } catch (Exception e) {
                    // remove
                    return false;
                }
            }
            history.info(Messages.getString("JavaStatement.23")); //$NON-NLS-1$
            return false;
        }
        history.error(Messages.getString("JavaStatement.24")); //$NON-NLS-1$
        throw new SQLException();
    }

    @Override
    public boolean execute(final String arg0, final int arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.25") + Messages.getString("JavaStatement.26")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean execute(final String arg0, final int[] arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.27") + Messages.getString("JavaStatement.28")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean execute(final String arg0, final String[] arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.29") + Messages.getString("JavaStatement.30")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int[] executeBatch() throws SQLException { // ========
        // TODO Auto-generated method stub
        if (!isClosed) {
            int[] changes = new int[commands.size()];
            for (int i = 0; i < changes.length; i++) {
                changes[i] = executeUpdate(commands.get(i));
            }
            lastResult = -1;
            history.info(Messages.getString("JavaStatement.31")); //$NON-NLS-1$
            return changes;
        } else {
            history.error(Messages.getString("JavaStatement.32")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaStatement.33")); //$NON-NLS-1$
        }
    }

    @Override
    // Select only
    public JavaResultset executeQuery(final String arg0) throws SQLException { // ========
        // TODO Auto-generated method stub
        if (!isClosed) {
            if (keyFound(arg0, Messages.getString("JavaStatement.34"))) { //$NON-NLS-1$
                try {
                    receiver = statment.execute(arg0.toLowerCase());
                } catch (Exception e) {
                    history.error(Messages.getString("JavaStatement.35")); //$NON-NLS-1$
                    throw new SQLException(Messages.getString("JavaStatement.36")); //$NON-NLS-1$
                }
                if (receiver != null) {
                    lastResult = -1;
                    try {
                        result = new JavaResultset(statment.getTable(), this, history);
                    } catch (Exception e) {
                        history.error(Messages.getString("JavaStatement.37")); //$NON-NLS-1$
                        throw new SQLException(Messages.getString("JavaStatement.38")); //$NON-NLS-1$
                    }
                    return result;
                }
            }
        }
        throw new SQLException();
    }

    @Override
    // Not select
    public int executeUpdate(final String arg0) throws SQLException { // ========
        // TODO Auto-generated method stub
        if (!isClosed) {
            if (keyFound(arg0, Messages.getString("JavaStatement.39")) || keyFound(arg0, Messages.getString("JavaStatement.40")) || keyFound(arg0, Messages.getString("JavaStatement.41"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                try {
                    statment.execute(arg0.toLowerCase());
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    history.error(Messages.getString("JavaStatement.42")); //$NON-NLS-1$
                    throw new SQLException(e.getMessage());
                }
                try {
                    lastResult = statment.getChangedSize();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    history.error(Messages.getString("JavaStatement.43")); //$NON-NLS-1$
                    throw new SQLException(Messages.getString("JavaStatement.44")); //$NON-NLS-1$
                }
                history.info(Messages.getString("JavaStatement.45")); //$NON-NLS-1$
                return lastResult;
            }
        }
        history.error(Messages.getString("JavaStatement.46")); //$NON-NLS-1$
        throw new SQLException(Messages.getString("JavaStatement.47")); //$NON-NLS-1$
    }

    @Override
    public int executeUpdate(final String arg0, final int arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.48") + Messages.getString("JavaStatement.49")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int executeUpdate(final String arg0, final int[] arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.50") + Messages.getString("JavaStatement.51")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int executeUpdate(final String arg0, final String[] arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.52") + Messages.getString("JavaStatement.53")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public JavaConnection getConnection() throws SQLException { // ========
        // TODO Auto-generated method stub
        return father;
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.54") + Messages.getString("JavaStatement.55")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.56") + Messages.getString("JavaStatement.57")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.58") + Messages.getString("JavaStatement.59")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.60") + Messages.getString("JavaStatement.61")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getMaxRows() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.62") + Messages.getString("JavaStatement.63")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.64") + Messages.getString("JavaStatement.65")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean getMoreResults(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.66") + Messages.getString("JavaStatement.67")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getQueryTimeout() throws SQLException { // ========
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.68") + Messages.getString("JavaStatement.69")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return result;
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.70") + Messages.getString("JavaStatement.71")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.72") + Messages.getString("JavaStatement.73")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getResultSetType() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.74") + Messages.getString("JavaStatement.75")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return lastResult;
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.76") + Messages.getString("JavaStatement.77")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean isCloseOnCompletion() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.78") + Messages.getString("JavaStatement.79")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.80") + Messages.getString("JavaStatement.81")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean isPoolable() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.82") + Messages.getString("JavaStatement.83")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setCursorName(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.84") + Messages.getString("JavaStatement.85")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setEscapeProcessing(final boolean arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.86") + Messages.getString("JavaStatement.87")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setFetchDirection(final int arg0) throws SQLException {
        // TODO Auto-generated method stub
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.88") + Messages.getString("JavaStatement.89")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setFetchSize(final int arg0) throws SQLException {
        // TODO Auto-generated method stub
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.90") + Messages.getString("JavaStatement.91")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setMaxFieldSize(final int arg0) throws SQLException {
        // TODO Auto-generated method stub
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.92") + Messages.getString("JavaStatement.93")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setMaxRows(final int arg0) throws SQLException {
        // TODO Auto-generated method stub
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.94") + Messages.getString("JavaStatement.95")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setPoolable(final boolean arg0) throws SQLException {
        // TODO Auto-generated method stub
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.96") + Messages.getString("JavaStatement.97")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void setQueryTimeout(final int arg0) throws SQLException { // ========
        // TODO Auto-generated method stub
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaStatement.98") + Messages.getString("JavaStatement.99")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    private boolean keyFound(String input, String key) {
        Pattern sele = Pattern.compile(Messages.getString("JavaStatement.100") + key + Messages.getString("JavaStatement.101")); //$NON-NLS-1$ //$NON-NLS-2$
        Matcher matcher = sele.matcher(input.trim().replaceAll(Messages.getString("JavaStatement.102"), Messages.getString("JavaStatement.103"))); //$NON-NLS-1$ //$NON-NLS-2$
        if (matcher.find()) {
            return true;
        }
        return false;
    }

}