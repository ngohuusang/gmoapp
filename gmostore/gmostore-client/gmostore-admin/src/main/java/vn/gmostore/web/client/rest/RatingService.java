package vn.gmostore.web.client.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dispatch.GetResults;
import vn.gmostore.basic.dto.RatingDto;
import vn.gmostore.web.shared.rest.PathParameter;
import vn.gmostore.web.shared.rest.ResourcesPath;
import vn.gmostore.web.shared.rest.RestParameter;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.NoResult;
import com.gwtplatform.dispatch.shared.rest.RestService;

@Path(ResourcesPath.RATING)
public interface RatingService extends RestService {
    @GET
    Action<GetResults<RatingDto>> getRatings();

    @GET
    @Path(PathParameter.ID)
    Action<GetResult<RatingDto>> get(@PathParam(RestParameter.ID)
    Long id);

    @POST
    Action<GetResult<RatingDto>> saveOrCreate(RatingDto rating);

    @DELETE
    @Path(PathParameter.ID)
    Action<NoResult> delete(@PathParam(RestParameter.ID)
    Integer id);
}
