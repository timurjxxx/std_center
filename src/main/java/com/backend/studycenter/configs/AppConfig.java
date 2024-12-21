package com.backend.studycenter.configs;

import com.backend.studycenter.common.service.db.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableScheduling
public class AppConfig implements WebMvcConfigurer {

  @Autowired
  private DBService dbService;
       ;
  @Bean
  public void initTestData() throws Exception {
    dbService.initTestData();
  }
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }
}
