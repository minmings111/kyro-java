package com.kyro.kyro.recovery.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.kyro.kyro.common.event.KyroEventPublisher;
import com.kyro.kyro.recovery.domain.RecoveryActionSelected;
import com.kyro.kyro.recovery.domain.RecoveryPlanned;
import com.kyro.kyro.recovery.domain.RecoverySelectionRequested;

@Component
class SelectionWorker {

	private final KyroEventPublisher events;

	SelectionWorker(KyroEventPublisher events) {
		this.events = events;
	}

	@EventListener
	void on(RecoveryPlanned event) {
		if (event.actions().size() == 1 && !event.actions().getFirst().requiresSelection()) {
			var action = event.actions().getFirst();
			events.publish(new RecoveryActionSelected(
					event.metadata().child(),
					event.incidentId(),
					action.actionId(),
					action.actionType(),
					action.deliveryMode(),
					action.repository(),
					action.baseSha(),
					action.sourcePath(),
					action.description()
			));
			return;
		}
		events.publish(new RecoverySelectionRequested(
				event.metadata().child(),
				event.incidentId(),
				event.actions()
		));
	}
}
