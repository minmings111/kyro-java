package com.kyro.kyro.common.worker;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class WorkerCatalog {

	private final List<WorkerDescriptor> workers = List.of(
			new WorkerDescriptor("evidence-worker", "cluster.evidence.received", "evidence.built"),
			new WorkerDescriptor("incident-worker", "evidence.built", "incident.detected, evidence.bundle.built"),
			new WorkerDescriptor("plan-worker", "evidence.bundle.built", "rca.candidates.planned"),
			new WorkerDescriptor("analyze-worker", "rca.candidates.planned", "rca.candidates.evaluated"),
			new WorkerDescriptor("rca-worker", "rca.candidates.evaluated", "rca.completed or rca.analysis_blocked"),
			new WorkerDescriptor("recovery-worker", "rca.completed", "recovery.planned"),
			new WorkerDescriptor("select-worker", "recovery.planned", "recovery.action_selected or recovery.selection_requested"),
			new WorkerDescriptor("dispatch-worker", "recovery.action_selected", "safe_pr.requested or rca.action_required"),
			new WorkerDescriptor("safe-pr-worker", "safe_pr.requested", "safe_pr.patch_prepared"),
			new WorkerDescriptor("ai-diff-worker", "safe_pr.patch_prepared", "safe_pr.ready_for_creation or safe_pr.failed"),
			new WorkerDescriptor("scm-worker", "safe_pr.ready_for_creation", "safe_pr.created")
	);

	public List<WorkerDescriptor> workers() {
		return workers;
	}
}
