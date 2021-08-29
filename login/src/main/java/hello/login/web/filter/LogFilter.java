package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log filter doFilter");

        // down casting : ServletRequest -> HttpServletRequest : 기능 확장을 위함함
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String requestURI = httpRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            log.info("req = [{}][{}]", uuid, requestURI);
            MDC.put("traceId", uuid);
            // doFilter() 를 호출하여 다음 Filter 로 이동하거나, 더 이상 Filter가 없다면 Servlet를 호출한다.
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        } finally {
            log.info("res = [{}][{}]", uuid, requestURI);
            MDC.clear();
        }

    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
