package com.kyro.kyro.rca.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;

public record RcaAnalysisBlocked(
		EventMetadata metadata,
		String incidentId,
		String reason,
		List<String> requiredEvidence
) implements KyroEvent {

	public RcaAnalysisBlocked {
		requiredEvidence = List.copyOf(requiredEvidence);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.RCA_ANALYSIS_BLOCKED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
