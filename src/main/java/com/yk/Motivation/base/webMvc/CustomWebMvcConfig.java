package com.yk.Motivation.base.webMvc;

import com.yk.Motivation.base.app.AppConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // /gen 으로 시작하는 모든 요청에 서버 로컬의 파일 리소스로 매핑 해줌 
        registry.addResourceHandler("/gen/**") // /gen 으로 시작하는 요청에 대해
                .addResourceLocations("file:///" + AppConfig.getGenFileDirPath() + "/"); // 파일 리소스로 매핑
    }
}