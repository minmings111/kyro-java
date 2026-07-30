package com.kyro.kyro.common.event;

public interface KyroEvent {

	EventSubject subject();

	EventMetadata metadata();

	String aggregateId();
}
