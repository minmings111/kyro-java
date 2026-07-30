package com.kyro.kyro.recovery.application;

import java.util.List;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.rca.domain.RcaCompleted;
import com.kyro.kyro.recovery.domain.RecoveryAction;
import com.kyro.kyro.recovery.domain.RecoveryPlanned;

@Component
class RecoveryWorker {

	private final KyroEventPublisher events;

	RecoveryWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(RcaCompleted event) {
		var action = new RecoveryAction(
				UUID.randomUUID().toString(),
				"image_tag_fix",
				"Prepare a source-controlled image field change.",
				false,
				"pull_request",
				"unbound",
				"unknown",
				event.sourceRef()
		);
		events.publish(new RecoveryPlanned(
				event.metadata().child(),
				event.incidentId(),
				event.causeCode(),
				List.of(action)
		));
	}
}
