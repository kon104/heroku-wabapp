package com.herokuapp.kon104.webapp.util;

import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerInterceptor
{
	private final Map<String, Logger> pool = new HashMap<>();

	// {{{ public void before(JoinPoint jp)
	@Before("execution(* com.herokuapp.kon104.webapp.service.*.*(..))")
	public void before(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, "START");
	}
	// }}}

	// {{{ public void after(JoinPoint jp)
	@After("execution(* com.herokuapp.kon104.webapp.service.*.*(..))")
	public void after(JoinPoint jp)
	{
		Logger logger = this.assignLogger(jp);
		this.outputLabel(logger, jp, "END  ");
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

	// {{{ private void outputLabel(Logger logger, JoinPoint jp, String label)
	private void outputLabel(Logger logger, JoinPoint jp, String label)
	{
		String signature = jp.getSignature().toString();
		logger.info("{} : {}", label, signature);
	}
	// }}}

}