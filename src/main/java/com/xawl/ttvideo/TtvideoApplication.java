package com.xawl.ttvideo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class TtvideoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtvideoApplication.class, args);
    }

    @Bean
    public MultipartConfigElement multipartConfigElement(@Value("${multipart.maxFileSize}") String maxFileSize, @Value("${multipart.maxRequestSize}") String maxRequestSize) {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }
}

