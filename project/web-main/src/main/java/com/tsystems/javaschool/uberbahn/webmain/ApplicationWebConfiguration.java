package com.tsystems.javaschool.uberbahn.webmain;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.tsystems.javaschool.uberbahn.webmain" })
@Import({ SecurityConfiguration.class })
@Profile("production")
public class ApplicationWebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/static/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    /*@Bean
    public SimpleMappingExceptionResolver exceptionResolver() {
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();
        //Properties exceptionMappings = new Properties();
        //exceptionMappings.put("CustomGenericException", "addStationsToRouteForm");
        //exceptionMappings.put("java.lang.RuntimeException", "errorPage");
        //exceptionResolver.setExceptionMappings(exceptionMappings);
        exceptionResolver.setDefaultErrorView("errorPage");
        exceptionResolver.setExceptionAttribute("exception");
        //exceptionResolver.setWarnLogCategory("example.MvcLogger");
        return exceptionResolver;
    }*/

}
