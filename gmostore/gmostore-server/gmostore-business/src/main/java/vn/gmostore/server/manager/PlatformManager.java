/**
 *
 */
package vn.gmostore.server.manager;

import java.util.List;

import vn.gmostore.basic.model.Platform;

/**
 *
 */
public interface PlatformManager {

    public Platform getById(Integer id);

    public List<Platform> getPlatforms(int offset, int limit);

    public Platform saveOrUpdate(Platform platform, boolean flush);

    public Platform save(Platform platform, boolean flush);

    public void update(Platform platform, boolean flush);

    public void delete(Integer platformId, boolean permalink);

}
