package vn.gmostore.web.client.columninitializer;

import com.google.gwt.user.cellview.client.CellTable;

public interface ColumnInitializer<T> {
    void initializeColumns(CellTable<T> table);
}
