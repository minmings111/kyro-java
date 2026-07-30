package com.kyro.kyro.gitops.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyro.kyro.common.api.ApiResponse;
import com.kyro.kyro.gitops.application.SafePrQueryService;
import com.kyro.kyro.gitops.domain.SafePrCreated;

@RestController
@RequestMapping("/api/safe-pr")
class SafePrController {

	private final SafePrQueryService safePr;

	SafePrController(SafePrQueryService safePr) {
		this.safePr = safePr;
	}

	@GetMapping("/{incidentId}")
	ApiResponse<SafePrCreated> get(@PathVariable String incidentId) {
		return ApiResponse.ok(safePr.findLatest(incidentId));
	}
}
