package de.woock.infra.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ApplicationEvents {
	
	@EventListener()
	public void listener(ApplicationEvent event) {
		String source = event.getSource().getClass().getSimpleName();
		String type   = event.getClass().getSimpleName();
		log.debug("{} \t<- {}", type, source);
		
		if(event instanceof ServletRequestHandledEvent){
			String requestUrl = ((ServletRequestHandledEvent)event).getRequestUrl();
			int    statusCode = ((ServletRequestHandledEvent)event).getStatusCode();
			String method = ((ServletRequestHandledEvent)event).getMethod();
				log.debug("{}: {} ({})", method, requestUrl, statusCode);
			}
	}
}
