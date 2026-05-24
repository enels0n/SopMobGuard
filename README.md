# SopMobGuard

`SopMobGuard` is a lightweight protection plugin for mob interactions and mob-related rules.

It is built against the `1.16.5` API baseline, uses `SopLib`, and can format player-facing messages through `PlaceholderAPI`.

## What It Does

`SopMobGuard` protects specific named monsters inside protected regions.

If a monster has the configured custom name, only region members or owners can:

- hit that monster
- make tamed pets attack that monster
- be targeted by that monster

For everyone else, those interactions are blocked.

## How To Use

1. Create or summon a hostile mob.
2. Give that mob the protected custom name from `config.yml`.
   Default:
   ```yml
   protected-monster-name: "protected"
   ```
3. Place the mob inside a region handled by the protection layer from `SopLib`.
4. Add players to that region if you want them to be allowed to fight or interact with that mob.

### Example

If the config contains:

```yml
protected-monster-name: "protected"
```

then a zombie named `protected` becomes guarded by the plugin.

- region owners/members can attack it normally
- other players cannot damage it
- other players and their pets are also prevented from engaging with it

## Admin Use

- `/sopmobguard reload` reloads `config.yml`
- the command is effectively restricted to server operators

## Important Notes

- only monsters with the exact configured custom name are protected
- unnamed mobs are ignored
- region checks are delegated to `SopLib`
- if `SopLib` is missing, region protection checks cannot be applied as intended

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
