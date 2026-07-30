# Kyro Core Java Skeleton

This project is a Java/Spring Boot skeleton extracted from the Python backend shape in
`C:\workspace\python_project\Kyro-jungle-final`.

The original repository was not modified. Frontend, deployment scripts, Terraform, Helm charts,
local demos, and generated GitOps artifacts were intentionally left behind.

## Skeleton Choice

Use a modular monolith first:

- Spring Boot for the application/runtime boundary.
- Spring Modulith for explicit domain modules before splitting services.
- Spring application events as the first in-process event bus.
- Spring Modulith's JPA event publication registry for durable module-event handling.
- Flyway and PostgreSQL-compatible schema stubs for Kyro-owned projections.
- Java records for contracts and event payloads.

This mirrors the Python controller composition root without carrying over every worker as an
independent deployable unit on day one.

## Core Flow

```text
cluster.evidence.received
  -> evidence.built
  -> incident.detected + evidence.bundle.built
  -> rca.candidates.planned
  -> rca.candidates.evaluated
  -> rca.completed
  -> recovery.planned
  -> recovery.action_selected
  -> safe_pr.requested
  -> safe_pr.patch_prepared
  -> safe_pr.ready_for_creation
  -> safe_pr.created
```

The code is deliberately thin. It shows where logic belongs, but external providers are not wired:
Kubernetes API, Prometheus, Loki, Tempo, GitHub/SCM, Redis, AI providers, WebSocket gateway,
and the React console are all out of scope for this skeleton. External message brokers are
intentionally not part of the module-internal skeleton.

## Package Map

| Java package | Python source idea | Responsibility |
| --- | --- | --- |
| `common.event` | `packages/contracts/event_bus`, `packages/events`, `packages/runtime` | event subjects, metadata, publisher port |
| `common.worker` | `src/services/**/app.py`, `scripts/services.py` | visible worker catalog |
| `target` | `domains/target`, `services/target/cluster-agent` | target identity reference only |
| `evidence` | `domains/evidence`, `services/ai/evidence-worker` | evidence ingress and normalization slot |
| `incident` | incident signal handling in AI/RCA flow | incident identity slot |
| `rca` | `domains/rca`, `services/ai/*worker` | RCA plan/analyze/decision slots |
| `recovery` | recovery selection and dispatch setup | recovery action planning |
| `gitops` | `domains/gitops`, `services/gitops/*worker` | Safe PR preparation slots |
| `audit` | `domains/audit`, `audit-worker` | event audit listener placeholder |

## Local Run

The generated Gradle wrapper is included. Install a JDK first, then run:

```powershell
cd C:\workspace\java_project
cd .\kyro-java
.\gradlew.bat test
.\gradlew.bat bootRun
```

The default datasource is H2 in PostgreSQL compatibility mode so the skeleton can start locally.
Set `KYRO_DATASOURCE_URL`, `KYRO_DATASOURCE_USERNAME`, and `KYRO_DATASOURCE_PASSWORD` to point
at PostgreSQL when you are ready.

## First API Surface

- `POST /api/evidence`: accepts a minimal cluster evidence payload and starts the event chain.
- `GET /api/system/workers`: lists the worker slots carried over from the Python Golden Path.
- `GET /api/incidents/{incidentId}/rca`: placeholder query endpoint.
- `POST /api/recovery/actions/{incidentId}/select`: manual recovery selection placeholder.
- `GET /api/safe-pr/{incidentId}`: placeholder Safe PR query endpoint.

Example evidence payload:

```json
{
  "workspaceId": "local",
  "targetId": "kind-target",
  "clusterName": "kind",
  "namespace": "shop",
  "workloadName": "shop-api",
  "reason": "ImagePullBackOff",
  "message": "image tag does not exist",
  "observedImage": "registry.example/shop-api:bad",
  "desiredImage": "registry.example/shop-api:stable",
  "sourceRef": "apps/shop-api/deployment.yaml"
}
```
