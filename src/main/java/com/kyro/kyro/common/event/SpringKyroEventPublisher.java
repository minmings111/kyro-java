package com.kyro.kyro.common.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
class SpringKyroEventPublisher implements KyroEventPublisher {

	private final ApplicationEventPublisher publisher;

	SpringKyroEventPublisher(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public void publish(KyroEvent event) {
		publisher.publishEvent(event);
	}
}
