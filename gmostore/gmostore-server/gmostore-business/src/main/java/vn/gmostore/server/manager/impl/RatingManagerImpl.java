/**
 *
 */
package vn.gmostore.server.manager.impl;

import java.util.Date;

import javax.persistence.NoResultException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import vn.gmostore.basic.model.Product;
import vn.gmostore.basic.model.Rating;
import vn.gmostore.basic.model.User;
import vn.gmostore.server.dao.ProductDao;
import vn.gmostore.server.dao.RatingDao;
import vn.gmostore.server.dao.UserDao;
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

    @Autowired
    UserDao userDao;

    @Autowired
    ProductDao productDao;

    @Override
    public Rating rate(Integer productId, String username, int mark) {
        Assert.notNull(productId, "Product must be not null");
        Assert.notNull(username, "Usernate must be not null");
        User user = userDao.getUserByUsername(username);
        if (user == null) {
            throw new NoResultException("User with username=" + username + " not found!");
        }

        Product product = productDao.getById(productId, false);
        if (product == null) {
            throw new NoResultException("Product with id=" + productId + " not found!");
        }

        Rating rating = new Rating(user, product, 0, new Date().getTime(), mark);
        logger.info("Save rating of userid=" + rating.getUser().getId() + " for productid=" + rating.getProduct().getId());
        Rating saved = ratingDao.save(rating, true);
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
