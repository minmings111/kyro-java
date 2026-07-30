package com.kyro.kyro.recovery.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record RecoveryActionSelected(
		EventMetadata metadata,
		String incidentId,
		String actionId,
		String actionType,
		String deliveryMode,
		String repository,
		String baseSha,
		String sourcePath,
		String patchSummary
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.RECOVERY_ACTION_SELECTED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
