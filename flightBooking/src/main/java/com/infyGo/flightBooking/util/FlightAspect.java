package com.infyGo.flightBooking.util;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class FlightAspect {
	
	private static Logger logger = LoggerFactory.getLogger(FlightAspect.class);
	
	@Before("execution(* com.infyGo.flightBooking.repository.FlightRepositoryImpl.addFlight(..))")
	public void logBeforeAddFlight(JoinPoint joinPoint) {
		logger.info("Logging before calling addFlight, Join Point : {}",joinPoint.getSignature());
	}
	
	@After("execution(* com.infyGo.flightBooking.repository.FlightRepositoryImpl.addFlight(..))")
	public void logAfterAddFlight() {
		logger.info("Logging before calling addFlight");
	}
	
	@Around("execution(* com.infyGo.flightBooking.repository.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Before proceeding part of the Around advice.");
		logger.info("Before : ", joinPoint.getSignature());
		 Object cust =  joinPoint.proceed();
		System.out.println("After proceeding part of the Around advice.");
		return cust;
	}
}
