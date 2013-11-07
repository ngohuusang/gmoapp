/**
 *
 */
package vn.gmostore.server.dao;

import vn.gmostore.basic.model.Rating;

/**
 *
 */
public interface RatingDao {

    public int getAverage(Integer productId);

    public void delete(Integer productId);

    public Rating saveOrUpdate(Rating rating, boolean flush);

    public Rating getBy(Integer productId, String username);
}
