package vn.gmostore.web.client.resources;

import com.google.gwt.user.cellview.client.CellList;

public interface NavigationListStyle extends CellList.Resources {
    @Override
    @Source({ CellList.Style.DEFAULT_CSS, "navigationListStyle.css" })
    ListStyle cellListStyle();

    interface ListStyle extends CellList.Style {
    }
}
