package vn.gmostore.web.rebind;

import java.util.ArrayList;
import java.util.List;

import vn.gmostore.web.client.columninitializer.Column;
import vn.gmostore.web.client.columninitializer.ColumnsDefinition;

import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;

public class ColumnsInitializerDefinitions {
    private ColumnsDefinition columnsInitializer;
    private List<ColumnTuple> columns;

    public List<ColumnTuple> getColumns() {
        return columns;
    }

    public String getDtoCanonicalName() {
        return columnsInitializer.definitionFor().getCanonicalName();
    }

    public static ColumnsInitializerDefinitions createFrom(JClassType type) {
        ColumnsInitializerDefinitions columnsInitializerDefinitions = new ColumnsInitializerDefinitions();

        columnsInitializerDefinitions.columnsInitializer = type.getAnnotation(ColumnsDefinition.class);

        List<ColumnTuple> columns = new ArrayList<ColumnTuple>();
        JMethod[] jMethods = type.getMethods();
        for (int i = 0; i < jMethods.length; i++) {
            JMethod method = jMethods[i];

            if (method.isAnnotationPresent(Column.class)) {
                ColumnTuple columnTuple = ColumnTuple.createFrom(method);

                columns.add(columnTuple);
            }
        }

        columnsInitializerDefinitions.columns = columns;

        return columnsInitializerDefinitions;
    }
}
