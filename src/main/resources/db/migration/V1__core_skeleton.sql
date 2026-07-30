create table if not exists kyro_event_log (
    event_id varchar(64) primary key,
    subject varchar(160) not null,
    aggregate_id varchar(160) not null,
    correlation_id varchar(64) not null,
    causation_id varchar(64),
    occurred_at timestamp with time zone not null,
    payload text not null default '{}'
);

create index if not exists idx_kyro_event_log_correlation
    on kyro_event_log (correlation_id, occurred_at);
