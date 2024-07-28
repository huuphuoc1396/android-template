# Android Template

Welcome to the Android Template repository! This project is a modern Android template designed to help you kickstart your Android development with best practices and the latest technologies.

## Key Features

- **Clean Architecture**: Ensures a robust, scalable, and testable codebase.
- **MVVM (Model-View-ViewModel)**: Facilitates separation of concerns and promotes a cleaner architecture.
- **Jetpack Compose**: Modern toolkit for building native UI with less code.
- **Navigation Component**: Simplifies navigation and passing data between destinations.
- **Hilt**: Dependency injection library for Android that reduces the boilerplate of doing manual dependency injection.
- **Encrypted Datastore**: Provides a secure and simple way to store key-value pairs.
- **Coroutines and Flow**: Simplifies asynchronous programming and stream handling.
- **Retrofit**: Type-safe HTTP client for Android and Java.
- **Unit Testing**: Ensures your code is reliable and bug-free.

## Getting Started

### Prerequisites

- **Android Studio**: Download and install [Android Studio](https://developer.android.com/studio).
- **JDK 11**: Ensure you have JDK 11 installed and configured.

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/huuphuoc1396/android-template.git
   ```

2. **Open the project** in Android Studio.

3. **Build the project**: Let Gradle sync and build the project.

### Project Structure

- **data**: Contains data sources, repositories, and mappers.
- **domain**: Contains use cases, models, and repository interfaces.
- **presentation**: Contains UI components, ViewModels, and other presentation layer code.

### Key Components

#### Clean Architecture

This project follows Clean Architecture principles, separating the codebase into layers:

- **Presentation Layer**: Handles UI and user interactions.
- **Domain Layer**: Contains business logic and use cases.
- **Data Layer**: Manages data sources and repositories.

#### MVVM (Model-View-ViewModel)

- **Model**: Represents the data and business logic.
- **View**: Represents the UI components and user interface.
- **ViewModel**: Manages the data for the UI and handles the communication between the Model and the View.

#### Jetpack Compose

- **Declarative UI**: Build UI components with less code and in a more intuitive way.
- **State Management**: Manage UI state efficiently with Compose.

#### Navigation Component

- **Simplified Navigation**: Manage app navigation and pass data between destinations easily.
- **Safe Args**: Type-safe argument passing between fragments.

#### Hilt

- **Dependency Injection**: Provides a standardized way to handle dependency injection with less boilerplate.

#### Encrypted Datastore

- **Secure Data Storage**: Store sensitive key-value pairs securely.
- **Easy to Use**: Simple API to manage encrypted preferences.

#### Coroutines and Flow

- **Asynchronous Programming**: Write clean and concise asynchronous code.
- **Reactive Streams**: Handle streams of data efficiently with Flow.

#### Retrofit

- **HTTP Client**: Make network requests with ease.
- **Type-safe**: Ensures type safety and reduces errors.

#### Unit Testing

- **JUnit**: Write and run unit tests.
- **Mockito**: Mock dependencies and verify interactions.

### Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**.
2. **Create a new branch** for your feature or bugfix.
3. **Commit your changes**.
4. **Push to your branch**.
5. **Create a pull request**.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Contact

For any inquiries, please reach out to [Phuoc Bui](https://github.com/huuphuoc1396).
