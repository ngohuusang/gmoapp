package vn.gmostore.web.client.application.products;

import java.util.List;

import javax.inject.Inject;

import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.web.client.application.products.ProductsPresenter.MyView;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.cell.client.ActionCell.Delegate;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class ProductsView extends ViewWithUiHandlers<ProductsUiHandlers> implements MyView {
    interface Binder extends UiBinder<Widget, ProductsView> {
    }

    private static final int PAGE_SIZE = 10;

    @UiField(provided = true)
    CellTable<ProductDto> productGrid;

    @UiField(provided = true)
    SimplePager pager;

    private AsyncDataProvider<ProductDto> asyncDataProvider;

    @Inject
    ProductsView(Binder uiBinder) {
        initCarGrid();

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void initDataProvider() {
        asyncDataProvider = new AsyncDataProvider<ProductDto>() {
            @Override
            protected void onRangeChanged(HasData<ProductDto> display) {
                Range range = display.getVisibleRange();
                getUiHandlers().fetchData(range.getStart(), range.getLength());
            }
        };

        asyncDataProvider.addDataDisplay(productGrid);
    }

    @Override
    public HasData<ProductDto> getProductDisplay() {
        return productGrid;
    }

    @Override
    public void setProductsCount(Integer result) {
        asyncDataProvider.updateRowCount(result, true);
    }

    @Override
    public void displayProducts(int offset, List<ProductDto> products) {
        asyncDataProvider.updateRowData(offset, products);
    }

    @UiHandler("create")
    void onCreateClicked(ClickEvent ignored) {
        getUiHandlers().onCreate();
    }

    private void initCarGrid() {
        productGrid = new CellTable<ProductDto>();
        productGrid.setSelectionModel(new NoSelectionModel<ProductDto>());

        pager = new SimplePager(SimplePager.TextLocation.CENTER);
        pager.setDisplay(productGrid);
        pager.setPageSize(PAGE_SIZE);

        initDataColumns();
        initActionColumns();
    }

    private void initDataColumns() {
        Column<ProductDto, Number> idColumn = new Column<ProductDto, Number>(new NumberCell()) {
            @Override
            public Integer getValue(ProductDto product) {
                return product.getId();
            }
        };

        Column<ProductDto, String> productNameColumn = new Column<ProductDto, String>(new TextCell()) {
            @Override
            public String getValue(ProductDto product) {
                return product.getFullName();
            }
        };

        Column<ProductDto, String> statusColumn = new Column<ProductDto, String>(new TextCell()) {
            @Override
            public String getValue(ProductDto product) {
                return String.valueOf(product.getFileSize());
            }
        };

        productGrid.addColumn(idColumn, "ID");
        productGrid.addColumn(productNameColumn, "Name");
        productGrid.addColumn(statusColumn, "Size");
        productGrid.setColumnWidth(idColumn, 50, Unit.PX);
    }

    private void initActionColumns() {
        Cell<ProductDto> editCell = new ActionCell<ProductDto>("Edit", new Delegate<ProductDto>() {
            @Override
            public void execute(ProductDto product) {
                getUiHandlers().onEdit(product);
            }
        });

        Cell<ProductDto> deleteCell = new ActionCell<ProductDto>("Delete", new Delegate<ProductDto>() {
            @Override
            public void execute(ProductDto product) {
                Boolean confirm = Window.confirm("Are you sure you want to delete " + product.getFullName() + "?");

                if (confirm) {
                    getUiHandlers().onDelete(product);
                }
            }
        });

        IdentityColumn<ProductDto> editColumn = new IdentityColumn<ProductDto>(editCell);
        IdentityColumn<ProductDto> deleteColumn = new IdentityColumn<ProductDto>(deleteCell);

        productGrid.addColumn(editColumn, "Edit");
        productGrid.addColumn(deleteColumn, "Delete");

        productGrid.setColumnWidth(editColumn, 75, Unit.PX);
        productGrid.setColumnWidth(deleteColumn, 75, Unit.PX);
    }
}
