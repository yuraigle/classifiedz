package ru.orlovs.classifiedz.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TrailingSlashRedirectingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest req,
            HttpServletResponse resp,
            FilterChain chain
    ) throws ServletException, IOException {
        String uri = req.getRequestURI();

        boolean isStaticFile = false;
        if (uri.endsWith(".js") || uri.endsWith(".css") ||
                uri.endsWith(".html") || uri.endsWith(".ico") ||
                uri.endsWith(".woff") || uri.endsWith(".woff2") ||
                uri.endsWith(".svg") || uri.endsWith(".ttf") ||
                uri.endsWith(".eot")
        )
            isStaticFile = true;

        if (!isStaticFile && !uri.startsWith("/api") && !uri.endsWith("/")) {
            ServletUriComponentsBuilder builder = ServletUriComponentsBuilder.fromRequest(req);
            builder.replacePath(String.format("%s/", builder.build().getPath()));
            resp.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
            resp.setHeader(HttpHeaders.LOCATION, builder.toUriString());
        } else {
            chain.doFilter(req, resp);
        }
    }
}
