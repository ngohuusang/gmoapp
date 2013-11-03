package vn.gmostore.api.mvc;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vn.gmostore.basic.dispatch.GetResult;
import vn.gmostore.basic.dto.ProductDto;

@Controller
public class UserController {

    @RequestMapping(value = "/api/user/logging",//
    method = RequestMethod.GET,// Only response on GET
    consumes = MediaType.APPLICATION_JSON_VALUE //Need to set Header: "Content-Type: application/json" to get right content type
    //    , produces = { MediaType.APPLICATION_JSON_VALUE })
    )
    //    @Cacheable(value="products", key="#student.name")
    //    @Cacheable(value = "products")
    @ResponseBody
    public GetResult<ProductDto> getPhoto() throws IOException {
        String username = "";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails details = (UserDetails) authentication.getPrincipal();
            username = details.getUsername();
        }
        ProductDto dto = new ProductDto();
        dto.setFullName(username);
        GetResult<ProductDto> user = new GetResult<ProductDto>(dto);
        //        return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
        return user;
    }
}
