package com.herokuapp.kon104.webapp.util;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerInterceptor
{
	private final Map<String, Logger> pool = new HashMap<>();

	public final static int MODE_START = 1;
	public final static int MODE_END = 2;
	public final static int MODE_EXCEPTION = 3;

	// {{{ public void beforeController(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.controller.*.*(..))")
	public void beforeController(JoinPoint jp)
	{
		this.outputState(jp,MODE_START);
	}
	// }}}

	// {{{ public void beforeService(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.service.*.*(..))")
	public void beforeService(JoinPoint jp)
	{
		this.outputState(jp, MODE_START);
	}
	// }}}

	// {{{ public void beforeSecurity(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.security.*.*(..))")
	public void beforeSecurity(JoinPoint jp)
	{
		this.outputState(jp, MODE_START);
	}
	// }}}

	// {{{ public void afterController(JoinPoint jp)
	@After("execution(* com.herokuapp.kon104.webapp.controller.*.*(..))")
	public void afterController(JoinPoint jp)
	{
		this.outputState(jp, MODE_END);
	}
	// }}}

	// {{{ public void afterReturningService(JoinPoint jp, Object retval)
	@AfterReturning(value = "execution(* com.herokuapp.kon104.webapp.service.*.*(..))", returning = "retval")
	public void afterReturningService(JoinPoint jp, Object retval)
	{
		this.outputState(jp, MODE_END, retval);
	}
	// }}}

	// {{{ public void afterThrowingService(JoinPoint jp, RuntimeException e)
	@AfterThrowing(value = "execution(* com.herokuapp.kon104.webapp.service.*.*(..))", throwing = "e")
	public void afterThrowingService(JoinPoint jp, RuntimeException e)
	{
		this.outputState(jp, e);
	}
	// }}}

	// {{{ public void afterSecurity(JoinPoint jp)
	@After("execution(* com.herokuapp.kon104.webapp.security.*.*(..))")
	public void afterSecurity(JoinPoint jp)
	{
		this.outputState(jp, MODE_END);
	}
	// }}}

	// {{{ private void outputState(JoinPoint jp, int mode)
	private void outputState(JoinPoint jp, int mode)
	{
		this.outputState(jp, mode, null, null);
	}
	// }}}

	// {{{ private void outputState(JoinPoint jp, int mode, Object retval)
	private void outputState(JoinPoint jp, int mode, Object retval)
	{
		this.outputState(jp, mode, retval, null);
	}
	// }}}

	// {{{ private void outputState(JoinPoint jp, int mode, Object retval, RuntimeException e)
	private void outputState(JoinPoint jp, int mode, Object retval, RuntimeException e)
	{
		String label = null;
		String infoVal = null;

		if (mode == MODE_START) {
			label = "START";
			String[] argsKeys = ((CodeSignature) jp.getSignature()).getParameterNames();
			Object[] argsVals = jp.getArgs();
			for (int i = 0; i < argsKeys.length; i++) {
				if (infoVal == null) {
					infoVal = "Parameter : ";
				} else {
					infoVal += ", ";
				}
				infoVal += argsKeys[i] + "=" + String.valueOf(argsVals[i]);
			}
		} else
		if (mode == MODE_END) {
			label = "END  ";
			if (retval != null) {
				infoVal = "Returning : " + String.valueOf(retval);
			}
		} else
		if (mode == MODE_EXCEPTION) {
			label = "EXCEPTION";
			if (e != null) {
				StringBuilder build = new StringBuilder();
				build.append(e.getClass()).append(": ").append(e.getMessage());
				StackTraceElement[] list = e.getStackTrace();
				for (StackTraceElement line : list) {
					build.append(" [").append(line.toString()).append("]");
					break;
				}
				infoVal = build.toString();
			}
		}
		String pathClassMethod = jp.getSignature().toString();

		Logger logger = this.assignLogger(jp);
		if (infoVal == null) {
			if (mode != MODE_EXCEPTION) {
				logger.info("{} >> {}", label, pathClassMethod);
			} else {
				logger.error("{} >> {}", label, pathClassMethod);
			}
		} else {
			if (mode != MODE_EXCEPTION) {
				logger.info("{} >> {} >> {}", label, pathClassMethod, infoVal);
			} else {
				logger.error("{} >> {} >> {}", label, pathClassMethod, infoVal);
			}
		}
	}
	// }}}

	// {{{ private void outputState(JoinPoint jp, RuntimeException e)
	private void outputState(JoinPoint jp, RuntimeException e)
	{
		this.outputState(jp, MODE_EXCEPTION, null, e);
	}
	// }}}

	// {{{ private Logger assignLogger(JoinPoint jp)
	private Logger assignLogger(JoinPoint jp)
	{
		String className = jp.getSignature().getDeclaringType().getName();
		Logger logger;
		if (this.pool.containsKey(className)) {
			logger = this.pool.get(className);
		} else {
			logger = LoggerFactory.getLogger(className);
			this.pool.put(className, logger);
			logger.info("Created and pooled the logger instance of [{}]", className);
		}

		return logger;
	}
	// }}}

}
