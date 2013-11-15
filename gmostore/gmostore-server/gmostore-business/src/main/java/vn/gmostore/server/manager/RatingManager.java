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

    public Rating rate(Integer productId, String username, int mark);

    public Rating getBy(Integer productId, String username);
}
