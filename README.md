# 🌤️ SkyPulse - Dynamic Weather App

A beautiful Android weather application built with Jetpack Compose that dynamically changes its theme based on your location and current weather conditions.

## 🎥 Demo Video

https://github.com/user-attachments/assets/61fe0de0-a19b-4911-b1ad-0f9ae66c48ed

## ✨ Features

### 🎨 Dynamic Theme System
- **Location-Based Themes**: The app automatically adapts its visual theme based on your current location
- **Weather-Responsive UI**: Theme colors and gradients change dynamically based on weather conditions
- **Day/Night Mode**: Automatic theme switching between day (light blue gradient) and night (dark purple gradient) modes

### 🌍 Location Services
- **GPS Location Detection**: Automatically detects your current location using GPS
- **City Name Resolution**: Converts coordinates to readable city names using Geocoder
- **Permission Handling**: Smart permission management for location access

### 🌤️ Weather Information
- **Current Weather**: Real-time temperature, humidity, wind speed, and atmospheric pressure
- **Hourly Forecast**: Detailed hourly weather predictions
- **Daily Forecast**: Multi-day weather outlook with min/max temperatures
- **Weather Icons**: Beautiful, context-aware weather icons for different conditions
- **Comprehensive Data**: UV index, precipitation probability, and surface pressure

### 🎯 Weather Conditions Supported
- Clear sky
- Partly cloudy
- Overcast
- Fog conditions
- Various drizzle intensities
- Rain and precipitation
- Snow conditions
- Thunderstorms
- And many more weather types

## 🛠️ Technical Stack

### Architecture
- **MVVM Pattern**: Clean separation of concerns with ViewModels
- **Clean Architecture**: Domain, Data, and Presentation layers
- **Dependency Injection**: Koin for dependency management

### Technologies Used
- **Jetpack Compose**: Modern Android UI toolkit
- **Kotlin**: Primary programming language
- **Ktor**: HTTP client for API communication
- **Kotlin Coroutines**: Asynchronous programming
- **Navigation Compose**: Type-safe navigation
- **Material Design 3**: Modern design system
- **Accompanist**: Additional Compose utilities

### API Integration
- **Open-Meteo API**: Free weather forecast API
- **Real-time Data**: Live weather updates
- **Global Coverage**: Weather data for locations worldwide

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Android SDK 24+ (Android 7.0)
- Kotlin 2.0.21+
- Gradle 8.10.1+

### Permissions
The app requires the following permissions:
- `ACCESS_FINE_LOCATION`: For precise GPS location
- `ACCESS_COARSE_LOCATION`: For approximate location
- `INTERNET`: For weather API calls

## 🏗️ Project Structure

```
app/src/main/java/com/asoom/skypulse/
├── data/                    # Data layer
│   ├── location/           # GPS and location services
│   ├── remote/             # API communication
│   ├── repository/         # Data repositories
│   └── utils/              # Constants and utilities
├── domain/                 # Domain layer
│   ├── model/              # Business models
│   ├── repository/         # Repository interfaces
│   ├── usecase/            # Business logic
│   └── util/               # Domain utilities
├── presentation/           # Presentation layer
│   ├── component/          # Reusable UI components
│   ├── screen/             # App screens
│   ├── state/              # UI state management
│   ├── theme/              # App theming
│   └── viewModel/          # ViewModels
└── di/                     # Dependency injection
```

## 🎨 Theme System

The app features a sophisticated theme system that responds to environmental conditions:

### Day Theme
- **Primary**: Light sky blue gradient
- **Background**: White to light blue transition
- **Icons**: Bright, sunny weather icons

### Night Theme
- **Primary**: Dark purple gradient
- **Background**: Deep purple to darker purple transition
- **Icons**: Moonlit weather icons

### Weather-Responsive Elements
- Dynamic background gradients
- Context-aware weather icons
- Adaptive color schemes
- Smooth theme transitions

## 🔧 Configuration

### API Configuration
The app uses Open-Meteo API (free, no API key required):
```kotlin
const val BASE_URL = "https://api.open-meteo.com/v1/forecast"
```

### Location Services
Configured for optimal battery usage and accuracy:
- GPS provider for precise location
- Geocoder for address resolution
- Permission-aware location handling

## 📦 Dependencies

Key dependencies include:
- **Compose BOM**: 2025.06.00
- **Ktor**: 2.3.8 (HTTP client)
- **Koin**: 3.5.3 (Dependency injection)
- **Coroutines**: 1.10.2 (Async programming)
- **Navigation Compose**: 2.7.0
- **Accompanist**: 0.31.4-beta (Compose utilities)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Asmaa**
- GitHub: [@yourusername]([https://github.com/yourusername](https://github.com/AsmaaKhaledGomaa))

## 🙏 Acknowledgments

- [Open-Meteo](https://open-meteo.com/) for providing free weather API
- [Jetpack Compose](https://developer.android.com/jetpack/compose) team for the amazing UI toolkit
- Android community for inspiration and support

---

**Note**: This app demonstrates dynamic theming based on location and weather conditions. The theme automatically adapts to provide an immersive, context-aware user experience that reflects the current environmental conditions.
