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

    public Price getLatestBy(Integer productId);

    public void delete(Integer priceId);

    public Price saveOrUpdate(Price price, boolean flush);

    public List<Price> getPrices(Integer productId);

}
