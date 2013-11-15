/**
 *
 */
package vn.gmostore.server.dao;

import vn.gmostore.basic.model.Rating;

/**
 *
 */
public interface RatingDao {

    int getAverage(Integer productId);

    void delete(Integer productId);

    Rating save(Rating rating, boolean flush);

    void update(Rating rating, boolean flush);

    Rating getBy(Integer productId, String username);
}
