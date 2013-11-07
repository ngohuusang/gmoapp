package vn.gmostore.api.mvc;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
            @PathVariable
            Long id) throws IOException {
        PlatformDto platformDto = platformService.getBy(id.intValue());

        return new GetResult<PlatformDto>(platformDto);
    }

    @RequestMapping(value = "/platforms",//
    params = { "offset", "limit" },//
    method = RequestMethod.GET,//
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheName = "platformsCache", keyGeneratorName = "keyGenerator")
    //TODO: Cache
    @ResponseBody
    public GetResults<PlatformDto> getPlatforms(//
            @RequestParam(required = false, defaultValue = ApiConstants.DEFAULT_OFFSET)
            int offset,//
            @RequestParam(required = false, defaultValue = ApiConstants.DEFAULT_LIMIT)
            int limit) {

        List<PlatformDto> platformDtos = platformService.getPlatforms(offset, limit);

        return new GetResults<PlatformDto>(platformDtos);
    }

    @RequestMapping(value = "/platforms",//
    method = RequestMethod.GET,//
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheName = "platformsCache", keyGeneratorName = "keyGenerator")
    //TODO: Cache
    @ResponseBody
    public GetResults<PlatformDto> getPlatforms() {

        List<PlatformDto> platformDtos = platformService.getPlatforms(0, 1000);

        return new GetResults<PlatformDto>(platformDtos);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admin/platforms")
    @TriggersRemove(cacheName = "platformsCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<PlatformDto> addPlatform(@RequestBody
    PlatformDto platformDto) {

        PlatformDto dto = platformService.saveOrCreate(platformDto);

        return new GetResult<PlatformDto>(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/admin/platforms/{id}")
    @TriggersRemove(cacheName = "platformCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<PlatformDto> updateEmp(@RequestBody
    PlatformDto platformDto, @PathVariable
    String id) {
        PlatformDto dto = platformService.saveOrCreate(platformDto);

        return new GetResult<PlatformDto>(dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/admin/platforms/{id}")
    @TriggersRemove(cacheName = "platformCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public void removeEmp(@PathVariable
    Integer id) {
        platformService.delete(id);
    }

}
