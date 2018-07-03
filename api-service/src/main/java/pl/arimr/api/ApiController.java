package pl.arimr.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class ApiController {


    private final RestTemplate restTemplate;

    public ApiController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/principal")
    public Principal getPrincipal(@AuthenticationPrincipal Principal principal) {
        return principal;
    }

    @GetMapping("/deep")
    public String callDeepApi() {

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Auth-Token", RequestContextHolder.currentRequestAttributes().getSessionId());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        String response = restTemplate.exchange("http://deep-api-service/deep/api/call", HttpMethod.GET, httpEntity, String.class).getBody();

        return "Response from deep api: \"" + response + "\"";
    }

}
