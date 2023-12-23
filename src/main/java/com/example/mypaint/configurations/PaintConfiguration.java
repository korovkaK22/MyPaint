package com.example.mypaint.configurations;

import com.example.mypaint.PaintSceneApplication;
import com.example.mypaint.web.WebCanvasEffects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:config.properties")
public class PaintConfiguration {

    @Bean
    public WebCanvasEffects getWebCanvasEffects(
            @Value("${server.domain}") String serverDomain,
            @Value("${server.address.rotate}") String addressRotate,
            @Value("${server.address.expand}") String addressExpand,
            @Value("${server.address.crop}") String addressCrop) {
        return new WebCanvasEffects(serverDomain, addressRotate, addressExpand, addressCrop);
    }
}
