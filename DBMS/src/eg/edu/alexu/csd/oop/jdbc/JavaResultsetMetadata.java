package eg.edu.alexu.csd.oop.jdbc;

import java.sql.SQLException;

import classes.TabelImp;
import interfaces.IsDate;
import interfaces.IsFloat;
import interfaces.IsInteger;
import interfaces.IsString;

public class JavaResultsetMetadata implements java.sql.ResultSetMetaData {

    private TabelImp meta;

    public JavaResultsetMetadata(TabelImp meta) {
        // TODO Auto-generated constructor stub
        this.meta = meta;
    }

    @Override
    public boolean isWrapperFor(final Class<?> iface) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.0")); //$NON-NLS-1$

    }

    @Override
    public <T> T unwrap(final Class<T> iface) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.1")); //$NON-NLS-1$

    }

    @Override
    public String getCatalogName(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.2")); //$NON-NLS-1$

    }

    @Override
    public String getColumnClassName(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.3")); //$NON-NLS-1$

    }

    @Override
    public int getColumnCount() throws SQLException { // ========
        return meta.getTable().size();
    }

    @Override
    public int getColumnDisplaySize(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.4")); //$NON-NLS-1$

    }

    @Override
    public String getColumnLabel(final int arg0) throws SQLException { // ========
        return meta.getFromTable(arg0).getName();
    }

    @Override
    public String getColumnName(final int arg0) throws SQLException { // ========
        return getColumnLabel(arg0);
    }

    @Override
    public int getColumnType(final int arg0) throws SQLException { // ========
        if (meta.getFromTable(arg0) instanceof IsDate) {
            return java.sql.Types.DATE;
        }
        if (meta.getFromTable(arg0) instanceof IsInteger) {
            return java.sql.Types.INTEGER;
        }
        if (meta.getFromTable(arg0) instanceof IsFloat) {
            return java.sql.Types.FLOAT;
        }
        if (meta.getFromTable(arg0) instanceof IsString) {
            return java.sql.Types.VARCHAR;
        }
        throw new SQLException();
    }

    @Override
    public String getColumnTypeName(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.5")); //$NON-NLS-1$

    }

    @Override
    public int getPrecision(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.6")); //$NON-NLS-1$

    }

    @Override
    public int getScale(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.7")); //$NON-NLS-1$

    }

    @Override
    public String getSchemaName(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.8")); //$NON-NLS-1$

    }

    @Override
    public String getTableName(final int arg0) throws SQLException { // ========
        return meta.GetTableName();
    }

    @Override
    public boolean isAutoIncrement(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.9")); //$NON-NLS-1$

    }

    @Override
    public boolean isCaseSensitive(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.10")); //$NON-NLS-1$

    }

    @Override
    public boolean isCurrency(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.11")); //$NON-NLS-1$

    }

    @Override
    public boolean isDefinitelyWritable(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.12")); //$NON-NLS-1$

    }

    @Override
    public int isNullable(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.13")); //$NON-NLS-1$

    }

    @Override
    public boolean isReadOnly(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.14")); //$NON-NLS-1$

    }

    @Override
    public boolean isSearchable(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.15")); //$NON-NLS-1$

    }

    @Override
    public boolean isSigned(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.16")); //$NON-NLS-1$

    }

    @Override
    public boolean isWritable(final int arg0) throws SQLException {
        throw new java.lang.UnsupportedOperationException(Messages.getString("JavaResultsetMetadata.17")); //$NON-NLS-1$

    }

}