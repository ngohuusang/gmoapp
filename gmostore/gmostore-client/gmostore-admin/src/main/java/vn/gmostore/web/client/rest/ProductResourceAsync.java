package vn.gmostore.web.client.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Options;
import org.fusesource.restygwt.client.RestService;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dto.PlatformDto;

public interface ProductResourceAsync extends RestService {

    @GET
    @Path("/platforms/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    //    @Produces(MediaType.APPLICATION_JSON)
    //    @HeaderParam(value = "Content-Type: application/json")
    @Options(timeout = 5000)
    public void getById(@PathParam("id")
    long id, MethodCallback<GetResult<PlatformDto>> methodCallback);

}
