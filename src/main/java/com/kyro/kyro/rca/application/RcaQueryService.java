package com.kyro.kyro.rca.application;

import org.springframework.stereotype.Service;

import com.kyro.kyro.common.event.EventMetadata;
import com.kyro.kyro.rca.domain.RcaCompleted;

@Service
public class RcaQueryService {

	public RcaCompleted findLatest(String incidentId) {
		return new RcaCompleted(
				EventMetadata.start(),
				incidentId,
				"unknown",
				"unknown",
				null,
				"not_loaded",
				"RCA persistence is not wired in this skeleton yet.",
				null,
				null,
				null
		);
	}
}
