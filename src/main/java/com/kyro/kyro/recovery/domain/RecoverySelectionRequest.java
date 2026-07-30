package com.kyro.kyro.recovery.domain;

import jakarta.validation.constraints.NotBlank;

public record RecoverySelectionRequest(
		@NotBlank String actionId,
		@NotBlank String actionType,
		@NotBlank String repository,
		@NotBlank String baseSha,
		@NotBlank String sourcePath,
		@NotBlank String patchSummary
) {
}
