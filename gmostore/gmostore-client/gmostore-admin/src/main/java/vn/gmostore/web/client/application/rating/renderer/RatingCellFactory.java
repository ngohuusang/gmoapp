package vn.gmostore.web.client.application.rating.renderer;

import vn.gmostore.basic.dto.RatingDto;

import com.google.gwt.cell.client.ActionCell.Delegate;

public interface RatingCellFactory {
    RatingCell create(Delegate<RatingDto> delegate);
}
