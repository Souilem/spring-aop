package com.dproduction.controller.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Aspect
@Component
public class TransactionAspect {
	
    private static final Logger logger = LoggerFactory.getLogger(TransactionAspect.class);

    @Before("@annotation(transactional)")
    public void beginTransaction(Transactional transactional) {
        // Start the transaction
        try {
            // Perform any transaction-related operations
            logger.info("Begin transcation");
            // ...
        } catch (Exception e) {
            // Handle any exceptions
            e.printStackTrace();
        }
    }
}
