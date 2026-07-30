package com.kyro.kyro.evidence.domain;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEvent;
import com.kyro.kyro.target.domain.TargetRef;

public record ClusterEvidenceReceived(
		EventMetadata metadata,
		String evidenceId,
		TargetRef target,
		String namespace,
		String workloadName,
		String reason,
		String message,
		String observedImage,
		String desiredImage,
		String sourceRef
) implements KyroEvent {

	@Override
	public EventSubject subject() {
		return EventSubject.CLUSTER_EVIDENCE_RECEIVED;
	}

	@Override
	public String aggregateId() {
		return evidenceId;
	}
}
