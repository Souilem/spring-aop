package com.dproduction.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dproduction.controller.pointcut.LogExecutionTime;
import com.dproduction.controller.pointcut.SecureAccess;

@RestController
@RequestMapping("v1/")
public class ExampleController {
	
    private static final Logger logger = LoggerFactory.getLogger(ExampleController.class);


    @GetMapping("/example")
    @LogExecutionTime
    //@SecureAccess(roles = {"ADMIN", "USER"})
    public String exampleEndpoint() {
    	
    	logger.info("Example log statement");
    	
        // Code métier de l'endpoint
        return "Hello, World!";
    }

    @PostMapping("/process")
    @Transactional
    @Cacheable("processCache")
    public String processRequest(@RequestBody String request) {
    	
    	logger.info("Example process request");
    	
        // Code métier pour traiter la demande
        return "Request processed";
    }
}
