package com.kyro.kyro.rca.domain;

import java.util.List;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record EvidenceBundleBuilt(
		EventMetadata metadata,
		String incidentId,
		String incidentKey,
		String evidenceId,
		TargetRef target,
		List<String> symptoms,
		String observedImage,
		String desiredImage,
		String sourceRef
) implements KyroEvent {

	public EvidenceBundleBuilt {
		symptoms = List.copyOf(symptoms);
	}

	@Override
	public EventSubject subject() {
		return EventSubject.EVIDENCE_BUNDLE_BUILT;
	}

	@Override
	public String aggregateId() {
		return incidentId;
	}
}
