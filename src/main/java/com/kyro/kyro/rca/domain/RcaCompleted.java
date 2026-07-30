package com.kyro.kyro.rca.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record RcaCompleted(
		EventMetadata metadata,
		String incidentId,
		String incidentKey,
		String evidenceId,
		TargetRef target,
		String causeCode,
		String narrative,
		List<String> candidateCauses,
		List<String> evidenceNotes,
		String sourceRef
) implements KyroEvent {

	public RcaCompleted {
		candidateCauses = candidateCauses == null ? List.of() : List.copyOf(candidateCauses);
		evidenceNotes = evidenceNotes == null ? List.of() : List.copyOf(evidenceNotes);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.RCA_COMPLETED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
