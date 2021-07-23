package com.screw.datasourcedoc.doc.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author: jiangjs
 * @description:
 * @date: 2021/7/23 13:59
 **/
@ConfigurationProperties(prefix = "spring.datasource")
@Component
@Data
public class DataSourcePro {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private Hikari hikari;

    @Data
    public static class Hikari{
        private Integer minimumIdle;
        private Integer maximumPoolSize;
    }
}
