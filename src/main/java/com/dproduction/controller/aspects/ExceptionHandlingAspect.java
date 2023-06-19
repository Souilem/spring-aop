package com.dproduction.controller.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionHandlingAspect {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlingAspect.class);

	/*
	 * @AfterThrowing(pointcut = "execution(* com.dproduction.service.*.*(..))",
	 * throwing = "ex") public void handleException(JoinPoint joinPoint, Exception
	 * ex) { logger.error("Exception occurred in method: " +
	 * joinPoint.getSignature().getName(), ex); // Perform error handling or
	 * customize exception response }
	 */
}

