package com.kyro.kyro.rca.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record RcaActionRequired(
		EventMetadata metadata,
		String incidentId,
		String reasonCode,
		String operatorMessage
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.RCA_ACTION_REQUIRED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
