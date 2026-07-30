package com.kyro.kyro.gitops.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record SafePrFailed(
		EventMetadata metadata,
		String incidentId,
		String reasonCode,
		String message
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.SAFE_PR_FAILED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
