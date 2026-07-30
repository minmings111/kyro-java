package com.kyro.kyro.recovery.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record RecoverySelectionRequested(
		EventMetadata metadata,
		String incidentId,
		List<RecoveryAction> actions
) implements KyroEvent {

	public RecoverySelectionRequested {
		actions = List.copyOf(actions);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.RECOVERY_SELECTION_REQUESTED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
