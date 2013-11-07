/**
 *
 */
package vn.gmostore.server.manager;

import vn.gmostore.basic.model.Rating;

/**
 *
 */
public interface RatingManager {

    public int getAverage(Integer productId);

    public void delete(Integer productId);

    public Rating saveOrUpdate(Rating rating, boolean flush);

    public Rating getBy(Integer productId, String username);
}
