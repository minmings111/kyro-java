package com.kyro.kyro.common.event;

public enum EventSubject {
	CLUSTER_EVIDENCE_RECEIVED("cluster.evidence.received"),
	EVIDENCE_BUILT("evidence.built"),
	INCIDENT_DETECTED("incident.detected"),
	EVIDENCE_BUNDLE_BUILT("evidence.bundle.built"),
	RCA_CANDIDATES_PLANNED("rca.candidates.planned"),
	RCA_CANDIDATES_EVALUATED("rca.candidates.evaluated"),
	RCA_ANALYSIS_BLOCKED("rca.analysis_blocked"),
	RCA_COMPLETED("rca.completed"),
	RECOVERY_PLANNED("recovery.planned"),
	RECOVERY_SELECTION_REQUESTED("recovery.selection_requested"),
	RECOVERY_ACTION_SELECTED("recovery.action_selected"),
	RCA_ACTION_REQUIRED("rca.action_required"),
	SAFE_PR_REQUESTED("safe_pr.requested"),
	SAFE_PR_PATCH_PREPARED("safe_pr.patch_prepared"),
	SAFE_PR_READY_FOR_CREATION("safe_pr.ready_for_creation"),
	SAFE_PR_CREATED("safe_pr.created"),
	SAFE_PR_FAILED("safe_pr.failed"),
	RECOVERY_VERIFICATION_STARTED("recovery.verification.started"),
	RECOVERY_VERIFICATION_FAILED("recovery.verification.failed"),
	INCIDENT_RESOLVED("incident.resolved");

	private final String subject;

	EventSubject(String subject) {
		this.subject = subject;
	}

	public String subject() {
		return subject;
	}
}
