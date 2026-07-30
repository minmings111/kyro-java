package com.kyro.kyro.recovery.api;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.kyro.kyro.common.api.ApiResponse;
import com.kyro.kyro.recovery.application.RecoverySelectionService;
import com.kyro.kyro.recovery.domain.RecoveryActionSelected;
import com.kyro.kyro.recovery.domain.RecoverySelectionRequest;

@RestController
@RequestMapping("/api/recovery/actions")
class RecoveryController {

	private final RecoverySelectionService recovery;

	RecoveryController(RecoverySelectionService recovery) {
		this.recovery = recovery;
	}

	@PostMapping("/{incidentId}/select")
	@ResponseStatus(HttpStatus.ACCEPTED)
	ApiResponse<RecoveryActionSelected> select(
			@PathVariable String incidentId,
			@Valid @RequestBody RecoverySelectionRequest request
	) {
		return ApiResponse.accepted(recovery.select(incidentId, request));
	}
}
