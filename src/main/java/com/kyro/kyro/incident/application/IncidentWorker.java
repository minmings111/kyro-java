package com.kyro.kyro.incident.application;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.UUID;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.evidence.domain.EvidenceBuilt;
import com.kyro.kyro.incident.domain.IncidentDetected;
import com.kyro.kyro.rca.domain.EvidenceBundleBuilt;

@Component
class IncidentWorker {

	private final KyroEventPublisher events;

	IncidentWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(EvidenceBuilt event) {
		var incidentId = stableIncidentId(event.incidentKey());
		events.publish(new IncidentDetected(
				event.metadata().child(),
				incidentId,
				event.incidentKey(),
				event.evidenceId(),
				event.target(),
				"Investigate " + event.reason(),
				"warning"
		));
		events.publish(new EvidenceBundleBuilt(
				event.metadata().child(),
				incidentId,
				event.incidentKey(),
				event.evidenceId(),
				event.target(),
				List.of(event.summary()),
				event.observedImage(),
				event.desiredImage(),
				event.sourceRef()
		));
	}

	private static String stableIncidentId(String incidentKey) {
		return UUID.nameUUIDFromBytes(incidentKey.getBytes(StandardCharsets.UTF_8)).toString();
	}
}
