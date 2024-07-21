# Android Clean Architecture Template

This Android Template is designed to streamline the development of Android applications using Clean Architecture principles. It provides a robust foundation for building scalable, maintainable, and testable applications by separating concerns into distinct layers.

## Features

- **Clean Architecture Layers**: Organized into `Data`, `Domain`, and `Presentation` layers to ensure separation of concerns.
- **Kotlin and Java Support**: Utilize the power of Kotlin along with Java compatibility.
- **Gradle Configuration**: Centralized dependency management with `gradle/libs.versions.toml`.
- **Jetpack Compose**: Modern toolkit for building native UIs.
- **Hilt for Dependency Injection**: Simplify dependency injection with Hilt, configured across modules.
- **MVVM Pattern**: Leverage the Model-View-ViewModel pattern in the Presentation layer for a reactive UI.
- **Coroutines & Flow**: Asynchronous programming with Kotlin Coroutines and Flow.
- **Retrofit & Room**: Network communication with Retrofit and local database management with Room.
- **Unit & Integration Tests**: Pre-configured setups for writing unit and integration tests.

## Getting Started

1. **Clone the Repository**: Clone this template to start your project.
```
git clone https://github.com/huuphuoc1396/android-template.git
```
2. **Open in Android Studio Koala | 2024.1.1**: Ensure compatibility and access to the latest features.
3. **Configure Dependencies**: Check `gradle/libs.versions.toml` and module-level `build.gradle.kts` files to adjust dependencies as needed.
4. **Build the Project**: Compile the project to verify setup and dependencies.

## Project Structure

- `app/`: Entry point of the application, containing the Presentation layer.
- `domain/`: Business logic and use cases of the application.
- `data/`: Data handling layer, including network and database operations.
- `gradle/`: Contains Gradle wrapper and configuration files.

## Key Dependencies

- **Jetpack Compose**: For building modern, native UIs efficiently with Kotlin.
- **Hilt**: Dependency injection framework.
- **Retrofit**: HTTP client for Android.
- **Room**: Abstraction layer over SQLite.
- **Kotlin Coroutines**: For managing background threads.
- **Flow**: Handling asynchronous data streams.

## Contributing

Contributions are welcome. Please adhere to this project's `code of conduct`.

## License

Distributed under the MIT License. See `LICENSE` for more information.