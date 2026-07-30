package com.kyro.kyro.incident.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record IncidentDetected(
		EventMetadata metadata,
		String incidentId,
		String incidentKey,
		String evidenceId,
		TargetRef target,
		String title,
		String severity
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.INCIDENT_DETECTED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
