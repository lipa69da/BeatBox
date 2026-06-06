# BeatBox 🎵

**BeatBox** is a Kotlin-based Android application developed as part of the hands-on guide by **Big Nerd Ranch**. The app is a soundboard that loads audio assets, manages low-latency sound playback, and demonstrates how to implement modern Android architectural components.

---

## 🛠️ Tech Stack & Architecture Concepts
* **Language**: Kotlin
* **Architecture**: MVVM (Model-View-ViewModel) to separate business logic from the UI
* **Audio**: `SoundPool` for fast, low-latency playback of short sound effects
* **Jetpack Components**: 
  * Data Binding (or View Binding) to connect XML layouts directly to ViewModel properties
  * ViewModel to retain screen state during configuration changes (like device rotation)
* **Testing**: Mockito for writing automated Unit Tests to verify ViewModel logic
* **UI Layout**: `RecyclerView` combined with `GridLayoutManager` to display the audio button grid

## 🚀 Getting Started

### Prerequisites
* Android Studio (Ladybug or newer)
* Android SDK (API 33+)
* Gradle 8.0+

### Installation & Setup
1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com
   ```
2. Open the project in **Android Studio** (`File -> Open`).
3. Wait for the Gradle sync to complete.
4. Run the application on an emulator or a physical Android device.

## 📝 Key Learning Outcomes
* Working with Android `assets` to dynamically import and manage external audio files.
* Integrating and configuring `SoundPool` for optimized audio management.
* Managing the lifecycle of audio streams properly.
* Writing isolated Unit Tests to verify data binding and UI communication logic.

## 📜 License
This project was built strictly for educational purposes using materials from *Android Programming: The Big Nerd Ranch Guide*. All rights to the original educational content belong to the book authors.
