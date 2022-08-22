package com.example.demo.config;

import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@Configuration
public class FastJsonConfig {
    static {
        //全局配置关闭 Fastjson 循环引用
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.DisableCircularReferenceDetect.getMask();
    }
}
