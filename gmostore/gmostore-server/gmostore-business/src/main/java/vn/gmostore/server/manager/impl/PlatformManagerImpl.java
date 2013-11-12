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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import vn.gmostore.basic.model.Platform;
import vn.gmostore.server.dao.PlatformDao;
import vn.gmostore.server.manager.PlatformManager;

/**
 *
 *
 */
@Repository("platformManager")
@Transactional(rollbackFor = Throwable.class)
public class PlatformManagerImpl implements PlatformManager {

    protected final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    PlatformDao platformDao;

    @Override
    public Platform getById(Integer id) {
        Assert.notNull(id, "The platform id must be not null");
        logger.info("Get platform by id=" + id);
        return platformDao.getById(id, false);
    }

    @Override
    public List<Platform> getPlatforms(int offset, int limit) {
        List<Platform> products = platformDao.getPlatforms(offset, limit, false);
        logger.info("Get all platforms with offset=" + offset + " limit=" + limit + " order by order");
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
    public void delete(Integer platformId, boolean permalink) {
        Assert.notNull(platformId, "Platfom id must be not null");
        if (permalink) {
            logger.info("Delete platform with id=" + platformId);
            platformDao.delete(platformId);
        } else {
            logger.info("Move platform with id=" + platformId + " to trash!");
            platformDao.trash(platformId);
        }
    }

    @Override
    public Platform save(Platform platform, boolean flush) {
        Assert.notNull(platform, "Platform must be not null");

        logger.info("Save platform with name=" + platform.getName());
        Platform saved = platformDao.save(platform, flush);
        logger.info("Platform with id=" + saved.getId() + " is saved!");

        return saved;
    }

    @Override
    public void update(Platform platform, boolean flush) {
        Assert.notNull(platform, "Platform must be not null");

        logger.info("Update platform with id=" + platform.getId());
        platformDao.update(platform, flush);
    }
}
