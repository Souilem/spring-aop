package com.dproduction.controller.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component


    public class CachingAspect {

        @Around("@annotation(org.springframework.cache.annotation.Cacheable)")
        public Object cacheResult(ProceedingJoinPoint joinPoint) throws Throwable {
            // Check cache for existing result
            // If found, return cached result
            // If not found, execute method and cache the result
            return joinPoint.proceed();
        }
        

        @Around("@annotation(org.springframework.cache.annotation.CacheEvict)")
        public Object evictCache(ProceedingJoinPoint joinPoint) throws Throwable {
            // Cache eviction logic here
            return joinPoint.proceed();
        }
    }
    
  

