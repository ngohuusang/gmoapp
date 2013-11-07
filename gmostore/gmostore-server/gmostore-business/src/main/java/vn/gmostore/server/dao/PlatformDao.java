/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;

import vn.gmostore.basic.model.Platform;

/**
 *
 */
public interface PlatformDao {

    public Platform getById(Integer id);

    public List<Platform> getPlatforms(int offset, int limit);

    public void delete(Integer platformId);

    public Platform saveOrUpdate(Platform platform, boolean flush);
}
