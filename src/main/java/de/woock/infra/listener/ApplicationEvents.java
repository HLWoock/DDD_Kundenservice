package de.woock.infra.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ApplicationEvents {
	@EventListener
	public void listener(ApplicationEvent event) {
		String source = event.getSource().getClass().getSimpleName();
		String type   = event.getClass().getSimpleName();
		log.debug("{} \t<- {}", type, source);
	}
}
