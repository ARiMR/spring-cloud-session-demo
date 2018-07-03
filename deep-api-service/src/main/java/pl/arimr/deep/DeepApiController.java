package pl.arimr.deep;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class DeepApiController {

    @GetMapping("/deep/api/call")
    public String getPrincipal(@AuthenticationPrincipal Principal principal) {
        if (principal != null) {
            return "Deep api response for user " + principal.getName();
        }
        return "Deep api response for unauthenticated";
    }
}
