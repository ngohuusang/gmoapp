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

    void delete(Integer platformId);

    void trash(Integer platformId);

    Platform saveOrUpdate(Platform platform, boolean flush);

    void update(Platform platform, boolean flush);

    Platform save(Platform platform, boolean flush);

    Platform getById(Integer id, boolean inTrash);

    List<Platform> getPlatforms(int offset, int limit, boolean inTrash);
}
