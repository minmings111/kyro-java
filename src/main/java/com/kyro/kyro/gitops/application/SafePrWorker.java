package com.kyro.kyro.gitops.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.gitops.domain.SafePrPatchPrepared;
import com.kyro.kyro.gitops.domain.SafePrRequested;

@Component
class SafePrWorker {

	private final KyroEventPublisher events;

	SafePrWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(SafePrRequested event) {
		events.publish(new SafePrPatchPrepared(
				event.metadata().child(),
				event.incidentId(),
				event.actionId(),
				event.repository(),
				event.baseSha(),
				event.sourcePath(),
				event.patchSummary(),
				"structured patch placeholder"
		));
	}
}
