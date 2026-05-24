# SpaceWrapAnimationKMP

A Kotlin Multiplatform + Compose Multiplatform demo that renders an animated
hyperspace-style "light streak" effect — streaks of colour fanning out from
the screen centre with a 3D perspective tilt. Runs from a single shared
`commonMain` codebase on Android, iOS, Desktop (JVM), Web (JS), and Web
(Wasm).

<video src="screenshots/demo.mp4" controls width="600">
  Your viewer doesn't render inline video — see <code>screenshots/demo.mp4</code>.
</video>

## Supported targets

| Target              | Source set     | Entry point                       |
|---------------------|----------------|-----------------------------------|
| Android             | `androidMain`  | `MainActivity`                    |
| iOS (arm64 + sim)   | `iosMain`      | `MainViewController` (Swift)      |
| Desktop (JVM)       | `jvmMain`      | `main.kt`                         |
| Web (Wasm)          | `wasmJsMain`   | `webMain/main.kt`                 |
| Web (JS)            | `jsMain`       | `webMain/main.kt`                 |

JVM toolchain is 11; versions live in `gradle/libs.versions.toml`. The
Compose hot-reload plugin is wired up for the Desktop target.

## Build & run

```shell
# Android debug APK
./gradlew :composeApp:assembleDebug

# Desktop (JVM)
./gradlew :composeApp:run

# Web — Wasm (preferred, modern browsers)
./gradlew :composeApp:wasmJsBrowserDevelopmentRun

# Web — JS (older browsers)
./gradlew :composeApp:jsBrowserDevelopmentRun
```

iOS: open `iosApp/` in Xcode and run, or use the IDE run configuration. The
Kotlin code is exposed as a static framework named `ComposeApp`.

## Usage

The whole effect is a single composable that takes a `SpaceRepresentation`:

```kotlin
import com.example.spacewrapanimationkmp.components.Space
import com.example.spacewrapanimationkmp.model.SpaceRepresentation

@Composable
fun App() = MaterialTheme {
    Space(
        modifier = Modifier.fillMaxSize(),
        spaceRepresentation = SpaceRepresentation.LightStreak()
    )
}
```

`SpaceRepresentation` is a sealed-style interface; today only the
`LightStreak` variant is implemented. Add new visual modes by adding a new
nested `data class` and a branch to the `when` inside `Space`.

### LightStreak configuration

All parameters are optional and have sensible defaults:

| Parameter                | Default                                | Effect                              |
|--------------------------|----------------------------------------|-------------------------------------|
| `colors`                 | yellow, blue, red, cyan, magenta, green| Pool sampled randomly per streak    |
| `durationMillis`         | `6_000`                                | One full streak travel cycle        |
| `lightStreakCount`       | `100`                                  | Streaks per group                   |
| `lightStreakGroupCount`  | `1`                                    | Number of staggered groups          |
| `scaleRange`             | `1..4`                                 | Per-streak max scale, sampled       |
| `rotation`               | `-70f`                                 | `rotationX` tilt (degrees)          |

Background colour is a separate `Space` parameter (`spaceBackground`,
default `Color.Black`).

## Project layout

Single Gradle module: `:composeApp`.

```
composeApp/src/
  commonMain/kotlin/com/example/spacewrapanimationkmp/
    App.kt                                    # root composable
    Platform.kt                               # expect interface
    animations/LightStreakAnimation.kt        # rememberInfiniteTransition wrapper
    components/Space.kt                       # entry composable, spawns streaks
    model/SpaceRepresentation.kt              # configuration sealed-style interface
    shapes/LightStreakShape.kt                # three-box streak primitive
  androidMain/ iosMain/ jvmMain/ jsMain/      # platform entry points + actual getPlatform()
  wasmJsMain/ webMain/                        # web entry + browser resources
```

See `CLAUDE.md` for deeper notes on the animation pipeline and the
recomposition rules that have to be respected when extending it.
