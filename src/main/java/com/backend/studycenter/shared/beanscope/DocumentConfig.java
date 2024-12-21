package com.backend.studycenter.shared.beanscope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@ComponentScan(basePackageClasses = DocumentConfig.class)
public class DocumentConfig {

  //web aware @ApplicationScope @SessionScope  @RequestScope
  @ApplicationScope
  @Bean("docApplicationScope")
  public Document documentApplicationScope() {
    return new Document();
  }

  @SessionScope
  @Bean
  public Document documentSessionScope() {
    return new Document("SessionScopeTitle1", "SessionScopeContent1", 20);
  }

  @RequestScope
  @Bean("documentRequestScope")
  public Document documentRequestScope() {
    return new Document("RequestScopeTitle1", "RequestScopeContent1", 30);
  }

  //
  @Scope(value = "prototype")
  @Bean
  public Document docPrototype() {
    return new Document("PrototypeScopeTitle1", "PrototypeScopeContent1", 30);
  }

  //
  @Scope(value = "singleton")
  @Bean
  public Document documentSingletonScope() {
    return new Document("SingletonScopeTitle1", "SingletonScopeContent1", 30);
  }

  //
  public static void main(String[] args) {
    ApplicationContext context1 = new AnnotationConfigApplicationContext(DocumentConfig.class);
    ApplicationContext context2 = new AnnotationConfigApplicationContext(DocumentConfig.class);
    Document document1 = (Document) context1.getBean("documentSingletonScope");
    Document document2 = (Document) context1.getBean("documentSingletonScope");
    Document document3 = (Document) context2.getBean("documentSingletonScope");
    System.out.println(document1 == document2);
    //it is not equal because it is from different ApplicationContext
    System.out.println(document1 == document3);

  }
}