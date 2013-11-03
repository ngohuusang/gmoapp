package vn.gmostore.web.client.application.rating.ui;

import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.client.columninitializer.Column;
import vn.gmostore.web.client.columninitializer.ColumnInitializer;
import vn.gmostore.web.client.columninitializer.ColumnsDefinition;

import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;

@ColumnsDefinition(definitionFor = RatingDto.class)
public interface RatingColumnsDefinition extends ColumnInitializer<RatingDto> {
    @Column(cellType = NumberCell.class, cellReturnType = Number.class, headerName = "ID")
    public String getId();

    @Override
    @Column(cellType = TextCell.class, cellReturnType = String.class, headerName = "Product")
    public String toString();

    @Column(cellType = NumberCell.class, cellReturnType = Number.class, headerName = "Rating")
    public String getRating();
}
