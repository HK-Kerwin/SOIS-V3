package com.tedu.sois.common.config;

import com.tedu.sois.common.web.TimeAccessInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringWebConfig implements WebMvcConfigurer {

    @Value("${sois.profile}")
    private String uploadFolder;

    @Value("${sois.staticAccessPath}")
    private String staticAccessPath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> patterns = new ArrayList();

        /*静态资源*/
        patterns.add("/favicon.ico");

        patterns.add("/bower_components/**");
        patterns.add("/build/**");
        patterns.add("/dist/**");
        patterns.add("/i18n/**");

        patterns.add("/");
        patterns.add("/error");
        patterns.add("/doErrorUI");
        patterns.add("/doLoginUI");

        //头像请求
        patterns.add("/upload/**");

        //单独的页面跳转请求
        patterns.add("/user/getUserRegisterPage");
        patterns.add("/user/getUserInfoPage");
        patterns.add("/user/getModifyPasswordPage");

        patterns.add("/common/*");
        patterns.add("/home/*");
        patterns.add("/training/*");
        patterns.add("/stu/*");
        patterns.add("/crm/*");
        patterns.add("/teacher/*");
        patterns.add("/user/*");
        patterns.add("/sys/*");

        registry.addInterceptor( new TimeAccessInterceptor())
                .addPathPatterns()
                .excludePathPatterns(patterns);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(staticAccessPath).addResourceLocations("file:" + uploadFolder);
    }

}
