package vn.gmostore.web.rebind;

import vn.gmostore.web.client.columninitializer.Column;

import com.google.gwt.core.ext.typeinfo.JMethod;

public class ColumnTuple {
    private JMethod method;
    private Column column;

    public JMethod getMethod() {
        return method;
    }

    public Column getColumn() {
        return column;
    }

    public String getCellCanonicalName() {
        return column.cellType().getCanonicalName();
    }

    public String getReturnCellCanonicalName() {
        return column.cellReturnType().getCanonicalName();
    }

    public String getMethodCapitalizedName() {
        return method.getName();
    }

    public static ColumnTuple createFrom(JMethod method) {
        ColumnTuple columnTuple = new ColumnTuple();

        columnTuple.method = method;
        columnTuple.column = method.getAnnotation(Column.class);

        return columnTuple;
    }
}
