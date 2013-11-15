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

    Platform getById(Integer id, boolean inTrash);

    List<Platform> getPlatforms(int offset, int limit);

    Platform saveOrUpdate(Platform platform, boolean flush);

    Platform save(Platform platform, boolean flush);

    void update(Platform platform, boolean flush);

    void delete(Integer platformId, boolean permalink);

}
