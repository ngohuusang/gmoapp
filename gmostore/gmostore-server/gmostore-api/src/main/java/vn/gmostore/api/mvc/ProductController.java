package vn.gmostore.api.mvc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.gmostore.api.service.ProductService;
import vn.gmostore.api.util.ApiConstants;
import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dispatch.GetResults;
import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.basic.dto.ProductDto;

import com.googlecode.ehcache.annotations.Cacheable;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    //    @TriggersRemove(cacheName="infoCache", keyGeneratorName="infoKeyGenerator")//TODO: update, delete, insert

    @RequestMapping(value = "platforms/{platfomId}/products/{id}",//
    method = RequestMethod.GET// Only response on GET
    //    consumes = MediaType.APPLICATION_JSON_VALUE //Need to set Header: "Content-Type: application/json" to get right content type
    )
    @Cacheable(cacheName = "productCache", keyGeneratorName = "keyGenerator")
    @ResponseBody
    public GetResult<ProductDetailDto> getProductDetailsById(//
            @PathVariable
            Integer platformId, @PathVariable
            Integer id) throws IOException {
        ProductDetailDto productDetailDto = productService.getProductDetailsBy(platformId, id);
        GetResult<ProductDetailDto> product = new GetResult<ProductDetailDto>(productDetailDto);
        return product;
    }

    @RequestMapping(value = "platforms/{platfomId}/products",//
    params = { "offset", "limit", "orderBy", "category", "orderType" },//
    method = RequestMethod.GET,//
    consumes = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable(cacheName = "productsCache", keyGeneratorName = "keyGenerator")
    //TODO: Cache
    @ResponseBody
    public GetResults<ProductDto> getProducts(//
            @PathVariable
            Integer platfomId,//
            @RequestParam(required = false)
            String orderBy,//
            @RequestParam(required = false)
            String orderType,//
            @RequestParam(required = false)
            Integer category,//
            @RequestParam(required = false, defaultValue = ApiConstants.DEFAULT_OFFSET)
            int offset,//
            @RequestParam(required = false, defaultValue = ApiConstants.DEFAULT_LIMIT)
            int limit) {

        //        List<ProductDto> productDtos = productService.getProducts(platfomId, category, offset, limit, orderBy, orderType);

        //        return new GetResults<ProductDto>(productDtos);
        return null;
    }

    @RequestMapping(value = "/products",//
    //    params = { "offset", "limit", "orderBy", "category", "orderType" },//
    method = RequestMethod.GET,//
    consumes = MediaType.APPLICATION_JSON_VALUE)
    //    @Cacheable(cacheName = "productsCache", keyGeneratorName = "keyGenerator")
    //TODO: Cache
    @ResponseBody
    public GetResults<ProductDto> getProducts(//
    //            @RequestParam(required = false, defaultValue = ApiConstants.DEFAULT_OFFSET)
    //            int offset,//
    //            @RequestParam(required = false, defaultValue = ApiConstants.DEFAULT_LIMIT)
    //            int limit) {
    ) {
        //        List<ProductDto> productDtos = productService.getProducts(platfomId, category, offset, limit, orderBy, orderType);

        //        return new GetResults<ProductDto>(productDtos);
        ProductDto dto = new ProductDto();
        dto.setFullName("Sang");
        List<ProductDto> dtos = new ArrayList<ProductDto>();
        dtos.add(dto);
        return new GetResults<ProductDto>(dtos);
    }

}
