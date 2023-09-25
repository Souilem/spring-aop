package com.dproduction.controller.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.dao.DataAccessException;

import com.dproduction.controller.annotations.Auditable;
import com.dproduction.controller.entity.Account;

@Aspect
public class MainAspect {
	
	
	// The execution of any method defined in the dao package:
	@Before("execution(* com.xyz.dao.*.*(..))")
	public void doAccessCheck1() {
		// ...
	}
	
	
	@Before("com.dproduction.pointcut.CommonPointcuts.dataAccessOperation()")
	public void doAccessCheck2() {
		// ...
	}
	
	
	//After returning advice runs when a matched method execution returns normally. 
	//You can declare it by using the @AfterReturning annotation.
	@AfterReturning("execution(* com.xyz.dao.*.*(..))")
	public void doAccessCheck3() {
		// ...
	}
	
	
	//After throwing advice runs when a matched method execution exits by throwing an exception
	@AfterThrowing("execution(* com.xyz.dao.*.*(..))")
	public void doRecoveryActions() {
		// ...
	}
	
	//Run only when exceptions of a given type are thrown
	@AfterThrowing(pointcut="execution(* com.xyz.dao.*.*(..))",throwing="ex")
		public void doRecoveryActions(DataAccessException ex) {
			// ...
		}
	
	
	//After advice must be prepared to handle both normal and exception return conditions.
	// It is typically used for releasing resources and similar purposes
	@After("execution(* com.xyz.dao.*.*(..))")
	public void doReleaseLock() {
		// ...
	}
	
	
	//The last kind of advice is around advice. Around advice runs "around" a matched method’s execution.
	// It has the opportunity to do work both before and after the method runs and 
	//to determine when, how, and even if the method actually gets to run at all. 
	//Around advice is often used if you need to share state before and after a method execution 
	//in a thread-safe manner – for example, starting and stopping a timer.
	@Around("execution(* com.xyz..service.*.*(..))")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
		// start stopwatch
		Object retVal = pjp.proceed();
		// stop stopwatch
		return retVal;
	}
	
	
	//Access to the Current JoinPoint from the interface org.aspectj.lang.JoinPoint
	//around advice is required to declare a first parameter of type ProceedingJoinPoint, 
	//which is a subclass of JoinPoint.
	    
	public Object doBasicProfiling2(ProceedingJoinPoint pjp) {

	     System.err.println("entering: " + pjp);
         System.err.println("  w/args: " + pjp.getArgs());
         System.err.println("      at: " + pjp.getSourceLocation());
         System.err.println("      target: " + pjp.getTarget());
         System.err.println("      proxy: " + pjp.getThis());
		
		
		return pjp;
		
	}
	
	// Passing Parameters to Advice (2 examples with the same logic)
	//The args(account,..) part of the pointcut expression serves two purposes. 
	//First, it restricts matching to only those method executions where the method takes at least one parameter, 
	//and the argument passed to that parameter is an instance of Account. 
	//Second, it makes the actual Account object available to the advice through the account parameter.
	@Before("execution(* com.xyz.dao.*.*(..)) && args(account,..)")
	public void validateAccount1(Account account) {
		// ...
	}
	
	
	@Pointcut("execution(* com.xyz.dao.*.*(..)) && args(account,..)")
	private void accountDataAccessOperation(Account account) {}

	@Before("accountDataAccessOperation(account)")
	public void validateAccount2(Account account) {
		// ...
	}
	
	
	@Before("com.xyz.Pointcuts.publicMethod() && @annotation(auditable)") 
	public void audit(Auditable auditable) {
		// AuditCode code = auditable.value();
		// ...
	}
	
	
	

}
