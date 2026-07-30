package com.kyro.kyro.incident.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record IncidentResolved(
		EventMetadata metadata,
		String incidentId,
		String resolutionSummary
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.INCIDENT_RESOLVED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
