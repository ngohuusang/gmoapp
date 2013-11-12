package vn.gmostore.api.mvc;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.gmostore.api.service.PlatformService;
import vn.gmostore.api.util.ApiConstants;
import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dispatch.GetResults;
import vn.gmostore.basic.dto.Dto;
import vn.gmostore.basic.dto.PlatformDto;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

@Controller
public class PlatformController {

    @Autowired
    PlatformService platformService;

    @RequestMapping(value = "/platforms/{id}",//
    method = RequestMethod.GET,// Only response on GET
    consumes = MediaType.APPLICATION_JSON_VALUE //Need to set Header: "Content-Type: application/json" to get right content type
    )
    @Cacheable(cacheName = "platformCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<PlatformDto> getPlatformById(//
            @PathVariable Long id) throws IOException {

        PlatformDto platformDto = platformService.getBy(id.intValue());

        return new GetResult<PlatformDto>(platformDto);
    }

    @RequestMapping(value = "/platforms", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheName = "platformsCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResults<PlatformDto> getPlatforms(//
            @RequestParam(value = "offset", required = false, defaultValue = ApiConstants.DEFAULT_OFFSET) Long offset,//
            @RequestParam(value = "limit", required = false, defaultValue = ApiConstants.DEFAULT_LIMIT) Long limit) {

        List<PlatformDto> platformDtos = platformService.getPlatforms(offset.intValue(), limit.intValue());

        return new GetResults<PlatformDto>(platformDtos);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/platforms")
    @TriggersRemove(cacheName = "platformsCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<Dto> addPlatform(@RequestBody @Valid PlatformDto platformDto) {

        PlatformDto result = platformService.save(platformDto);

        return new GetResult<Dto>(result, HttpStatus.CREATED.value());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/platforms/{id}")
    @TriggersRemove(cacheName = { "platformCache", "platformsCache" }, keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<PlatformDto> updatePlatform(@RequestBody @Valid PlatformDto platformDto, @PathVariable Long id) {

        PlatformDto dto = platformService.saveOrCreate(platformDto);

        return new GetResult<PlatformDto>(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/platforms/{id}")
    @TriggersRemove(cacheName = { "platformCache", "platformsCache" }, keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<Dto> removePlatform(@PathVariable Long id, @RequestParam(value = "permalink", defaultValue = "false") Boolean permalink) {
        platformService.delete(id.intValue(), permalink);

        return new GetResult<Dto>(null);
    }

}
