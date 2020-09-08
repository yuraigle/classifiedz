package ru.orlovs.classifiedz.config;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Component
public class SpaResourceResolver extends PathResourceResolver {

    // all requests -> index.html

    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
        Resource resource = location.createRelative(resourcePath);
        if (resource.exists() && resource.isReadable()) {
            return resource;
        } else {
            resource = location.createRelative("index.html");
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                return null;
            }
        }
    }
}
