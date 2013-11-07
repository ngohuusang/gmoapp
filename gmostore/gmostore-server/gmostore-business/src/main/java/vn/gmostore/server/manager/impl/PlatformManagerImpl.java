/**
 *
 */
package vn.gmostore.server.manager.impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import vn.gmostore.basic.model.Platform;
import vn.gmostore.server.dao.PlatformDao;
import vn.gmostore.server.manager.PlatformManager;

/**
 *
 *
 */
@Repository("platformManager")
public class PlatformManagerImpl implements PlatformManager {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    PlatformDao platformDao;

    @Override
    public Platform getById(Integer id) {
        Assert.notNull(id, "The platform id must be not null");
        logger.info("Get platform by id: " + id);
        return platformDao.getById(id);
    }

    @Override
    public List<Platform> getPlatforms(int offset, int limit) {
        //TODO: Sort product by???
        List<Platform> products = platformDao.getPlatforms(offset, limit);
        logger.info("Get all platforms with offset=" + offset + " limit=" + limit);
        if (products != null) {
            logger.info("Result size=" + products.size());
            return products;
        }

        return Collections.emptyList();
    }

    @Override
    public Platform saveOrUpdate(Platform platform, boolean flush) {
        Assert.notNull(platform, "Platfom must be not null");
        logger.info("Save platform with name=" + platform.getName());
        Platform saved = platformDao.saveOrUpdate(platform, flush);
        logger.info("Platform with id=" + saved.getId() + " is saved!");
        return saved;
    }

    @Override
    public void delete(Integer platformId) {
        Assert.notNull(platformId, "Platfom id must be not null");
        platformDao.delete(platformId);
    }
}
