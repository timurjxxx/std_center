package com.backend.studycenter.shared.beanscope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping(value = "/api/v2", produces = MediaType.APPLICATION_JSON_VALUE)
public class ScopeController {

  @Autowired
  @Qualifier("docApplicationScope")
  Document docApplicationScope;

  @Autowired
  @Qualifier("documentSessionScope")
  Document documentSession;

  @Autowired
  @Qualifier("documentRequestScope")
  Document documentRequestScope;

  @GetMapping("/app-scope")
  public Long getApplicationScope() {

    return docApplicationScope.getMaxContentSize();
  }

  @PostMapping(value = "/app-scope", consumes = MediaType.APPLICATION_JSON_VALUE)
  public long postApplicationScope(@RequestBody Request request) {
    docApplicationScope.setMaxContentSize(request.getMaxSize());
    return docApplicationScope.getMaxContentSize();
  }

  @GetMapping("/session-scope")
  public Long getSessionScope() {

    return documentSession.getMaxContentSize();
  }

  @PostMapping(value = "/session-scope", consumes = MediaType.APPLICATION_JSON_VALUE)
  public long postSessionScope(@RequestBody Request request) {
    documentSession.setMaxContentSize(request.getMaxSize());
    return documentSession.getMaxContentSize();
  }

  @GetMapping("/request-scope")
  public Long getRequestScope() {

    return documentRequestScope.getMaxContentSize();
  }

  @PostMapping(value = "/request-scope", consumes = MediaType.APPLICATION_JSON_VALUE)
  public long postRequestScope(@RequestBody Request request) {
    documentRequestScope.setMaxContentSize(request.getMaxSize());
    return documentRequestScope.getMaxContentSize();
  }
}
