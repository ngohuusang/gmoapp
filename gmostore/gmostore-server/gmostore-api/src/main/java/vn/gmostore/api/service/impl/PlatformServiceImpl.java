package vn.gmostore.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
        Platform platform = platformManager.getById(id, false);
        return new PlatformDto(platform);
    }

    @Override
    public List<PlatformDto> getPlatforms(int offset, int limit) {
        List<Platform> platforms = platformManager.getPlatforms(offset, limit);
        List<PlatformDto> result = new ArrayList<PlatformDto>(platforms.size());
        for (Platform platform : platforms) {
            result.add(new PlatformDto(platform));
        }
        return result;
    }

    @Override
    public PlatformDto saveOrCreate(PlatformDto platformDto) {
        Assert.notNull(platformDto, "Platform must be not null");

        Platform platform = platformDto.from();
        if (!platform.isPersisted()) {
            platform.setCreateDate(new Date().getTime());
        } else {
            platform.setUpdateDate(new Date().getTime());
        }
        Platform saved = platformManager.saveOrUpdate(platform, true);
        return new PlatformDto(saved);
    }

    @Override
    public void delete(Integer platformId, boolean permalink) {
        platformManager.delete(platformId, permalink);
    }

    @Override
    public void update(PlatformDto platformDto) {

        Assert.notNull(platformDto, "Platform cannot be null");

        Platform platform = platformDto.from();
        platformManager.update(platform, true);
    }

    @Override
    public PlatformDto save(PlatformDto platformDto) {
        Assert.notNull(platformDto, "Platform cannot be null");

        Platform platform = platformDto.from();
        Platform saved = platformManager.save(platform, true);

        return new PlatformDto(saved);
    }
}
