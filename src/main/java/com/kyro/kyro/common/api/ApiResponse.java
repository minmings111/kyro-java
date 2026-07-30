package com.kyro.kyro.common.api;

public record ApiResponse<T>(String status, T data) {

	public static <T> ApiResponse<T> accepted(T data) {
		return new ApiResponse<>("accepted", data);
	}

	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>("ok", data);
	}
}
