package com.kyro.kyro.recovery.domain;

public record RecoveryAction(
		String actionId,
		String actionType,
		String description,
		boolean requiresSelection,
		String deliveryMode,
		String repository,
		String baseSha,
		String sourcePath
) {
}
