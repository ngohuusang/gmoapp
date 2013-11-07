package vn.gmostore.api.service;

import java.util.List;

import vn.gmostore.basic.dto.PlatformDto;

public interface PlatformService {
    PlatformDto getBy(Integer productId);

    List<PlatformDto> getPlatforms(int offset, int limit);

    PlatformDto saveOrCreate(PlatformDto platform);

    void delete(Integer platformId);
}
