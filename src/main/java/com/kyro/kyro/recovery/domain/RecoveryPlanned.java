package com.kyro.kyro.recovery.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record RecoveryPlanned(
		EventMetadata metadata,
		String incidentId,
		String causeCode,
		List<RecoveryAction> actions
) implements KyroEvent {

	public RecoveryPlanned {
		actions = List.copyOf(actions);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.RECOVERY_PLANNED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
