package com.kyro.kyro.rca.application;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.rca.domain.RcaCandidatesEvaluated;
import com.kyro.kyro.rca.domain.RcaCandidatesPlanned;

@Component
class AnalyzeWorker {

	private final KyroEventPublisher events;

	AnalyzeWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(RcaCandidatesPlanned event) {
		events.publish(new RcaCandidatesEvaluated(
				event.metadata().child(),
				event.incidentId(),
				event.incidentKey(),
				event.evidenceId(),
				event.target(),
				event.candidateCauses(),
				List.of("symptom matches image pull failure catalog")
		));
	}
}
