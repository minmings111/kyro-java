package com.kyro.kyro.common.event;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record EventMetadata(
		String eventId,
		String correlationId,
		String causationId,
		Instant occurredAt
) {

	public EventMetadata {
		eventId = requireText(eventId, "eventId");
		correlationId = requireText(correlationId, "correlationId");
		occurredAt = Objects.requireNonNull(occurredAt, "occurredAt");
	}

	public static EventMetadata start() {
		var eventId = UUID.randomUUID().toString();
		return new EventMetadata(eventId, eventId, null, Instant.now());
	}

	public EventMetadata child() {
		return new EventMetadata(
				UUID.randomUUID().toString(),
				correlationId,
				eventId,
				Instant.now()
		);
	}

	private static String requireText(String value, String name) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(name + " must not be blank");
		}
		return value;
	}
}
