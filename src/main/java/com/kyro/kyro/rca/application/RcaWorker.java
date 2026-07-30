package com.kyro.kyro.rca.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.rca.domain.RcaCompleted;
import com.kyro.kyro.rca.domain.RcaCandidatesEvaluated;

@Component
class RcaWorker {

	private final KyroEventPublisher events;

	RcaWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(RcaCandidatesEvaluated event) {
		events.publish(new RcaCompleted(
				event.metadata().child(),
				event.incidentId(),
				event.incidentKey(),
				event.evidenceId(),
				event.target(),
				"image_pull_backoff",
				"Workload image evidence indicates an image pull failure.",
				event.candidateCauses(),
				event.evaluationNotes(),
				null
		));
	}
}
