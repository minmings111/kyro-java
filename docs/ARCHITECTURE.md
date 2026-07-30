# Architecture Notes

## What Was Carried Over

The Python project's durable backbone is event driven:

- typed event subjects
- correlation and causation metadata
- controller composition root
- worker handlers
- retry and recovery concepts
- domain routers and repository boundaries

This Java skeleton keeps those ideas and names, but leaves implementation depth out.

## What Was Dropped

- React/Vite frontend
- Helm, Terraform, Kind, AWS, and shell automation
- broad dashboard, terminal, cost, traffic, topology, and AI chat surfaces
- generated `.gitops` outputs
- direct Kubernetes mutation paths
- actual SCM provider calls
- external broker adapters for internal module communication

## Preferred Growth Path

1. Persist events and projections behind repository ports.
2. Replace placeholder RCA workers with rule catalog services.
3. Add source authority checks before `safe_pr.requested`.
4. Add SCM provider adapters behind a `PullRequestPort`.
5. Add verification events after merge/deploy signals.
6. Split modules into deployable workers only after the module contracts stabilize.
7. Add an external broker only when an event crosses a process boundary, such as a separate agent or worker service.

## Safety Defaults

The default skeleton follows the current safe operating boundary:

- agent access mode: `read_only`
- remediation delivery mode: `pull_request`
- production auto merge: disabled
- source path required before Safe PR creation
