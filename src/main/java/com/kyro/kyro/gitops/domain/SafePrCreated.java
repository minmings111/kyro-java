package com.kyro.kyro.gitops.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record SafePrCreated(
		EventMetadata metadata,
		String incidentId,
		String actionId,
		String repository,
		String baseSha,
		String sourcePath,
		String status,
		String url
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.SAFE_PR_CREATED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
