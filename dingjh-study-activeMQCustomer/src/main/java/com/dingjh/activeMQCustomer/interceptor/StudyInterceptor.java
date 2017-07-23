package com.dingjh.activeMQCustomer.interceptor;

import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.dingjh.log.logUtil.LogManager;

@Aspect
@Component
public class StudyInterceptor {
	private static final Logger LOG = org.apache.logging.log4j.LogManager.getLogger(StudyInterceptor.class);

	@Pointcut("(execution(* com.dingjh.activeMQCustomer.jms..*.*(..)))")
	private void aspect() {
	}

	@Before(value = "aspect()")
	public void before(JoinPoint jp) {
		String className = jp.getThis().toString();
		String methodName = jp.getSignature().getName(); // 获得方法名
		LOG.error(" === " + className + "调用" + methodName + "()方法-开始 === ");
		LogManager.log(LogManager.LEVEL_INFO,
                LogManager.LEVEL_INFO,this.getClass().getName(),"before",
                " === " + className + "调用" + methodName + "()方法-开始 === ");
		for (int i = 0; i < jp.getArgs().length; i++) {
			LOG.error(" === " + "方法调用参数：" + jp.getArgs()[i].toString());
			LogManager.log(LogManager.LEVEL_INFO,
	                LogManager.LEVEL_INFO,this.getClass().getName(),"before",
	                " === " + "方法调用参数：" + jp.getArgs()[i].toString());
		}
	}

	@After(value = "aspect()")
	public void around(JoinPoint jp) {
		String className = jp.getThis().toString();
		String methodName = jp.getSignature().getName(); // 获得方法名
		LOG.error(" === " + className + "调用" + methodName + "()方法-完成 === ");
		LogManager.log(LogManager.LEVEL_INFO,
                LogManager.LEVEL_INFO,this.getClass().getName(),"around",
                " === " + className + "调用" + methodName + "()方法-完成 === " );
	}
}
