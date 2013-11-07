package vn.gmostore.api.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.gmostore.api.service.PlatformService;
import vn.gmostore.basic.dto.PlatformDto;
import vn.gmostore.basic.model.Platform;
import vn.gmostore.server.manager.PlatformManager;

@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    PlatformManager platformManager;

    @Override
    public PlatformDto getBy(Integer id) {
        if (id != null) {
            Platform platform = platformManager.getById(id);
            return new PlatformDto(platform);
        }
        return null;
    }

    @Override
    public List<PlatformDto> getPlatforms(int offset, int limit) {
        if (offset >= 0 && limit >= 0) {
            List<Platform> platforms = platformManager.getPlatforms(offset, limit);
            List<PlatformDto> result = new ArrayList<PlatformDto>(platforms.size());
            for (Platform platform : platforms) {
                result.add(new PlatformDto(platform));
            }
            return result;
        }

        return Collections.emptyList();
    }

    @Override
    public PlatformDto saveOrCreate(PlatformDto product) {
        //        if (product != null)
        //            platformManager.saveOrUpdate(product, true);
        return null;
    }

    @Override
    public void delete(Integer platformId) {
        if (platformId != null) {
            platformManager.delete(platformId);
        }

    }
}
