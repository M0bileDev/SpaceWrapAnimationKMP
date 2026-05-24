# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project

Kotlin Multiplatform + Compose Multiplatform demo that renders an animated "space"/"hyperspace" effect (streaks of light) across Android, iOS, Desktop (JVM), Web (JS), and Web (Wasm) from a single `:composeApp` module.

Package root: `com.example.spacewrapanimationkmp`. Settings use `TYPESAFE_PROJECT_ACCESSORS` and there is only one Gradle subproject (`:composeApp`).

## Build & run

All commands run from the repo root via `./gradlew` (use `gradlew.bat` on Windows).

- Android debug APK: `./gradlew :composeApp:assembleDebug`
- Desktop (JVM) app: `./gradlew :composeApp:run` (main class `com.example.spacewrapanimationkmp.MainKt`; Compose hot-reload plugin is applied)
- Web (Wasm, preferred): `./gradlew :composeApp:wasmJsBrowserDevelopmentRun`
- Web (JS, older browsers): `./gradlew :composeApp:jsBrowserDevelopmentRun`
- iOS: open `iosApp/` in Xcode and run, or use the IDE run configuration. The iOS framework is built as a static framework named `ComposeApp` for `iosArm64` and `iosSimulatorArm64`.
- Tests: `./gradlew :composeApp:allTests` (or per-target, e.g. `:composeApp:jvmTest`). Single test: `./gradlew :composeApp:jvmTest --tests "fully.qualified.TestClass.method"`. There are currently no test sources, only the `kotlin-test` dependency in `commonTest`.

JVM toolchain is 11 (Android `sourceCompatibility`/`targetCompatibility` 11, Kotlin `jvmTarget` 11). Gradle configuration cache and build cache are enabled in `gradle.properties` — keep build scripts configuration-cache-safe.

Versions are centralized in `gradle/libs.versions.toml` (version catalog); add/upgrade dependencies there rather than hardcoding coordinates.

## Source layout & platform pattern

`composeApp/src/` uses standard KMP source sets:

- `commonMain` — all shared UI and logic (this is where almost everything lives).
- `androidMain`, `iosMain`, `jvmMain`, `jsMain`, `wasmJsMain` — per-target entry points and `actual` implementations.
- `webMain` — code shared between the `jsMain` and `wasmJsMain` browser targets (e.g. `main.kt`, plus `resources/index.html` and `styles.css`).

The `Platform` interface in `commonMain/.../Platform.kt` is the `expect`/`actual` pattern used here: each platform source set provides a `Platform.<target>.kt` with the `actual fun getPlatform()`. Follow the same pattern when adding platform-specific APIs — declare `expect` in `commonMain`, implement in every target.

## Animation architecture

The render pipeline is small but layered — understanding the chain matters because parameter changes flow top-down:

1. **`model/SpaceRepresentation`** — sealed-style `interface` with nested `data class` variants (currently just `LightStreak`). Each variant carries the entire visual configuration (colors, counts, duration, scale range, rotation). Add new visual modes by adding a new nested `data class` here.
2. **`components/Space`** (entry composable) — takes a `SpaceRepresentation`, reads window size via `LocalWindowInfo` and orientation via `utils/currentOrientation()`, computes a travel offset, then `when`-dispatches on the representation type. For `LightStreak` it spawns `lightStreakGroupCount * lightStreakCount` animations with staggered delays. **Adding a new `SpaceRepresentation` variant requires adding a branch to this `when`.**
3. **`animations/LightStreakAnimation`** — wraps `rememberInfiniteTransition` to drive `scale` and `offset` floats, then hands them to a caller-provided composable lambda `onUpdate(Scale, Offset)`. Animation params (delay, duration, target scale/offset) are passed in by `Space`.
4. **`shapes/LightStreakShape`** — leaf composable: three rounded `Box`es stacked, rotated by `degrees`, transformed via `graphicsLayer { rotationX, scaleX/Y, translationY }`. Note: `degrees` and `color` are intentionally captured in `remember { mutableStateOf(...) }` so they stay stable per instance across recompositions (this is deliberate — see the `refactor(shapes): keep degrees and color as remembered` commit).

`utils/Orientation.currentOrientation()` is the single source of truth for portrait vs landscape — it reads `LocalWindowInfo.current.containerSize` so it works uniformly across all targets (no Android `Configuration` calls). Use it instead of platform-specific orientation APIs.

`App.kt` is the shared root composable wired into every platform's entry point (`MainActivity` on Android, `MainViewController` on iOS, `main.kt` on JVM/web).
