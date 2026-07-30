package com.kyro.kyro.rca.application;

import java.util.List;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.rca.domain.EvidenceBundleBuilt;
import com.kyro.kyro.rca.domain.RcaCandidatesPlanned;

@Component
class PlanWorker {

	private final KyroEventPublisher events;

	PlanWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(EvidenceBundleBuilt event) {
		events.publish(new RcaCandidatesPlanned(
				event.metadata().child(),
				event.incidentId(),
				event.incidentKey(),
				event.evidenceId(),
				event.target(),
				List.of("image_pull_backoff", "image_tag_mismatch"),
				event.symptoms(),
				event.observedImage(),
				event.desiredImage(),
				event.sourceRef()
		));
	}
}
