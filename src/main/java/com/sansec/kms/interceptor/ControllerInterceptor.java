package com.sansec.kms.interceptor;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Aspect  //指定当前类为切面类
@Component
@Order(10)
public class ControllerInterceptor {
	private static final Logger log =  LoggerFactory.getLogger(ControllerInterceptor.class);

//	指定切入点表达式，拦截哪些方法，即为哪些方法生成代理对象
//	任何io.fchain.dapp.c2c.controller中的方法
//	任何方法有RequestMapping注解的方法
//	@Pointcut("execution(* io.fchain.dapp.c2c.controller.*.*(..)) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	@Pointcut("execution(* com.sansec.kms.controller.*.*(..))")
	private void controllerAspect() {
		
	}
//前置通知，在执行目标方法之前执行
//	@Before(value = "controllerAspect()")
//	public void beforeService(JoinPoint joinPoint) {
//		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
//		HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//		log.info("---------进入请求处理[{}]---------", request.getRequestURI());
//		Map<String, String[]> args = request.getParameterMap();
//		log.info("请求信息：{}", JSON.toJSONString(args).toString());
//	}
////返回后通知，在调用目标方法结束后执行
//	@AfterReturning(returning = "o", pointcut = "controllerAspect()")
//	public void afterService(JoinPoint joinPoint, Object o) {
////		if (o != null && o instanceof ResponseBean)
////			log.info("返回信息：{}", JSON.toJSONString(o));
//		log.info("---------请求处理完成---------");
//	}
	@Around(value = "controllerAspect()")
	public Object loggingAround(ProceedingJoinPoint joinPoint) {
		long startTime = System.currentTimeMillis();
		// 定义返回对象、得到方法需要的参数
		Object resultData = null;
		Object[] args = joinPoint.getArgs();
		Object apiName = args[0];
		String methodName = joinPoint.getSignature().getName();
		try {
			// 调用钉钉接口
			log.info("======>请求--{}--接口开始,参数:{}",methodName,args);
			resultData = joinPoint.proceed(args);
			long endTime = System.currentTimeMillis();
			log.info("======>请求--{}--接口完成,耗时:{}ms,返回:{}", methodName,(endTime - startTime), JSON.toJSON(resultData));
		} catch (Throwable e) {
			// 记录异常信息
			long endTime = System.currentTimeMillis();
			log.error("======>请求--{}--接口异常！耗时:{}ms", methodName,(endTime - startTime));
		}
		return resultData;
	}
}
