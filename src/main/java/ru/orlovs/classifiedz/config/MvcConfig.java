package ru.orlovs.classifiedz.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // all requests -> SPA
        registry
                .addResourceHandler("/dashboard", "/dashboard/", "/dashboard/**")
                .addResourceLocations("classpath:/dashboard-dist/")
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new SpaResourceResolver());
    }
}
