/**
 *
 */
package vn.gmostore.server.dao;

import java.util.List;
import java.util.Map;

import vn.gmostore.basic.model.DownloadLink;

/**
 *
 */
public interface DownloadLinkDao {

    void delete(Integer downloadLinkId);

    void trash(Integer downloadLinkId);

    void update(DownloadLink platform, boolean flush);

    DownloadLink save(DownloadLink platform, boolean flush);

    DownloadLink getById(Integer id, boolean inTrash);

    List<DownloadLink> getDownloadLinks(int offset, int limit, boolean inTrash);

    Map<Integer, Integer> getTopProductsDownloaded(Integer platformId, Integer categoryId, int limit);

    int getDownloadedCountBy(Integer productId);
}
