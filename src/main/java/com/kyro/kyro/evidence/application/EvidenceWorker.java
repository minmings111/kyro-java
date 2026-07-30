package com.kyro.kyro.evidence.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.evidence.domain.ClusterEvidenceReceived;
import com.kyro.kyro.evidence.domain.EvidenceBuilt;

@Component
class EvidenceWorker {

	private final KyroEventPublisher events;

	EvidenceWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(ClusterEvidenceReceived event) {
		events.publish(new EvidenceBuilt(
				event.metadata().child(),
				event.evidenceId(),
				incidentKey(event),
				event.target(),
				summary(event),
				event.reason(),
				event.observedImage(),
				event.desiredImage(),
				event.sourceRef()
		));
	}

	private static String incidentKey(ClusterEvidenceReceived event) {
		return String.join(
				":",
				event.target().workspaceId(),
				event.target().targetId(),
				event.namespace(),
				event.workloadName(),
				event.reason()
		);
	}

	private static String summary(ClusterEvidenceReceived event) {
		return "Workload " + event.namespace() + "/" + event.workloadName()
				+ " reported " + event.reason() + ": " + event.message();
	}
}
