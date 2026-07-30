package com.kyro.kyro.gitops.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record SafePrReadyForCreation(
		EventMetadata metadata,
		String incidentId,
		String actionId,
		String repository,
		String baseSha,
		String sourcePath,
		String patchSummary,
		String policySummary
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.SAFE_PR_READY_FOR_CREATION;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
