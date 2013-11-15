/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;

import vn.gmostore.basic.model.Price;

/**
 *
 */
public interface PriceDao {

    Price getLatestBy(Integer productId);

    void delete(Integer priceId);

    void trash(Integer priceId);

    Price save(Price price, boolean flush);

    void update(Price price, boolean flush);

    List<Price> getPrices(Integer productId);

}
