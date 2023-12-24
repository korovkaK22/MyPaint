package com.example.mypaint.configurations;

import com.example.mypaint.PaintSceneApplication;
import com.example.mypaint.facade.EffectsFacade;
import com.example.mypaint.facade.FacadeImpl;
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
            @Value("${server.address.resize}") String addressResize,
            @Value("${server.address.crop}") String cropImage,
            @Value("${server.address.move}") String moveImage,
            @Value("${server.address.changeCanvasSize}") String changeCanvasSize) {
        return new WebCanvasEffects(serverDomain, addressRotate, addressResize, changeCanvasSize, cropImage, moveImage);
    }

    @Bean
    public EffectsFacade getEffectsFacade(WebCanvasEffects effects){
        return new FacadeImpl(effects);
    }

}
