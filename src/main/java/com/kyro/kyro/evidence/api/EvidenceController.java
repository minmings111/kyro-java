package com.kyro.kyro.evidence.api;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kyro.kyro.common.api.ApiResponse;
import com.kyro.kyro.evidence.application.EvidenceIngestionService;
import com.kyro.kyro.evidence.domain.EvidenceReceivedRequest;

@RestController
@RequestMapping("/api/evidence")
class EvidenceController {

	private final EvidenceIngestionService evidence;

	EvidenceController(EvidenceIngestionService evidence) {
		this.evidence = evidence;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.ACCEPTED)
	ApiResponse<EvidenceIngestionService.AcceptedEvidence> receive(
			@Valid @RequestBody EvidenceReceivedRequest request
	) {
		return ApiResponse.accepted(evidence.submit(request));
	}
}
