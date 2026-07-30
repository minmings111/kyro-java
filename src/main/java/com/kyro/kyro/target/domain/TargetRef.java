package com.kyro.kyro.target.domain;

public record TargetRef(
		String workspaceId,
		String targetId,
		String clusterName
) {
}
