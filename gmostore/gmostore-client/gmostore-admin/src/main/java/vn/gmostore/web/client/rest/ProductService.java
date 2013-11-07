package vn.gmostore.web.client.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dispatch.GetResults;
import vn.gmostore.basic.dto.NumberDto;
import vn.gmostore.basic.dto.ProductDetailDto;
import vn.gmostore.basic.dto.ProductDto;
import vn.gmostore.web.shared.rest.PathParameter;
import vn.gmostore.web.shared.rest.ResourcesPath;
import vn.gmostore.web.shared.rest.RestParameter;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.NoResult;
import com.gwtplatform.dispatch.shared.rest.RestService;

@Path("/platforms")
public interface ProductService extends RestService {
    @GET
    @Consumes(value = MediaType.APPLICATION_JSON)
    @Produces(value = MediaType.APPLICATION_JSON)
    Action<GetResults<ProductDto>> getProducts();

    @GET
    Action<GetResults<ProductDto>> getProducts(@QueryParam(RestParameter.OFFSET)
    int offset, @QueryParam(RestParameter.LIMIT)
    int limit);

    @GET
    @Path(ResourcesPath.COUNT)
    Action<GetResult<NumberDto<Integer>>> getProductsCount();

    @POST
    Action<GetResult<ProductDetailDto>> saveOrCreate(ProductDetailDto product);

    @DELETE
    @Path(PathParameter.ID)
    Action<NoResult> delete(@PathParam(RestParameter.ID)
    Integer productId);
}
