package com.kyro.kyro.audit.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEvent;

@Component
class EventAuditListener {

	private static final Logger log = LoggerFactory.getLogger(EventAuditListener.class);

	@EventListener
	void on(KyroEvent event) {
		log.info(
				"event={} aggregate={} correlation={} causation={}",
				event.subject().subject(),
				event.aggregateId(),
				event.metadata().correlationId(),
				event.metadata().causationId()
		);
	}
}
