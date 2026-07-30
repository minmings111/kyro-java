package com.kyro.kyro.evidence.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record EvidenceBuilt(
		EventMetadata metadata,
		String evidenceId,
		String incidentKey,
		TargetRef target,
		String summary,
		String reason,
		String observedImage,
		String desiredImage,
		String sourceRef
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.EVIDENCE_BUILT;
	}

	@Override
	public String aggregateId() {
		return incidentKey;
	}
}
