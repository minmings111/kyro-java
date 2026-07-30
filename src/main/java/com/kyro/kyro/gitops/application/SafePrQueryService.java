package com.kyro.kyro.gitops.application;

import org.springframework.stereotype.Service;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.gitops.domain.SafePrCreated;

@Service
public class SafePrQueryService {

	public SafePrCreated findLatest(String incidentId) {
		return new SafePrCreated(
				EventMetadata.start(),
				incidentId,
				"unknown",
				"unbound",
				"unknown",
				"unknown",
				"not_created",
				null
		);
	}
}
