package pl.arimr.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class SessionSavingZuulPreFilter extends ZuulFilter {

    private final Logger log = LoggerFactory.getLogger(SessionSavingZuulPreFilter.class);

    private final SessionRepository repository;

    public SessionSavingZuulPreFilter(SessionRepository repository) {
        this.repository = repository;
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();

        HttpSession httpSession = context.getRequest().getSession();
        Session session = repository.findById(httpSession.getId());

        if (session != null) {
            context.addZuulRequestHeader("X-Auth-Token", httpSession.getId());

            log.info("ZuulPreFilter session proxy: {}", session.getId());
        }

        return null;
    }

}