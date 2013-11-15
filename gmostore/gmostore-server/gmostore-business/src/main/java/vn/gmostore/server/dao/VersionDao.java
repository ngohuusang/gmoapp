/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;

import vn.gmostore.basic.model.Version;

/**
 *
 */
public interface VersionDao {

    void delete(Integer versionId);

    void trash(Integer versionId);

    void update(Version version, boolean flush);

    Version save(Version version, boolean flush);

    Version getById(Integer id, boolean inTrash);

    List<Version> getVersions(int offset, int limit, boolean inTrash);
}
