package vn.gmostore.web.client.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dto.CurrentUserDto;
import vn.gmostore.web.shared.dispatch.LogInRequest;
import vn.gmostore.web.shared.dispatch.LogInResult;
import vn.gmostore.web.shared.rest.ResourcesPath;

import com.gwtplatform.dispatch.shared.Action;
import com.gwtplatform.dispatch.shared.NoResult;
import com.gwtplatform.dispatch.shared.rest.RestService;

@Path(ResourcesPath.SESSION)
public interface SessionService extends RestService {
    @DELETE
    Action<NoResult> logout();

    @GET
    Action<GetResult<CurrentUserDto>> getCurrentUser();

    @POST
    Action<LogInResult> login(LogInRequest logInRequest);
}
