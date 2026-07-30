package com.kyro.kyro.gitops.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.gitops.domain.SafePrCreated;
import com.kyro.kyro.gitops.domain.SafePrReadyForCreation;

@Component
class ScmWorker {

	private final KyroEventPublisher events;

	ScmWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(SafePrReadyForCreation event) {
		events.publish(new SafePrCreated(
				event.metadata().child(),
				event.incidentId(),
				event.actionId(),
				event.repository(),
				event.baseSha(),
				event.sourcePath(),
				"draft",
				"https://example.invalid/pull-request-placeholder"
		));
	}
}
