package com.dproduction.controller.aspects;

import java.nio.file.AccessDeniedException;
import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.dproduction.controller.pointcut.SecureAccess;



	/*
	 * @Before("@annotation(SecureAccess) && args(.., authentication)") public void
	 * secureAccess(Authentication authentication) { // Vérifier les autorisations
	 * d'accès ici if (!authentication.isAuthenticated()) { throw new
	 * SecurityException("Accès non autorisé"); } }
	 */



@Aspect
@Component
public class SecurityAspect {

    @Before("@annotation(secureAccess)")
    public void checkAccess(JoinPoint joinPoint, SecureAccess secureAccess) throws AccessDeniedException {
        // Retrieve user roles from authentication context or session
        
    	String[] userRoles = getUserRoles();
        
        // Get required roles from the SecureAccess annotation
        String[] requiredRoles = secureAccess.roles();
        
        // Check if the user has any of the required roles
        boolean hasAccess = false;
        for (String requiredRole : requiredRoles) {
            if (Arrays.asList(userRoles).contains(requiredRole)) {
                hasAccess = true;
                break;
            }
        }
        
        if (!hasAccess) {
            // Throw an exception or redirect to an error page
            throw new AccessDeniedException("Access denied");
        }
    }

    
	private String[] getUserRoles() {	
		/*
		 * UserDetails userDetails =
		 * userDetailsService.loadUserByUsername(authentication.getName()); Collection<?
		 * extends GrantedAuthority> authorities = userDetails.getAuthorities();
		 * String[] userRoles = authorities.stream()
		 * .map(GrantedAuthority::getAuthority) .toArray(String[]::new);
		 * 
		 * // Get required roles from the SecureAccess annotation String[] requiredRoles
		 * = secureAccess.roles();
		 */
		return null;
	}
    
    // Other advice methods for security-related aspects
   
}


