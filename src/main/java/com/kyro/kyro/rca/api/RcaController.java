package com.kyro.kyro.rca.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyro.kyro.common.api.ApiResponse;
import com.kyro.kyro.rca.application.RcaQueryService;
import com.kyro.kyro.rca.domain.RcaCompleted;

@RestController
@RequestMapping("/api/incidents/{incidentId}/rca")
class RcaController {

	private final RcaQueryService rca;

	RcaController(RcaQueryService rca) {
		this.rca = rca;
	}

	@GetMapping
	ApiResponse<RcaCompleted> get(@PathVariable String incidentId) {
		return ApiResponse.ok(rca.findLatest(incidentId));
	}
}
