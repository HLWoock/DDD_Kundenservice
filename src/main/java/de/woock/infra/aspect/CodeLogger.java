package de.woock.infra.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import de.woock.infra.annotation.Log;
import lombok.extern.log4j.Log4j2;


@Log4j2
@Aspect
@Component
public class CodeLogger {
	private static final String DASH_LINE = "===================================";
	private static final String NEXT_LINE = "\n";
			
	@Pointcut("@annotation(codeLog)")
	public void codeLogger(Log codeLog){}
	
	@Before("codeLogger(codeLog)")
	public void doCodeLogger(JoinPoint jp,Log codeLog){
		StringBuilder str = new StringBuilder(NEXT_LINE);
		str.append(DASH_LINE);
		str.append(NEXT_LINE);
		str.append(" Class: " + jp.getTarget().getClass().getSimpleName());
		str.append(NEXT_LINE);
		str.append("Method: " + jp.getSignature().getName());
		str.append(NEXT_LINE);
		if(codeLog.printParamsValues()){
			Object[] args = jp.getArgs();
			str.append(NEXT_LINE);
			for(Object obj : args){
				str.append(" Param: " + obj.getClass().getSimpleName());
				str.append(NEXT_LINE);
				
				try {
					String methodToCall = codeLog.callMethodWithNoParamsToString();
					
					if("toString".equals(methodToCall))
						str.append(" Value: " + obj);
					else
						str.append(" Value: " + 
							obj.getClass()
							   .getDeclaredMethod(methodToCall, new Class[]{})
							   .invoke(obj,new Object[]{}));
				} catch (Exception e) {
					str.append(" Value: [ERROR]> " + e.getMessage());
				} 
				str.append(NEXT_LINE);
			}
		}
		str.append(DASH_LINE);
		log.info(str.toString());
	}
}

