package com.kyro.kyro.gitops.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.gitops.domain.SafePrFailed;
import com.kyro.kyro.gitops.domain.SafePrPatchPrepared;
import com.kyro.kyro.gitops.domain.SafePrReadyForCreation;

@Component
class DiffPolicyWorker {

	private final KyroEventPublisher events;

	DiffPolicyWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(SafePrPatchPrepared event) {
		if (event.sourcePath() == null || event.sourcePath().isBlank()) {
			events.publish(new SafePrFailed(
					event.metadata().child(),
					event.incidentId(),
					"missing-source-path",
					"Safe PR requires an explicit source file path."
			));
			return;
		}
		events.publish(new SafePrReadyForCreation(
				event.metadata().child(),
				event.incidentId(),
				event.actionId(),
				event.repository(),
				event.baseSha(),
				event.sourcePath(),
				event.patchSummary(),
				"allowed image field only"
		));
	}
}
