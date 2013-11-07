/**
 *
 */
package vn.gmostore.server.manager.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import vn.gmostore.basic.model.Rating;
import vn.gmostore.server.dao.RatingDao;
import vn.gmostore.server.manager.RatingManager;

/**
 *
 *
 */
@Repository("ratingManager")
public class RatingManagerImpl implements RatingManager {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    RatingDao ratingDao;

    @Override
    public Rating saveOrUpdate(Rating rating, boolean flush) {
        Assert.notNull(rating, "Platfom must be not null");
        logger.info("Save rating of userid=" + rating.getUser().getId() + " for productid=" + rating.getProduct().getId());
        Rating saved = ratingDao.saveOrUpdate(rating, flush);
        logger.info("Rating with id=" + saved.getId() + " is saved!");
        return saved;
    }

    @Override
    public void delete(Integer ratingId) {
        Assert.notNull(ratingId, "Rating id must be not null");
        ratingDao.delete(ratingId);
    }

    @Override
    public int getAverage(Integer productId) {
        Assert.notNull(productId, "Product id must be not null");
        return ratingDao.getAverage(productId);
    }

    @Override
    public Rating getBy(Integer productId, String username) {
        Assert.notNull(productId, "Product id must be not null");
        Assert.notNull(username, "Username must be not null");
        return ratingDao.getBy(productId, username);
    }

}
