package challenge.resource;

import java.security.Principal;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/test")
@RestController
public class HelloController {

    @GetMapping(path="/hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(Principal principal) {
        return "Hello " + principal.getName();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/secured/all")
    public String securedHello() {
        return "Secured Hello";
    }

    @GetMapping("/secured/alternate")
    public String alternate() {
        return "alternate";
    }
}
