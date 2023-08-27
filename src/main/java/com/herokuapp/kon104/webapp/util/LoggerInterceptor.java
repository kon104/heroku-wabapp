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

	// {{{ public void beforeController(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.controller.*.*(..))")
	public void beforeController(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, true);
	}
	// }}}

	// {{{ public void beforeService(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.service.*.*(..))")
	public void beforeService(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, true);
	}
	// }}}

	// {{{ public void beforeSecurity(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.security.*.*(..))")
	public void beforeSecurity(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, true);
	}
	// }}}

	// {{{ public void afterController(JoinPoint jp)
	@After("execution(* com.herokuapp.kon104.webapp.controller.*.*(..))")
	public void afterController(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, false);
	}
	// }}}

	// {{{ public void afterReturningService(JoinPoint jp, Object retval)
	@AfterReturning(value = "execution(* com.herokuapp.kon104.webapp.service.*.*(..))", returning = "retval")
	public void afterReturningService(JoinPoint jp, Object retval)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, false, retval);
	}
	// }}}

	@AfterThrowing(value = "execution(* com.herokuapp.kon104.webapp.service.*.*(..))", throwing = "e")
	public void afterThrowingService(JoinPoint jp, RuntimeException e)
	{
		Logger logger = this.assignLogger(jp);
		// TODO: write some codes.
	}

	// {{{ public void afterSecurity(JoinPoint jp)
	@After("execution(* com.herokuapp.kon104.webapp.security.*.*(..))")
	public void afterSecurity(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, false);
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

	// {{{ private void outputLabel(Logger logger, JoinPoint jp, boolean startflg)
	private void outputLabel(Logger logger, JoinPoint jp, boolean startflg)
	{
		this.outputLabel(logger, jp, startflg, null);
	}
	// }}}

	// {{{ private void outputLabel(Logger logger, JoinPoint jp, boolean startflg, Object retval)
	private void outputLabel(Logger logger, JoinPoint jp, boolean startflg, Object retval)
	{
		String label = "START";
		if (! startflg) {
			label = "END  ";
		}

		String pathClassMethod = jp.getSignature().toString();

		String infoVal = null;
		if (startflg) {
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
		} else {
			if (retval != null) {
				infoVal = "Returning : " + String.valueOf(retval);
			}
		}

		if (infoVal == null) {
			logger.info("{} >> {}", label, pathClassMethod);
		} else {
			logger.info("{} >> {} >> {}", label, pathClassMethod, infoVal);
		}
	}
	// }}}

}
