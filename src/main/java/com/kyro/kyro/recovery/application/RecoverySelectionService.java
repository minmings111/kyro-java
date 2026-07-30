package com.kyro.kyro.recovery.application;

import org.springframework.stereotype.Service;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.recovery.domain.RecoveryActionSelected;
import com.kyro.kyro.recovery.domain.RecoverySelectionRequest;

@Service
public class RecoverySelectionService {

	private final KyroEventPublisher events;

	public RecoverySelectionService(KyroEventPublisher events) {
		this.events = events;
	}

	public RecoveryActionSelected select(String incidentId, RecoverySelectionRequest request) {
		var selected = new RecoveryActionSelected(
				EventMetadata.start(),
				incidentId,
				request.actionId(),
				request.actionType(),
				"pull_request",
				request.repository(),
				request.baseSha(),
				request.sourcePath(),
				request.patchSummary()
		);
		events.publish(selected);
		return selected;
	}
}
