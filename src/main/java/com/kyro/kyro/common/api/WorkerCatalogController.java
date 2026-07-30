package com.kyro.kyro.common.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kyro.kyro.common.worker.WorkerCatalog;
import com.kyro.kyro.common.worker.WorkerDescriptor;

@RestController
@RequestMapping("/api/system/workers")
class WorkerCatalogController {

	private final WorkerCatalog workers;

	WorkerCatalogController(WorkerCatalog workers) {
		this.workers = workers;
	}

	@GetMapping
	ApiResponse<List<WorkerDescriptor>> list() {
		return ApiResponse.ok(workers.workers());
	}
}
