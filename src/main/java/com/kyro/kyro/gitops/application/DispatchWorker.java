package com.kyro.kyro.gitops.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.gitops.domain.SafePrRequested;
import com.kyro.kyro.rca.domain.RcaActionRequired;
import com.kyro.kyro.recovery.domain.RecoveryActionSelected;

@Component
class DispatchWorker {

	private final KyroEventPublisher events;

	DispatchWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(RecoveryActionSelected event) {
		if (!"pull_request".equals(event.deliveryMode())) {
			events.publish(new RcaActionRequired(
					event.metadata().child(),
					event.incidentId(),
					"unsupported-delivery-mode",
					"Only pull_request delivery is enabled in the Java skeleton."
			));
			return;
		}
		events.publish(new SafePrRequested(
				event.metadata().child(),
				event.incidentId(),
				event.actionId(),
				event.repository(),
				event.baseSha(),
				event.sourcePath(),
				event.patchSummary()
		));
	}
}
