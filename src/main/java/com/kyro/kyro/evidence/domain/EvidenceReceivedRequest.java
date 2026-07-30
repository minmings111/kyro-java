package com.kyro.kyro.evidence.domain;

import jakarta.validation.constraints.NotBlank;

public record EvidenceReceivedRequest(
		@NotBlank String workspaceId,
		@NotBlank String targetId,
		@NotBlank String clusterName,
		@NotBlank String namespace,
		@NotBlank String workloadName,
		@NotBlank String reason,
		String message,
		String observedImage,
		String desiredImage,
		String sourceRef
) {
}
