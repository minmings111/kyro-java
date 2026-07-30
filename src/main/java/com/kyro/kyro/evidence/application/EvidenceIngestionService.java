package com.kyro.kyro.evidence.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.EventSubject;
import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.evidence.domain.ClusterEvidenceReceived;
import com.kyro.kyro.evidence.domain.EvidenceReceivedRequest;
import com.kyro.kyro.target.domain.TargetRef;

@Service
public class EvidenceIngestionService {

	private final KyroEventPublisher events;

	public EvidenceIngestionService(KyroEventPublisher events) {
		this.events = events;
	}

	public AcceptedEvidence submit(EvidenceReceivedRequest request) {
		var evidenceId = UUID.randomUUID().toString();
		var target = new TargetRef(request.workspaceId(), request.targetId(), request.clusterName());
		events.publish(new ClusterEvidenceReceived(
				EventMetadata.start(),
				evidenceId,
				target,
				request.namespace(),
				request.workloadName(),
				request.reason(),
				request.message(),
				request.observedImage(),
				request.desiredImage(),
				request.sourceRef()
		));
		return new AcceptedEvidence(evidenceId, EventSubject.CLUSTER_EVIDENCE_RECEIVED.subject());
	}

	public record AcceptedEvidence(String evidenceId, String subject) {
	}
}
