# SopMobGuard

`SopMobGuard` is a lightweight protection plugin for mob interactions and mob-related rules.

It is built against the `1.16.5` API baseline, uses `SopLib`, and can format player-facing messages through `PlaceholderAPI`.

## Requirements

- Java 8+
- server build compatible with the `1.16.5` API baseline
- [`SopLib`](https://github.com/enels0n/SopLib)

Optional:

- `PlaceholderAPI`

## Build

```bash
mvn -DskipTests package
```

Output:

- `target/SopMobGuard.jar`

## Command

- `/sopmobguard`

## Notes

- runtime compatibility for supported server versions follows `SopLib`
- configuration is stored in `config.yml`
