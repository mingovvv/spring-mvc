package com.example.exception.config;

import com.example.exception.filter.LogFilter;
import com.example.exception.resolver.MyHandlerExceptionResolver;
import com.example.exception.resolver.UserHandlerExceptionResolver;
import hello.login.web.interceptor.LogInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.util.List;

@Configuration
public class Webconfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/*.ico", "/error", "/error-page/**" //오류 페이지 경로
                );
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserHandlerExceptionResolver());
    }

    //    @Bean
//    public FilterRegistrationBean logFilter() {
//        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.setFilter(new LogFilter());
//        filterRegistrationBean.setOrder(1);
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST); // default
//        return filterRegistrationBean;
//    }

}
