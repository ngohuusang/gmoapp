package vn.gmostore.api.mvc;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FallbackController {

    @RequestMapping(value = "*")
    @ResponseBody
    public ResponseEntity<String> fallBack() throws IOException {
        return new ResponseEntity<String>("Bad request", HttpStatus.BAD_REQUEST);
    }

}
