package com.example.myhead.second.common.config.Kaptcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 配置类
 * 配置验证码的一些生成属性
 *
 * @author lector
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {

        Properties properties = new Properties();
        properties.put("kaptcha.border", "no");
        properties.put("kaptcha.textprodecer.font.color", "black");
        properties.put("kaptcha.textprodecer.char.space", "5");

        Config config = new Config(properties);
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
