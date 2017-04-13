package eg.edu.alexu.csd.oop.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import org.apache.logging.log4j.Logger;

import classes.TabelImp;
import interfaces.IsDate;
import interfaces.IsFloat;
import interfaces.IsInteger;
import interfaces.IsString;

public class JavaResultset implements java.sql.ResultSet {

    private JavaResultsetMetadata meta;
    private TabelImp resultSet;
    private int rowIndex;
    private boolean isClosed;
    private JavaStatement father;
    private Logger history;

    public JavaResultset(final TabelImp result, JavaStatement father,Logger history) {
        // TODO Auto-generated constructor stub
        rowIndex = 0;
        isClosed = false;
        this.resultSet = result;
        this.father = father;
        meta = new JavaResultsetMetadata(result);
        this.history= history;

    }

    public ArrayList<ArrayList<String>> getArrayList(){
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for(int i = 0 ; i < resultSet.getNumberOfRows() ; i++){
            output.add(resultSet.getRow(i));
        }
        return output;
    }
    
    private void Checker() throws SQLException {
        if (isClosed) {
            throw new SQLException(Messages.getString("JavaResultset.0")); //$NON-NLS-1$
        }
    }

    private boolean isValidRow() {
        if (rowIndex < resultSet.getNumberOfRows() && rowIndex > -1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.1") + Messages.getString("JavaResultset.2")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.3") + Messages.getString("JavaResultset.4")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean absolute(final int row) throws SQLException { // ========
        this.Checker();
        int tmp = -1;
        if (row > 0 && row <= resultSet.getNumberOfRows()) {
            tmp = row - 1;

        } else if (row < 0 && (Math.abs(row)) <= resultSet.getNumberOfRows()) {
            tmp = resultSet.getNumberOfRows() + row;
        }
        if(tmp >= 0 && tmp < resultSet.getNumberOfRows()){
            rowIndex = tmp;
            return true;
        }
        return false;
    }

    @Override
    public void afterLast() throws SQLException { // ========
        this.Checker();
        rowIndex = resultSet.getNumberOfRows();

    }

    @Override
    public void beforeFirst() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0) {
            rowIndex = -1;
        }
    }

    @Override
    public void cancelRowUpdates() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.5") + Messages.getString("JavaResultset.6")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void clearWarnings() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.7") + Messages.getString("JavaResultset.8")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public void close() throws SQLException { // ========
        rowIndex = 0;
        resultSet = null;
        isClosed = true;
        history.info(Messages.getString("JavaResultset.9")); //$NON-NLS-1$

    }

    @Override
    public void deleteRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.10") + Messages.getString("JavaResultset.11")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    @Override
    public int findColumn(final String columnLabel) throws SQLException { // ========
        int columnIndex = 0;
        this.Checker();
        if (!resultSet.isIn(columnLabel)) {
        	history.info(Messages.getString("JavaResultset.12") + Messages.getString("JavaResultset.13")); //$NON-NLS-1$ //$NON-NLS-2$
            throw new SQLException(Messages.getString("JavaResultset.14") + Messages.getString("JavaResultset.15")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        columnIndex = resultSet.getIndex(columnLabel);
        return columnIndex;
    }

    @Override
    public boolean first() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0) {
            rowIndex = 0;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Array getArray(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.16") + Messages.getString("JavaResultset.17")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Array getArray(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.18") + Messages.getString("JavaResultset.19")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public InputStream getAsciiStream(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.20") + Messages.getString("JavaResultset.21")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public InputStream getAsciiStream(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.22") + Messages.getString("JavaResultset.23")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public BigDecimal getBigDecimal(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.24") + Messages.getString("JavaResultset.25")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public BigDecimal getBigDecimal(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.26") + Messages.getString("JavaResultset.27")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public BigDecimal getBigDecimal(final int arg0, final int arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.28") + Messages.getString("JavaResultset.29")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public BigDecimal getBigDecimal(final String arg0, final int arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.30") + Messages.getString("JavaResultset.31")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public InputStream getBinaryStream(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.32") + Messages.getString("JavaResultset.33")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public InputStream getBinaryStream(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.34") + Messages.getString("JavaResultset.35")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Blob getBlob(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.36") + Messages.getString("JavaResultset.37")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Blob getBlob(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.38") + Messages.getString("JavaResultset.39")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean getBoolean(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.40") + Messages.getString("JavaResultset.41")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public boolean getBoolean(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.42") + Messages.getString("JavaResultset.43")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public byte getByte(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.44") + Messages.getString("JavaResultset.45")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public byte getByte(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.46") + Messages.getString("JavaResultset.47")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public byte[] getBytes(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.48") + Messages.getString("JavaResultset.49")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public byte[] getBytes(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.50") + Messages.getString("JavaResultset.51")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Reader getCharacterStream(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.52") + Messages.getString("JavaResultset.53")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Reader getCharacterStream(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.54") + Messages.getString("JavaResultset.55")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Clob getClob(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.56") + Messages.getString("JavaResultset.57")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Clob getClob(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.58") + Messages.getString("JavaResultset.59")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getConcurrency() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.60") + Messages.getString("JavaResultset.61")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public String getCursorName() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.62") + Messages.getString("JavaResultset.63")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Date getDate(final int columnIndex) throws SQLException { // ========
        this.Checker();
        if (columnIndex-1 < resultSet.GetTableSize() && this.isValidRow()
                && (resultSet.getFromTable(columnIndex-1) instanceof IsDate
                || (resultSet.getFromTable(columnIndex-1).getColumn().get(0)).equals(Messages.getString("JavaResultset.64")))) { //$NON-NLS-1$
            Object temp = resultSet.getFromTable(columnIndex-1).getColumn().get(rowIndex);
            if (temp.equals(Messages.getString("JavaResultset.65"))) { //$NON-NLS-1$
                return null;
            } else {
                return (Date) temp;
            }
        } else {
        	history.error(Messages.getString("JavaResultset.66")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.67")); //$NON-NLS-1$
        }

    }

    @Override
    public Date getDate(final String columnLabel) throws SQLException { // ========
        this.Checker();
        int columnIndex;
        if (resultSet.isIn(columnLabel)) {
            columnIndex = resultSet.getIndex(columnLabel);
        } else {
        	history.error(Messages.getString("JavaResultset.68")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.69")); //$NON-NLS-1$
        }
        return this.getDate(columnIndex+1);

    }

    @Override
    public Date getDate(final int arg0, final Calendar arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.70") + Messages.getString("JavaResultset.71")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Date getDate(final String arg0, final Calendar arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.72") + Messages.getString("JavaResultset.73")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public double getDouble(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.74") + Messages.getString("JavaResultset.75")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public double getDouble(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.76") + Messages.getString("JavaResultset.77")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getFetchDirection() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.78") + Messages.getString("JavaResultset.79")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getFetchSize() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.80") + Messages.getString("JavaResultset.81")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public float getFloat(final int columnIndex) throws SQLException { // ========
        this.Checker();
        if (columnIndex-1 < resultSet.GetTableSize() && this.isValidRow()
                && resultSet.getFromTable(columnIndex-1) instanceof IsFloat) {
            Object temp = resultSet.getFromTable(columnIndex-1).getColumn().get(rowIndex);
            if (temp == null) {
                return 0;
            } else {
                return (Float) temp;
            }
        } else {
            throw new SQLException(Messages.getString("JavaResultset.82")); //$NON-NLS-1$
        }

    }

    @Override
    public float getFloat(final String columnLabel) throws SQLException { // ========
        this.Checker();
        int columnIndex;
        if (resultSet.isIn(columnLabel)) {
            columnIndex = resultSet.getIndex(columnLabel);
        } else {
        	history.error(Messages.getString("JavaResultset.83")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.84")); //$NON-NLS-1$
        }
        return this.getFloat(columnIndex+1);

    }

    @Override
    public int getHoldability() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.85") + Messages.getString("JavaResultset.86")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getInt(final int columnIndex) throws SQLException { // ========
        this.Checker();
        if (columnIndex-1 < resultSet.GetTableSize() && this.isValidRow()
                && resultSet.getFromTable(columnIndex-1) instanceof IsInteger) {
            Object temp = resultSet.getFromTable(columnIndex-1).getColumn().get(rowIndex);
            if (temp == null) {
                return 0;
            } else {
                return (Integer) temp;
            }
        } else {
        	history.error(Messages.getString("JavaResultset.87")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.88")); //$NON-NLS-1$
        }

    }

    @Override
    public int getInt(final String columnLabel) throws SQLException { // ========
        this.Checker();
        int columnIndex;
        if (resultSet.isIn(columnLabel)) {
            columnIndex = resultSet.getIndex(columnLabel);
        } else {
            throw new SQLException(Messages.getString("JavaResultset.89")); //$NON-NLS-1$
        }
        return this.getInt(columnIndex+1);

    }

    @Override
    public long getLong(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.90") + Messages.getString("JavaResultset.91")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public long getLong(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.92") + Messages.getString("JavaResultset.93")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public ResultSetMetaData getMetaData() throws SQLException { // ========
        this.Checker();
        return meta;
    }

    @Override
    public Reader getNCharacterStream(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.94") + Messages.getString("JavaResultset.95")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Reader getNCharacterStream(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.96") + Messages.getString("JavaResultset.97")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public NClob getNClob(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.98") + Messages.getString("JavaResultset.99")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public NClob getNClob(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.100") + Messages.getString("JavaResultset.101")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public String getNString(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.102") + Messages.getString("JavaResultset.103")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public String getNString(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.104") + Messages.getString("JavaResultset.105")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Object getObject(final int columnIndex) throws SQLException { // ========
        this.Checker();
        if (columnIndex-1 < resultSet.GetTableSize() && this.isValidRow()) {
            Object temp = resultSet.getFromTable(columnIndex-1).getColumn().get(rowIndex);
            if (temp == null) {
                return null;
            } else {
                return temp;
            }
        } else {
        	history.error(Messages.getString("JavaResultset.106")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.107")); //$NON-NLS-1$
        }

    }

    @Override
    public Object getObject(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.108") + Messages.getString("JavaResultset.109")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Object getObject(final int arg0, final Map<String, Class<?>> arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.110") + Messages.getString("JavaResultset.111")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Object getObject(final String arg0, final Map<String, Class<?>> arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.112") + Messages.getString("JavaResultset.113")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public <T> T getObject(final int arg0, final Class<T> arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.114") + Messages.getString("JavaResultset.115")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public <T> T getObject(final String arg0, final Class<T> arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.116") + Messages.getString("JavaResultset.117")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Ref getRef(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.118") + Messages.getString("JavaResultset.119")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Ref getRef(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.120") + Messages.getString("JavaResultset.121")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.122") + Messages.getString("JavaResultset.123")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public RowId getRowId(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.124") + Messages.getString("JavaResultset.125")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public RowId getRowId(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.126") + Messages.getString("JavaResultset.127")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public SQLXML getSQLXML(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.128") + Messages.getString("JavaResultset.129")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public SQLXML getSQLXML(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.130") + Messages.getString("JavaResultset.131")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public short getShort(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.132") + Messages.getString("JavaResultset.133")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public short getShort(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.134") + Messages.getString("JavaResultset.135")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Statement getStatement() throws SQLException { // ========
        this.Checker();
        return father;

    }

    @Override
    public String getString(final int columnIndex) throws SQLException { // ========
        this.Checker();
        if (columnIndex-1 < resultSet.GetTableSize() && this.isValidRow()
                && resultSet.getFromTable(columnIndex-1) instanceof IsString) {
            Object temp = resultSet.getFromTable(columnIndex-1).getColumn().get(rowIndex);
            if (temp == null) {
                return null;
            } else {
                return (String) temp;
            }
        } else {
        	history.error(Messages.getString("JavaResultset.136")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.137")); //$NON-NLS-1$
        }

    }

    @Override
    public String getString(final String columnLabel) throws SQLException { // ========
        this.Checker();
        int columnIndex;
        if (resultSet.isIn(columnLabel)) {
            columnIndex = resultSet.getIndex(columnLabel);
        } else {
        	history.error(Messages.getString("JavaResultset.138")); //$NON-NLS-1$
            throw new SQLException(Messages.getString("JavaResultset.139")); //$NON-NLS-1$
        }
        return this.getString(columnIndex+1);

    }

    @Override
    public Time getTime(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.140") + Messages.getString("JavaResultset.141")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Time getTime(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.142") + Messages.getString("JavaResultset.143")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Time getTime(final int arg0, final Calendar arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.144") + Messages.getString("JavaResultset.145")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Time getTime(final String arg0, final Calendar arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.146") + Messages.getString("JavaResultset.147")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Timestamp getTimestamp(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.148") + Messages.getString("JavaResultset.149")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Timestamp getTimestamp(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.150") + Messages.getString("JavaResultset.151")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Timestamp getTimestamp(final int arg0, final Calendar arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.152") + Messages.getString("JavaResultset.153")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public Timestamp getTimestamp(final String arg0, final Calendar arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.154") + Messages.getString("JavaResultset.155")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public int getType() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.156") + Messages.getString("JavaResultset.157")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public URL getURL(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.158") + Messages.getString("JavaResultset.159")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public URL getURL(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.160") + Messages.getString("JavaResultset.161")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public InputStream getUnicodeStream(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.162") + Messages.getString("JavaResultset.163")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    @Override
    public InputStream getUnicodeStream(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.164")); //$NON-NLS-1$
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.165")); //$NON-NLS-1$

    }

    @Override
    public void insertRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.166")); //$NON-NLS-1$

    }

    @Override
    public boolean isAfterLast() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0 && rowIndex == resultSet.getNumberOfRows()) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isBeforeFirst() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0 && rowIndex == -1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isClosed() throws SQLException { // ========

        return isClosed;

    }

    @Override
    public boolean isFirst() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0 && rowIndex == 0) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean isLast() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0 && rowIndex == resultSet.getNumberOfRows() - 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean last() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() != 0) {
            rowIndex = resultSet.getNumberOfRows() - 1;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void moveToCurrentRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.167")); //$NON-NLS-1$

    }

    @Override
    public void moveToInsertRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.168")); //$NON-NLS-1$

    }

    @Override
    public boolean next() throws SQLException { // ========
        this.Checker();
        if (resultSet.getNumberOfRows() > rowIndex) {
            rowIndex++;
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean previous() throws SQLException { // ========
        this.Checker();
        if (0 <= rowIndex && resultSet.getNumberOfRows() != 0/* && this.isValidRow()*/) {
            rowIndex--;
            return true;
        }
        return false;

    }

    @Override
    public void refreshRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.169")); //$NON-NLS-1$

    }

    @Override
    public boolean relative(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.170")); //$NON-NLS-1$

    }

    @Override
    public boolean rowDeleted() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.171")); //$NON-NLS-1$

    }

    @Override
    public boolean rowInserted() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.172")); //$NON-NLS-1$

    }

    @Override
    public boolean rowUpdated() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.173")); //$NON-NLS-1$

    }

    @Override
    public void setFetchDirection(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.174")); //$NON-NLS-1$

    }

    @Override
    public void setFetchSize(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.175")); //$NON-NLS-1$

    }

    @Override
    public void updateArray(final int arg0, final Array arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.176")); //$NON-NLS-1$

    }

    @Override
    public void updateArray(final String arg0, final Array arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.177")); //$NON-NLS-1$

    }

    @Override
    public void updateAsciiStream(final int arg0, final InputStream arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.178")); //$NON-NLS-1$

    }

    @Override
    public void updateAsciiStream(final String arg0, final InputStream arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.179")); //$NON-NLS-1$

    }

    @Override
    public void updateAsciiStream(final int arg0, final InputStream arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.180")); //$NON-NLS-1$

    }

    @Override
    public void updateAsciiStream(final String arg0, final InputStream arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.181")); //$NON-NLS-1$

    }

    @Override
    public void updateAsciiStream(final int arg0, final InputStream arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.182")); //$NON-NLS-1$

    }

    @Override
    public void updateAsciiStream(final String arg0, final InputStream arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.183")); //$NON-NLS-1$

    }

    @Override
    public void updateBigDecimal(final int arg0, final BigDecimal arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.184")); //$NON-NLS-1$

    }

    @Override
    public void updateBigDecimal(final String arg0, final BigDecimal arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.185")); //$NON-NLS-1$

    }

    @Override
    public void updateBinaryStream(final int arg0, final InputStream arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.186")); //$NON-NLS-1$

    }

    @Override
    public void updateBinaryStream(final String arg0, final InputStream arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.187")); //$NON-NLS-1$

    }

    @Override
    public void updateBinaryStream(final int arg0, final InputStream arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.188")); //$NON-NLS-1$

    }

    @Override
    public void updateBinaryStream(final String arg0, final InputStream arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.189")); //$NON-NLS-1$

    }

    @Override
    public void updateBinaryStream(final int arg0, final InputStream arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.190")); //$NON-NLS-1$

    }

    @Override
    public void updateBinaryStream(final String arg0, final InputStream arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.191")); //$NON-NLS-1$

    }

    @Override
    public void updateBlob(final int arg0, final Blob arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.192")); //$NON-NLS-1$

    }

    @Override
    public void updateBlob(final String arg0, final Blob arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.193")); //$NON-NLS-1$

    }

    @Override
    public void updateBlob(final int arg0, final InputStream arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.194")); //$NON-NLS-1$

    }

    @Override
    public void updateBlob(final String arg0, final InputStream arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.195")); //$NON-NLS-1$

    }

    @Override
    public void updateBlob(final int arg0, final InputStream arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.196")); //$NON-NLS-1$

    }

    @Override
    public void updateBlob(final String arg0, final InputStream arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.197")); //$NON-NLS-1$

    }

    @Override
    public void updateBoolean(final int arg0, final boolean arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.198")); //$NON-NLS-1$

    }

    @Override
    public void updateBoolean(final String arg0, final boolean arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.199")); //$NON-NLS-1$

    }

    @Override
    public void updateByte(final int arg0, final byte arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.200")); //$NON-NLS-1$

    }

    @Override
    public void updateByte(final String arg0, final byte arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.201")); //$NON-NLS-1$

    }

    @Override
    public void updateBytes(final int arg0, final byte[] arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.202")); //$NON-NLS-1$

    }

    @Override
    public void updateBytes(final String arg0, final byte[] arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.203")); //$NON-NLS-1$

    }

    @Override
    public void updateCharacterStream(final int arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.204")); //$NON-NLS-1$

    }

    @Override
    public void updateCharacterStream(final String arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.205")); //$NON-NLS-1$

    }

    @Override
    public void updateCharacterStream(final int arg0, final Reader arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.206")); //$NON-NLS-1$

    }

    @Override
    public void updateCharacterStream(final String arg0, final Reader arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.207")); //$NON-NLS-1$

    }

    @Override
    public void updateCharacterStream(final int arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.208")); //$NON-NLS-1$

    }

    @Override
    public void updateCharacterStream(final String arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.209")); //$NON-NLS-1$

    }

    @Override
    public void updateClob(final int arg0, final Clob arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.210")); //$NON-NLS-1$

    }

    @Override
    public void updateClob(final String arg0, final Clob arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.211")); //$NON-NLS-1$

    }

    @Override
    public void updateClob(final int arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.212")); //$NON-NLS-1$

    }

    @Override
    public void updateClob(final String arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.213")); //$NON-NLS-1$

    }

    @Override
    public void updateClob(final int arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.214")); //$NON-NLS-1$

    }

    @Override
    public void updateClob(final String arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.215")); //$NON-NLS-1$

    }

    @Override
    public void updateDate(final int arg0, final Date arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.216")); //$NON-NLS-1$

    }

    @Override
    public void updateDate(final String arg0, final Date arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.217")); //$NON-NLS-1$

    }

    @Override
    public void updateDouble(final int arg0, final double arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.218")); //$NON-NLS-1$

    }

    @Override
    public void updateDouble(final String arg0, final double arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.219")); //$NON-NLS-1$

    }

    @Override
    public void updateFloat(final int arg0, final float arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.220")); //$NON-NLS-1$

    }

    @Override
    public void updateFloat(final String arg0, final float arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.221")); //$NON-NLS-1$

    }

    @Override
    public void updateInt(final int arg0, final int arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.222")); //$NON-NLS-1$

    }

    @Override
    public void updateInt(final String arg0, final int arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.223")); //$NON-NLS-1$

    }

    @Override
    public void updateLong(final int arg0, final long arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.224")); //$NON-NLS-1$

    }

    @Override
    public void updateLong(final String arg0, final long arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.225")); //$NON-NLS-1$

    }

    @Override
    public void updateNCharacterStream(final int arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.226")); //$NON-NLS-1$

    }

    @Override
    public void updateNCharacterStream(final String arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.227")); //$NON-NLS-1$

    }

    @Override
    public void updateNCharacterStream(final int arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.228")); //$NON-NLS-1$

    }

    @Override
    public void updateNCharacterStream(final String arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.229")); //$NON-NLS-1$

    }

    @Override
    public void updateNClob(final int arg0, final NClob arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.230")); //$NON-NLS-1$

    }

    @Override
    public void updateNClob(final String arg0, final NClob arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.231")); //$NON-NLS-1$

    }

    @Override
    public void updateNClob(final int arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.232")); //$NON-NLS-1$

    }

    @Override
    public void updateNClob(final String arg0, final Reader arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.233")); //$NON-NLS-1$

    }

    @Override
    public void updateNClob(final int arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.234")); //$NON-NLS-1$

    }

    @Override
    public void updateNClob(final String arg0, final Reader arg1, final long arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.235")); //$NON-NLS-1$

    }

    @Override
    public void updateNString(final int arg0, final String arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.236")); //$NON-NLS-1$

    }

    @Override
    public void updateNString(final String arg0, final String arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.237")); //$NON-NLS-1$

    }

    @Override
    public void updateNull(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.238")); //$NON-NLS-1$

    }

    @Override
    public void updateNull(final String arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.239")); //$NON-NLS-1$

    }

    @Override
    public void updateObject(final int arg0, final Object arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.240")); //$NON-NLS-1$

    }

    @Override
    public void updateObject(final String arg0, final Object arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.241")); //$NON-NLS-1$

    }

    @Override
    public void updateObject(final int arg0, final Object arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.242")); //$NON-NLS-1$

    }

    @Override
    public void updateObject(final String arg0, final Object arg1, final int arg2) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.243")); //$NON-NLS-1$

    }

    @Override
    public void updateRef(final int arg0, final Ref arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.244")); //$NON-NLS-1$

    }

    @Override
    public void updateRef(final String arg0, final Ref arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.245")); //$NON-NLS-1$

    }

    @Override
    public void updateRow() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.246")); //$NON-NLS-1$

    }

    @Override
    public void updateRowId(final int arg0, final RowId arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.247")); //$NON-NLS-1$

    }

    @Override
    public void updateRowId(final String arg0, final RowId arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.248")); //$NON-NLS-1$

    }

    @Override
    public void updateSQLXML(final int arg0, final SQLXML arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.249")); //$NON-NLS-1$

    }

    @Override
    public void updateSQLXML(final String arg0, final SQLXML arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.250")); //$NON-NLS-1$

    }

    @Override
    public void updateShort(final int arg0, final short arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.251")); //$NON-NLS-1$

    }

    @Override
    public void updateShort(final String arg0, final short arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.252")); //$NON-NLS-1$

    }

    @Override
    public void updateString(final int arg0, final String arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.253")); //$NON-NLS-1$

    }

    @Override
    public void updateString(final String arg0, final String arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.254")); //$NON-NLS-1$

    }

    @Override
    public void updateTime(final int arg0, final Time arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.255")); //$NON-NLS-1$

    }

    @Override
    public void updateTime(final String arg0, final Time arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.256")); //$NON-NLS-1$

    }

    @Override
    public void updateTimestamp(final int arg0, final Timestamp arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.257")); //$NON-NLS-1$

    }

    @Override
    public void updateTimestamp(final String arg0, final Timestamp arg1) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.258")); //$NON-NLS-1$

    }

    @Override
    public boolean wasNull() throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultset.259")); //$NON-NLS-1$

    }

}