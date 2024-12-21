package com.backend.studycenter.common.controller.db;

import com.backend.studycenter.common.service.db.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class DBController {
    @Autowired
    private DBService dbService;


    @GetMapping(value = "/api/v1/db/clear-data")
    public ResponseEntity<Void> clearDBData() {
        try {
            dbService.clearData();
        } catch (Exception e) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    
    @GetMapping(value = {"/api/v1/db/init-test-data", "/init-test-data"})
    //@PreAuthorize("permitAll()")
    public ResponseEntity<Void> initTestData() throws Exception {
        dbService.initTestData();
        return ResponseEntity.ok().build();
    }

}

