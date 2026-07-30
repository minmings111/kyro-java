package com.kyro.kyro.rca.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record RcaCandidatesEvaluated(
		EventMetadata metadata,
		String incidentId,
		String incidentKey,
		String evidenceId,
		TargetRef target,
		List<String> candidateCauses,
		List<String> evaluationNotes
) implements KyroEvent {

	public RcaCandidatesEvaluated {
		candidateCauses = List.copyOf(candidateCauses);
		evaluationNotes = List.copyOf(evaluationNotes);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.RCA_CANDIDATES_EVALUATED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
