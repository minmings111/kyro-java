package com.kyro.kyro.rca.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record RcaCandidatesPlanned(
		EventMetadata metadata,
		String incidentId,
		String incidentKey,
		String evidenceId,
		TargetRef target,
		List<String> candidateCauses,
		List<String> symptoms,
		String observedImage,
		String desiredImage,
		String sourceRef
) implements KyroEvent {

	public RcaCandidatesPlanned {
		candidateCauses = List.copyOf(candidateCauses);
		symptoms = List.copyOf(symptoms);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.RCA_CANDIDATES_PLANNED;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
