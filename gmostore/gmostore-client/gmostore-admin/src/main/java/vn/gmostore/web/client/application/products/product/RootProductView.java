package vn.gmostore.web.client.application.products.product;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.gwtplatform.mvp.client.ViewImpl;

public class RootProductView extends ViewImpl implements RootProductPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, RootProductView> {
    }

    @UiField
    SimplePanel tabBarPanel;
    @UiField
    SimplePanel contentPanel;

    @Inject
    RootProductView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object slot, IsWidget content) {
        if (slot == RootProductPresenter.SLOT_TAB_BAR) {
            tabBarPanel.setWidget(content);
        } else if (slot == RootProductPresenter.TYPE_SetProductContent) {
            contentPanel.setWidget(content);
        }
    }
}
