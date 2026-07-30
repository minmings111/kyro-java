# Kyro

Kyro는 Kubernetes 서비스에서 발생한 장애를 분석하고, 원인 추론부터 안전한 복구 변경까지 이어주는 자동화 시스템입니다.

이 저장소는 Kyro를 Java/Spring Boot 기반으로 다시 다듬기 위한 백엔드 프로젝트입니다. 현재는 기존 Python 프로젝트의 핵심 흐름만 옮겨온 가벼운 시작점이며, 프론트엔드와 배포 자동화, 실제 외부 연동 구현은 포함하지 않습니다.

원본 프로젝트 `C:\workspace\python_project\Kyro-jungle-final`은 수정하지 않았습니다.

## 핵심 방향

Kyro는 먼저 모듈형 모놀리스로 구성합니다.

- Spring Boot로 애플리케이션 실행 경계를 잡습니다.
- Spring Modulith로 도메인 모듈을 명확하게 나눕니다.
- 모듈 간 통신은 Spring Application Event를 우선 사용합니다.
- Spring Modulith의 JPA event publication registry로 이벤트 처리 내구성을 확보할 수 있게 둡니다.
- Flyway와 PostgreSQL 호환 스키마를 기준으로 데이터베이스 구조를 확장합니다.
- Java record로 요청, 응답, 이벤트 payload 계약을 간결하게 표현합니다.

이 구조는 Python 프로젝트의 컨트롤러, 이벤트, 워커 흐름을 Java에서 다시 잡기 위한 기반입니다. 모든 워커를 처음부터 별도 서비스로 분리하기보다, 모듈 계약을 먼저 안정화하는 방향을 선택했습니다.

## 핵심 흐름

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

현재 코드는 의도적으로 얇게 유지되어 있습니다. 로직이 들어갈 위치와 이벤트 흐름을 보여주지만, Kubernetes API, Prometheus, Loki, Tempo, GitHub/SCM, Redis, AI provider, WebSocket gateway, React console은 아직 연결하지 않았습니다.

내부 모듈 통신은 Spring Application Event를 기준으로 하며, 외부 메시지 브로커는 현재 범위에 포함하지 않습니다.

## 패키지 구성

| Java package | Python 프로젝트에서 가져온 개념 | 역할 |
| --- | --- | --- |
| `common.event` | `packages/contracts/event_bus`, `packages/events`, `packages/runtime` | 이벤트 subject, metadata, publisher port |
| `common.worker` | `src/services/**/app.py`, `scripts/services.py` | 워커 목록과 실행 슬롯 |
| `target` | `domains/target`, `services/target/cluster-agent` | 분석 대상 식별자 |
| `evidence` | `domains/evidence`, `services/ai/evidence-worker` | 장애 증거 수집과 정규화 진입점 |
| `incident` | AI/RCA 흐름의 incident signal 처리 | 장애 식별 이벤트 |
| `rca` | `domains/rca`, `services/ai/*worker` | 원인 분석 계획, 평가, 결정 |
| `recovery` | recovery selection, dispatch setup | 복구 후보 선택과 액션 계획 |
| `gitops` | `domains/gitops`, `services/gitops/*worker` | 안전한 PR 생성 준비 |
| `audit` | `domains/audit`, `audit-worker` | 이벤트 감사 listener |

## 로컬 실행

Gradle wrapper가 포함되어 있습니다. JDK를 설치한 뒤 아래 명령으로 실행할 수 있습니다.

```powershell
cd C:\workspace\java_project
cd .\kyro-java
.\gradlew.bat test
.\gradlew.bat bootRun
```

기본 datasource는 로컬 실행을 쉽게 하기 위해 PostgreSQL 호환 모드의 H2를 사용합니다.

PostgreSQL로 연결하려면 아래 환경 변수를 설정합니다.

```powershell
$env:KYRO_DATASOURCE_URL = "jdbc:postgresql://localhost:5432/kyro"
$env:KYRO_DATASOURCE_USERNAME = "kyro"
$env:KYRO_DATASOURCE_PASSWORD = "kyro"
```

## API 시작점

- `POST /api/evidence`: Kubernetes 장애 증거 payload를 받아 이벤트 흐름을 시작합니다.
- `GET /api/system/workers`: Python Golden Path에서 옮겨온 워커 슬롯 목록을 확인합니다.
- `GET /api/incidents/{incidentId}/rca`: RCA 결과 조회를 위한 placeholder endpoint입니다.
- `POST /api/recovery/actions/{incidentId}/select`: 복구 액션 수동 선택을 위한 placeholder endpoint입니다.
- `GET /api/safe-pr/{incidentId}`: Safe PR 상태 조회를 위한 placeholder endpoint입니다.

예시 evidence payload:

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

## 앞으로 채워갈 것

- Kubernetes, observability, SCM provider adapter 연결
- RCA rule catalog와 AI 분석 provider 분리
- incident, evidence, recovery, safe PR projection 저장소 구현
- 복구 변경 생성 전 source authority와 safety policy 검증 강화
- 모듈 계약이 안정화된 뒤 필요한 워커만 별도 프로세스로 분리
